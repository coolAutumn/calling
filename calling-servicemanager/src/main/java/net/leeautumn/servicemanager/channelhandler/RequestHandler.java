package net.leeautumn.servicemanager.channelhandler;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by LeeAutumn on 28/12/2016.
 * blog: leeautumn.net
 */
public interface RequestHandler {
    public void process(ChannelHandlerContext ctx,byte[] bytes);
}
