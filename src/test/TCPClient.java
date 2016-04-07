import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import io.netty.bootstrap.Bootstrap;
/**
 * 建立客户端连接类
 */
public class TCPClient implements Runnable{
    private String msg = "";
    private Channel channel = null;
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public void run(){
        ChannelFuture channelFuture = null;
        try {
            channelFuture = channel.writeAndFlush(msg).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }

    public void connect(){
        //String ip = "192.168.249.132";
        String ip = "127.0.0.1";
        int port = 8000;


        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(group);
        b.channel(NioSocketChannel.class);

        b.handler(new ChannelInitializer<Channel>() {

            @Override
            protected void initChannel(Channel channel) throws Exception {
                ChannelPipeline cp = channel.pipeline();
                cp.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                cp.addLast("frameEncoder", new LengthFieldPrepender(4));
                cp.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                cp.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                cp.addLast(new TCPClientHandler());
            }
        });

//        b.option(ChannelOption.SO_KEEPALIVE, true);


        try {
            channel = b.connect(ip, port).sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        if(channel != null){
            channel.close();
        }
    }

    public static void main(String[] args) {
        int i = 0;
        TCPClient client = new TCPClient();
        while (i <= 0) {
            client.setMsg("0001msg:" + i);
            client.connect();
            new Thread(client).start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
