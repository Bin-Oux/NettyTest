package decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import model.RequestData;

import java.nio.charset.Charset;

public class RequestDataEncoderClient extends MessageToByteEncoder<RequestData> {
    private final Charset charset = Charset.forName("UTF-8");

    @Override
    protected void encode(ChannelHandlerContext context,
                          RequestData message, ByteBuf out) {
        out.writeInt(message.getIntValue());
        out.writeInt(message.getStringValue().length());
        out.writeCharSequence(message.getStringValue(), charset);
    }
}
