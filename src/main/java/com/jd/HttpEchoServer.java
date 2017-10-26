package com.jd;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * Created by liushilang on 2017/9/1.
 */
public class HttpEchoServer {
    private final int port;

    public HttpEchoServer(int port){
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if(args.length !=1){
            System.err.println("Usage: "+HttpEchoServer.class.getSimpleName()+"<port>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        new HttpEchoServer(port).start();
    }

    public void start()throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("idleStateHandler",new IdleStateHandler(0, 0, 60, TimeUnit.SECONDS))
                                    .addLast("httpServerCodec",new HttpServerCodec())
//                                    .addLast(businessGroup,name,businessHandler)
                                    .addLast("requestController",new RequestController());
                        }
                    });
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }


    }
}
