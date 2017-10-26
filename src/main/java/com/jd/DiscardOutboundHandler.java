package com.jd;

import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by liushilang on 2017/9/5.
 */
@ChannelHandler.Sharable
public class DiscardOutboundHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ReferenceCountUtil.release(msg);
        promise.setSuccess(); //通知channelPromise数据己经被处理了
    }
}
