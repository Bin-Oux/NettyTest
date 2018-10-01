package encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import model.RequestData;

import java.nio.charset.Charset;
import java.util.List;

public class RequestDataEncoder extends ReplayingDecoder<RequestData> {
    private final Charset charset = Charset.forName("UTF-8");

    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf inner, List<Object> out) throws Exception {
        RequestData data = new RequestData();
        data.setIntValue(inner.readInt());
        int strLen = inner.readInt();
        data.setStringValue(inner.readCharSequence(strLen,charset).toString());
        out.add(data);
    }
}
