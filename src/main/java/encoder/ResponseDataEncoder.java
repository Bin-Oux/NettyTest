package encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import model.ResponseData;

public class ResponseDataEncoder extends MessageToByteEncoder<ResponseData> {

    @Override
    protected void encode(ChannelHandlerContext context, ResponseData message, ByteBuf out) throws Exception{
        out.writeInt(message.getIntValue());
    }
}
