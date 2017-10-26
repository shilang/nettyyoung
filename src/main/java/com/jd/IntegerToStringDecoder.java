package com.jd;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * Created by liushilang on 2017/9/7.
 */
public class IntegerToStringDecoder extends MessageToMessageDecoder<Integer> {
    protected void decode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
        out.add(String.valueOf(msg));
    }
}
