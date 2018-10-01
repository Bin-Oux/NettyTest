package decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import model.ResponseData;

import java.util.List;

public class ResponseDataDecoderClient extends ReplayingDecoder<ResponseData> {

    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf in, List<Object> objects){
        ResponseData data = new ResponseData();
        data.setIntValue(in.readInt());
        objects.add(data);
    }
}
