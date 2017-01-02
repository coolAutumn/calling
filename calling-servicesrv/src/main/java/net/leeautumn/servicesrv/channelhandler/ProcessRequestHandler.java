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
package net.leeautumn.servicesrv.channelhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.leeautumn.common.constants.Constant;
import net.leeautumn.remoting.protocol.Code;
import net.leeautumn.remoting.protocol.ProtocolMessage;
import net.leeautumn.remoting.protocol.callingprotocol.CallingProtocolMessage;
import net.leeautumn.servicesrv.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by LeeAutumn on 28/12/2016.
 * blog: leeautumn.net
 *
 * @author LeeAutumn
 */
public class ProcessRequestHandler extends ChannelInboundHandlerAdapter{

    private static Logger logger = LoggerFactory.getLogger(Constant.PROCESS_REQUEST_HANDLER_LOGGERNAME);

    private Map<Code,ServiceProvider> registeredServiceProvider;

    public ProcessRequestHandler(Map<Code,ServiceProvider> registeredServiceProvider){
        this.registeredServiceProvider = registeredServiceProvider;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;

        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);



        processByReqCode(ctx,bytes,3341);
    }



    public void processByReqCode(ChannelHandlerContext ctx,byte[] bytes,int req){
        Code code = Code.getRequestCode(req);

        if(code == null){
            logger.error("Unknown Request Code : {}",req);
            return;
        }

        ServiceProvider serviceProvider = registeredServiceProvider.get(code);

        if(serviceProvider == null){
            logger.error("Unregistered Service : {}", code.getCodeMeaning());
            return;
        }

        //filters chain



        //transfer the message to the service provider
        CallingProtocolMessage protocolMessage = serviceProvider.getProtocolSerializer().parseProtocolMessage(bytes, CallingProtocolMessage.class);


        serviceProvider.invoke(ctx,protocolMessage);
    }


}
