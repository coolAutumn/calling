package net.moopa.calling.servicemanager.channelhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.moopa.calling.common.constants.Constant;
import net.moopa.calling.remoting.protocol.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by LeeAutumn on 28/12/2016.
 * blog: leeautumn.net
 */
public class ProcessRequestHandler extends ChannelInboundHandlerAdapter{

    private static Logger logger = LoggerFactory.getLogger(Constant.PROCESS_REQUEST_HANDLER_LOGGERNAME);

    private RequestHandler  getServiceProviderRequestHandler      =  new GetServiceProviderRequestHandler();
    private RequestHandler  updateServiceProviderRequestHandler   =  new UpdateServiceProviderHandler();


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;

        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);

        processByReqCode(ctx,bytes,bytes[0]);

        super.channelRead(ctx, msg);
    }



    public void processByReqCode(ChannelHandlerContext ctx,byte[] bytes,byte b){
        Code code = Code.getRequestCode(b);

        if(code == null){
            logger.error("Unknown Request Code : {}",Byte.valueOf(b).toString());
            return;
        }
        switch (code){
            case GET_SERVICE_PROVIDER:
                getServiceProviderRequestHandler.process(ctx,bytes);
                break;
            case UPDATE_SERVICE_PROVIDER:
                updateServiceProviderRequestHandler.process(ctx,bytes);

        }
    }


}
