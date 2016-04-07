import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *
 */
public class TCPClientHandler extends SimpleChannelInboundHandler<Object>{
    public void channelRead0(ChannelHandlerContext context, Object msg){
        System.out.println(msg);
    }
}
