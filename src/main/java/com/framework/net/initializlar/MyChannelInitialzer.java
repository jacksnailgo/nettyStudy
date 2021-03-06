package com.framework.net.initializlar;

import com.framework.net.handler.MyHttpHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.timeout.IdleStateHandler;



public class MyChannelInitialzer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new HttpServerCodec())
                .addLast(new HttpObjectAggregator(65536))
                .addLast(new IdleStateHandler(1000,1000,1000))
                .addLast(new MyHttpHandler());
    }
}
