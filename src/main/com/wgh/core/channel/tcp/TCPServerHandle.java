package com.wgh.core.channel.tcp;

import com.wgh.core.Global.BeanFactory;
import com.wgh.core.Global.CommUtil;
import com.wgh.core.trade.BaseTrade;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *
 */
public class TCPServerHandle extends SimpleChannelInboundHandler<Object>{
    @Override
    protected void channelRead0(ChannelHandlerContext context, Object o){
        //根据客户端传送过来的报文来调用具体交易
//        String msg = CommUtil.byteBufToString(buf);
        String msg = o.toString();
        String tradeCode = msg.substring(0, 4);
        BaseTrade trade = (BaseTrade)BeanFactory.getInstance().getBean("T" + tradeCode);
        trade.setReqMsg(msg.toString());
        trade.setContext(context);
        trade.run();
    }
}
