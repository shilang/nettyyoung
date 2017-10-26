package com.jd.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * Created by liushilang on 2017/9/5.
 */
public class SliceTest {
    public static void main(String[] args) {
        test2();
    }
    private  static void test1(){
        Charset utf8 = Charset.forName("utf-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!",utf8);
        ByteBuf sliced = buf.slice(0,15);
        System.out.println(sliced.toString(utf8));
        buf.setByte(0,(byte)'J');
        if(buf.getByte(0) == sliced.getByte(0)){
            System.out.println("true");
        }
    }

    private static void test2(){
        Charset utf8 = Charset.forName("utf-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!",utf8);
        ByteBuf copy = buf.copy(0,15);
        System.out.println(copy.toString(utf8));
        buf.setByte(0,(byte)'J');
        if(buf.getByte(0) == copy.getByte(0)){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }
}
