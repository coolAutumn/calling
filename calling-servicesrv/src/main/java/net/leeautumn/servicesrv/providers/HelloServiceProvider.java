package net.leeautumn.servicesrv.providers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import net.leeautumn.common.constants.Constant;
import net.leeautumn.remoting.protocol.Code;
import net.leeautumn.remoting.protocol.ProtocolMessage;
import net.leeautumn.remoting.protocol.ProtocolSerializer;
import net.leeautumn.remoting.protocol.ProtocolType;
import net.leeautumn.remoting.protocol.callingprotocol.CallingProtocolBody;
import net.leeautumn.remoting.protocol.callingprotocol.CallingProtocolHeader;
import net.leeautumn.remoting.protocol.callingprotocol.CallingProtocolMessage;
import net.leeautumn.servicesrv.ServiceProvider;
import net.leeautumn.common.annotation.servicesrv.ServiceProviderAn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by LeeAutumn on 30/12/2016.
 * blog: leeautumn.net
 */
@ServiceProviderAn(requestCode = Code.HELLO_SERVICE ,
        protocolType = ProtocolType.CallingProtocol)
public class HelloServiceProvider extends ServiceProvider{
    private static Logger logger = LoggerFactory.getLogger(Constant.HELLO_SERVICEPROVIDER_LOGGERNAME);

    public HelloServiceProvider(){};

    public HelloServiceProvider(Code code, ProtocolType protocolType, ProtocolSerializer protocolSerializer){
        setCode(code);
        setProtocolType(protocolType);
        setProtocolSerializer(protocolSerializer);
    }

    @Override
    public void invoke(ChannelHandlerContext ctx, ProtocolMessage protocolMessage) {
        //You have to set the cast type here to get the specific message type
        CallingProtocolMessage callingProtocolMessage = (CallingProtocolMessage)protocolMessage;

        logger.info("massage:\n " +
                "protocol type : {}\n " +
                "type: {}\n " +
                "ReqCode:{}\n " +
                "version.minorversion:{}.{}\n " +
                "attacheMentSize: {}\n " +
                "attachment : {}\n " +
                "Body:{} ",
                callingProtocolMessage.getHeader().protocolType,
                callingProtocolMessage.getHeader().type,
                callingProtocolMessage.getHeader().code,
                callingProtocolMessage.getHeader().version,
                callingProtocolMessage.getHeader().minorVersion,
                callingProtocolMessage.getHeader().attachmentSize,
                callingProtocolMessage.getHeader().attachment,
                callingProtocolMessage.getBody());

        CallingProtocolMessage callingProtocolMessage1 = new CallingProtocolMessage();
        callingProtocolMessage1.setHeader(new CallingProtocolHeader());

        String res = "This is calling servicesrv. Welcome!";
        CallingProtocolBody callingProtocolBody = new CallingProtocolBody();
        callingProtocolBody.body = res;
        callingProtocolMessage1.setBody(callingProtocolBody);

        byte[] result = getProtocolSerializer().serializeMessage(callingProtocolMessage1);
        ByteBuf byteBuf = Unpooled.copiedBuffer(result);

        ctx.writeAndFlush(byteBuf);

        ctx.close();

    }
}
