package net.moopa.calling.servicesrv.fliters;

import io.netty.channel.ChannelHandlerContext;
import net.moopa.calling.common.annotation.ThreadSafe;
import net.moopa.calling.remoting.protocol.ProtocolMessage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by LeeAutumn on 17/01/2017.
 * blog: leeautumn.net
 *
 * @autuor : LeeAutumn
 */
@ThreadSafe
public class TrafficAnalysisFliter implements ServiceSrvFilter{
    private static Map<String,AtomicInteger> trafficCounterMap = new ConcurrentHashMap<String, AtomicInteger>();

    @Override
    public void doFiiler(ProtocolMessage protocolMessage, ChannelHandlerContext ctx) {
        String methodDesc = protocolMessage.getBody().getRPCRequest().getServiceClassName();

        AtomicInteger atomicInteger ;
        if((atomicInteger = trafficCounterMap.get(methodDesc)) == null){
            trafficCounterMap.put(methodDesc,new AtomicInteger(0));
        }else {
            atomicInteger.getAndIncrement();
        }
    }
}
