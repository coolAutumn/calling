/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.moopa.calling.servicesrv.channelhandler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.moopa.calling.common.constants.Constant;
import net.moopa.calling.remoting.protocol.ProtocolMessage;
import net.moopa.calling.remoting.protocol.ProtocolSerializer;
import net.moopa.calling.remoting.protocol.ProtocolType;
import net.moopa.calling.remoting.rpc.RPCRequest;
import net.moopa.calling.remoting.rpc.RPCResponseCode;
import net.moopa.calling.remoting.util.RemotingUtil;
import net.moopa.calling.servicesrv.fliters.ServiceSrvFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by LeeAutumn on 28/12/2016.
 * blog: leeautumn.net
 *
 * @author LeeAutumn
 */
public class ProcessRequestHandler extends ChannelInboundHandlerAdapter{
    //Logger
    private static Logger logger = LoggerFactory.getLogger(Constant.PROCESS_REQUEST_HANDLER_LOGGERNAME);

    //store the declared services in moudle 'calling-services'.
    private Map<String,Object> registeredServices;

    //do filters before the protocol message reach the serviceImpl object
    private List<ServiceSrvFilter> beforeInvokeServiceFilters = new LinkedList<ServiceSrvFilter>();
    private List<ServiceSrvFilter> afterInvokeServiceFilters = new LinkedList<ServiceSrvFilter>();

    public ProcessRequestHandler(Map<String,Object> registeredServices){
        this.registeredServices = registeredServices;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;

        byte[] bytes = new byte[byteBuf.readableBytes()];

        logger.info("Get bytes length:{}",bytes.length);

        byteBuf.readBytes(bytes);

        /**         Pre-process
         * translate the bytes to protocol message
         * get the protocoltype , serializer and the messageclass
         **/
        //获得协议种类
        ProtocolType protocolType = getProtocolType(bytes);

        //将accept的二进制消息转换成协议消息
        ProtocolSerializer protocolSerializer = ProtocolType.getSerializerByType(protocolType);
        Class<?> messageClass = ProtocolType.getMessageClassByType(protocolType);

        ProtocolMessage protocolMessage = getProtocolMessage(bytes,protocolSerializer,messageClass);
        /*Pre-process over*/

        //do before fliters
        for(ServiceSrvFilter filter : beforeInvokeServiceFilters){
            filter.doFiiler(protocolMessage,ctx);
        }

        //according to the message info to invoke the message
        Object result = invokeMethod(ctx,protocolMessage,protocolSerializer,messageClass);


        //do after fliters
        for(ServiceSrvFilter filter : afterInvokeServiceFilters){
            filter.doFiiler(protocolMessage,ctx);
        }

        //send back the result
        sendResponse(ctx,protocolSerializer,messageClass,result);
    }

    private ProtocolMessage getProtocolMessage(byte[] bytes,ProtocolSerializer protocolSerializer,Class<?> messageClass){

        ProtocolMessage protocolMessage = (ProtocolMessage)protocolSerializer.parseProtocolMessage(Arrays.copyOfRange(bytes,1,bytes.length),messageClass);

        return protocolMessage;
    }


    public Object invokeMethod(ChannelHandlerContext ctx,ProtocolMessage protocolMessage,ProtocolSerializer protocolSerializer,Class<?> messageClass){

        RPCRequest rpcRequest = protocolMessage.getBody().getRPCRequest();
        if(rpcRequest == null){
            logger.error("Null rpcRequest, wrong request.");
        }

        String className = rpcRequest.getServiceClassName();
        String methodDesc = rpcRequest.getMethodDesc();
        Object[] args = rpcRequest.getArgs();

        Object serviceImpl = registeredServices.get(className);

        Method invokeMathod = findMethod(serviceImpl,methodDesc);


        Object result = null;
        try {
            result = invokeMathod.invoke(serviceImpl,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return result;
    }

    private ProtocolType getProtocolType(byte[] bytes){
        return ProtocolType.getProtocolTypeByByte(bytes[0]);
    }

    private Method findMethod(Object serviceImpl , String methodDesc){

        Class<?> clazz = serviceImpl.getClass();

        Method result = null;

        for(Method method : clazz.getDeclaredMethods()){
            if(methodDesc.equals(RemotingUtil.getMethodDesc(method))){
                result = method;
                break;
            }
        }

        return result;
    }

    private void sendResponse(ChannelHandlerContext ctx,ProtocolSerializer protocolSerializer,Class<?> messageClass,Object result){
        //开始生成返回信息
        byte[] res = null;
        try {
            ProtocolMessage responseMessage = (ProtocolMessage)messageClass.newInstance();

            responseMessage.getBody().getRPCResponse().setResponseCode(RPCResponseCode.RESPONSE_OK);
            responseMessage.getBody().getRPCResponse().setRPCResult(result);

            res = protocolSerializer.serializeMessage(responseMessage);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        ByteBuf byteBuf = Unpooled.copiedBuffer(res);

        ctx.writeAndFlush(byteBuf);
    }

}
