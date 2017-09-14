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
package net.moopa3376.calling.servicesrv;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import net.moopa3376.calling.common.constants.Constant;
import net.moopa3376.calling.servicesrv.config.ServiceServerConfig;
import net.moopa3376.calling.servicesrv.channelhandler.ProcessRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LeeAutumn on 30/12/2016.
 * blog: leeautumn.net
 *
 * @author LeeAutumn
 */
public class CallingServiceServer
{
    //listen to the port and accept the request
    EventLoopGroup acceptGroup = new NioEventLoopGroup();
    //process the request
    EventLoopGroup processGroup = new NioEventLoopGroup();

    private static Logger logger = LoggerFactory.getLogger(Constant.SERVICE_SERVER_LOGGERNAME);

    //这个Map key : 就是service.class.name
    protected Map<String,Object> registeredServices = new HashMap<String, Object>();

    private void init() {
        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class)
                    .group(acceptGroup, processGroup)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new InitialProcessor());

            //bind the port
            ChannelFuture f = serverBootstrap.bind(Integer.valueOf(ServiceServerConfig.getConfig("serviceserver.port"))).sync();

            logger.info("Service server start at the port {}",ServiceServerConfig.getConfig("serviceserver.port"));

            //register the service provider
            RegisterSupporter.register(registeredServices);

            //waiting for the close
            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            acceptGroup.shutdownGracefully();
            processGroup.shutdownGracefully();
        }
    }

    private class InitialProcessor extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new ProcessRequestHandler(registeredServices));
        }
    }

    public void run(){
        init();
    }

    public static void main( String[] args )
    {
        new CallingServiceServer().run();
    }
}
