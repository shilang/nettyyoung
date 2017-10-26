package com.jd;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.ssl.SslHandler;


import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/**
 * Created by liushilang on 2017/9/7.
 */
public class SslChannelInitializer extends ChannelInitializer<Channel> {

    private final SSLContext context;
    private final boolean startTls;

    public SslChannelInitializer(SSLContext context, boolean startTls) {
        this.context = context;
        this.startTls = startTls;
    }

    protected void initChannel(Channel ch) throws Exception {
        SSLEngine engine = context.createSSLEngine();
        ch.pipeline().addFirst("ssl",new SslHandler(engine,startTls));
    }
}
