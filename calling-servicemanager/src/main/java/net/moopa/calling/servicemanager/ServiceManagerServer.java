package net.moopa.calling.servicemanager;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import net.moopa.calling.servicemanager.channelhandler.ProcessRequestHandler;

/**
 * Created by LeeAutumn on 28/12/2016.
 * blog: leeautumn.net
 */
public class ServiceManagerServer {

    //listen to the port and accept the request
    EventLoopGroup acceptGroup = new NioEventLoopGroup();
    //process the request
    EventLoopGroup processGroup = new NioEventLoopGroup();



    public void init() {
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(acceptGroup, processGroup)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ProcessRequestHandler());

            //bind the port
            ChannelFuture f = serverBootstrap.bind(3340).sync();

            //waiting for the close
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            acceptGroup.shutdownGracefully();
            processGroup.shutdownGracefully();
        }
    }

    private class InitialProcessor extends ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new ProcessRequestHandler());
        }
    }


}
