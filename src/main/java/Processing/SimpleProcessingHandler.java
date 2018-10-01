package Processing;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import model.RequestData;
import model.ResponseData;

public class SimpleProcessingHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf tmp;

    @Override
    public void handlerAdded(ChannelHandlerContext context){
        System.out.println("Handler added");
        tmp = context.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext context){
        System.out.println("Handler removed");
        tmp.release();
        tmp = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object message){
        ByteBuf m = (ByteBuf) message;
        tmp.writeBytes(m);
        m.release();
        if(tmp.readableBytes() >= 4) {
            RequestData requestData = new RequestData();
            requestData.setIntValue(tmp.readInt());
            ResponseData responseData = new ResponseData();
            responseData.setIntValue(requestData.getIntValue() * 2);
            ChannelFuture future = context.writeAndFlush(responseData);
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }
}
