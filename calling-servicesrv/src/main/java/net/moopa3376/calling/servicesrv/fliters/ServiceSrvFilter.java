package net.moopa3376.calling.servicesrv.fliters;

import io.netty.channel.ChannelHandlerContext;
import net.moopa3376.calling.remoting.protocol.ProtocolMessage;

/**
 * Created by LeeAutumn on 17/01/2017.
 * blog: leeautumn.net
 *
 * @autuor : LeeAutumn
 */
public interface ServiceSrvFilter {
    public void doFiiler(ProtocolMessage protocolMessage,ChannelHandlerContext ctx);
}
