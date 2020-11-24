package server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpRequest;

import java.io.File;


@ChannelHandler.Sharable
public class MyHttpHandler extends SimpleChannelInboundHandler<HttpRequest> {

    // 资源所在路径
    private static final String location;

    // 404文件页面地址
    private static final File NOT_FOUND;

    static {
        location = "/resources/html";
        String path = location + "/404.html";
        NOT_FOUND = new File(path);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
        System.out.println("recieved Http :" + msg.uri() +"," +  msg.method() + "content" + msg.toString());
        // 127.0.0.1:8888/request?id=1&person=jackie
        // 路由转发
        String uri = msg.uri();
        // 设置不支持favicon.ico文件
        


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
