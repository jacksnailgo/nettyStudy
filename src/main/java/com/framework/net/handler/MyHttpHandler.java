package com.framework.net.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpRequest;

@ChannelHandler.Sharable
public class MyHttpHandler extends SimpleChannelInboundHandler<HttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
        System.out.println("request:" + msg.uri()+"," + msg.method().name());
        String uri = msg.uri();//  解析URI ， 得到Http里面的所有参数
        if ("/favicon.ico".equals(uri)) {
            ctx.close();
            return;
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
