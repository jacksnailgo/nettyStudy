package com.jackie.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpRequest;

@ChannelHandler.Sharable
public class MyHttpHandler extends SimpleChannelInboundHandler<HttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) throws Exception {
        System.out.println("request:" + httpRequest.uri());
        String uri = httpRequest.uri();//  解析URI ， 得到Http里面的所有参数

    }
}
