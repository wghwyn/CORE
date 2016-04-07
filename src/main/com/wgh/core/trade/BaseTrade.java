package com.wgh.core.trade;

import io.netty.channel.ChannelHandlerContext;

/**
 * 交易公共基础类
 */
public abstract class BaseTrade implements Runnable{
    protected String reqMsg;
    protected String rspMsg;
    protected ChannelHandlerContext context;

    public void setReqMsg(String reqMsg) {
        this.reqMsg = reqMsg;
    }

    public void setContext(ChannelHandlerContext context) {
        this.context = context;
    }

    protected abstract void proc() throws Exception;

    public void run(){
        try {
            //业务逻辑处理
            proc();

            //返回处理结果
            context.channel().writeAndFlush(rspMsg.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    };
}
