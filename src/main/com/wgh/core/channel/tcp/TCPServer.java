package com.wgh.core.channel.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.lang.reflect.Method;

/**
 *  建立服务器端连接类
 */
public class TCPServer {
    private String handlerName;
    private ServerBootstrap bootstrap;
    private String ip = "127.0.0.1";
    private int port = 9999;
    private int groupSize = 2;
    private int workSize = 0;
    private int backLog = 1024;
    public void run(){
        if(workSize <= 0) {
            workSize = Runtime.getRuntime().availableProcessors() * 2;
        }

        //处理TCP请求线程池
        EventLoopGroup bossGroup = new NioEventLoopGroup(groupSize);
        //处理IO事件线程池
        EventLoopGroup workGroup = new NioEventLoopGroup(workSize);

        bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.SO_BACKLOG, backLog);

        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline cp = socketChannel.pipeline();
                cp.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                cp.addLast("frameEncoder", new LengthFieldPrepender(4));
                cp.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                cp.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
//                cp.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
//                //构造函数传递要解码成的类型
//                cp.addLast("protobufDecoder", new ProtobufDecoder(LocalTimeProtocol.Locations.getDefaultInstance()));
//                //编码
//                cp.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
//                cp.addLast("protobufEncoder", new ProtobufEncoder());
                cp.addLast(new TCPServerHandle());
                cp.addLast(new TCPServerOutHandle());
            }
        });
        try {
            //绑定服务器地址，端口
            bootstrap.bind(ip, port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public void setWorkSize(int workSize) {
        this.workSize = workSize;
    }

    public void setBackLog(int backLog) {
        this.backLog = backLog;
    }

    public void setHandler(String handlerName) {
        this.handlerName = handlerName;
    }
}
