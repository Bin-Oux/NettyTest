package Processing;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import model.RequestData;
import model.ResponseData;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext context){
        RequestData message = new RequestData();
        message.setIntValue(123);
        message.setStringValue("On s'emmerde à Aston");
        ChannelFuture future = context.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext context,
                            Object message){
        System.out.println((ResponseData)message);
        context.close();
    }
}
