package com.wgh.core.Global;

import io.netty.buffer.ByteBuf;

/**
 * 公共工具类
 */
public class CommUtil {
    /**
     * ByteBuf转String
     * @param buf
     * @return
     */
    public static String byteBufToString(ByteBuf buf){
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        return new String(bytes);
    }
}
