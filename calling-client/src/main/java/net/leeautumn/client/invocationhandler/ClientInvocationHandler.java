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
package net.leeautumn.client.invocationhandler;

import com.alibaba.fastjson.JSON;
import net.leeautumn.common.annotation.services.ServicesAn;
import net.leeautumn.remoting.protocol.ProtocolMessage;
import net.leeautumn.remoting.rpc.RPCRequest;
import net.leeautumn.remoting.protocol.ProtocolSerializer;
import net.leeautumn.remoting.protocol.ProtocolType;
import net.leeautumn.remoting.protocol.callingprotocol.CallingProtocolMessageBody;
import net.leeautumn.remoting.protocol.callingprotocol.CallingProtocolMessageHeader;
import net.leeautumn.remoting.protocol.callingprotocol.CallingProtocolMessage;
import net.leeautumn.remoting.util.RemotingUtil;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by LeeAutumn on 03/01/2017.
 * blog: leeautumn.net
 *
 * @autuor : LeeAutumn
 */
public class ClientInvocationHandler implements InvocationHandler {

    private InetAddress socketAddress;
    private int port;

    public ClientInvocationHandler(InetAddress socketAddress ,int port){
        this.socketAddress = socketAddress;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        /*在这里加入 远程过程调用 的操作*/
        //获取通信协议类型
        Class clazz = proxy.getClass();

        ProtocolType protocolType = null;
        Class<?> protocolMessageClass = null;

        if(clazz.isAnnotationPresent(ServicesAn.class)){
            ServicesAn servicesAn = (ServicesAn)clazz.getInterfaces()[0].getAnnotation(ServicesAn.class);
            protocolType = servicesAn.protocolType();
        }else{
            protocolType = ProtocolType.CallingProtocol;
        }

        protocolMessageClass = ProtocolType.getMessageClassByType(protocolType);
        ProtocolSerializer protocolSerializer = ProtocolType.getSerializerByType(protocolType);

        //开始连接
        ProtocolMessage protocolMessage  = (ProtocolMessage) protocolMessageClass.newInstance();

        Selector selector = connect(protocolMessage,protocolSerializer,protocolMessage.getBody().getRPCRequest(),method,args,proxy.getClass().getInterfaces()[0].getName());

        //获取结果
        ProtocolMessage result = (ProtocolMessage)getResponseMessage(selector,protocolSerializer,protocolMessageClass);
        /*远程过程通信 end */

        //此处返回 远程过程调用返回的序列化以后的信息body
        switch (protocolType){
            case CallingProtocol:
                return ((CallingProtocolMessage)result).body.getRPCResponse().getRPCResult();
            default:
                return null;
        }
    }

    private Selector connect(ProtocolMessage protocolMessage,ProtocolSerializer protocolSerializer,RPCRequest request,Method method,Object[] args,String serviceClassName){
        Selector selector = null;
        try {
            selector = Selector.open();

            SocketChannel socketChannel = SocketChannel.open();

            socketChannel.connect(new InetSocketAddress(socketAddress,port));

            if(socketChannel.isConnected()){

                request.setMethodDesc(RemotingUtil.getMethodDesc(method));
                request.setArgs(args);
                request.setServiceClassName(serviceClassName);

                System.out.println(protocolMessage.getClass());

                byte[] bytes = protocolSerializer.serializeMessage(protocolMessage);

                ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);

                byteBuffer.put(bytes);

                byteBuffer.flip();
                socketChannel.configureBlocking(false);
                socketChannel.write(byteBuffer);

                socketChannel.register(selector, SelectionKey.OP_READ);

            }else{
                System.out.println("not connected");
            }
        } catch (ClosedChannelException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return selector;
    }

    private <T> T getResponseMessage(Selector selector , ProtocolSerializer protocolSerializer , Class<T> protocolMessageClass) {
        T protocolMessage = null;
        try {
            while (true) {
                if (selector.select() > 0) {
                    Set<SelectionKey> res = selector.keys();

                    for (SelectionKey s : res) {
                        SocketChannel socketChannel1 = (SocketChannel) s.channel();

                        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);

                        socketChannel1.read(byteBuffer);

                        byte[] bytes = new byte[byteBuffer.position()-1];

                        byteBuffer.flip();

                        byteBuffer.get(new byte[1]);

                        byteBuffer.get(bytes);

                        protocolMessage = protocolSerializer.parseProtocolMessage(bytes, protocolMessageClass);

                    }
                    break;
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return protocolMessage;
    }
}
