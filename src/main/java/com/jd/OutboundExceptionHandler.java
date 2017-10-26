package com.jd;

import io.netty.channel.*;

/**
 * Created by liushilang on 2017/9/6.
 */
public class OutboundExceptionHandler extends ChannelOutboundHandlerAdapter{
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        promise.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
                if(!future.isSuccess()){
                    future.cause().printStackTrace();
                    future.channel().close();
                }
            }
        });
    }
}
