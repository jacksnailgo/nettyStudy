package com;

import com.framework.net.initializlar.MyChannelInitialzer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppBootStrap {

    private static  final int port = 8888;

    private static ConfigurableApplicationContext context;
    public static void main(String[] args) throws InterruptedException {
        context = new ClassPathXmlApplicationContext("total.xml");

        serverStart();
    }

    public static void serverStart() throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootStrap = new ServerBootstrap();
        serverBootStrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childHandler(new MyChannelInitialzer());
        ChannelFuture sync = serverBootStrap.bind(port).sync();
        sync.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                System.out.println("回调提示服务器启动成功");
            }
        });
        System.out.println("ready to work!!!");
    }
}
