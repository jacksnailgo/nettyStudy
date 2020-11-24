import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import server.channelInitialzer.ChannelInitzerImp;
import server.channelInitialzer.HeartBeatChannelInitialzer;
import server.channelInitialzer.HttpChannelInitialzer;
import server.channelInitialzer.WebSocketInitialzerImpl;

import java.net.InetSocketAddress;

/**
 * 服务器启动类  ——第二个版本，用了ChannelInitializer
 */
public class ServerBootStrap {

    static final  int FourEightPort = 8888;

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
               // .option(ChannelOption.TCP_NODELAY,true)
              //  .option(ChannelOption.SO_KEEPALIVE,true)   //其实并不知道这几个选项的意义
              //  .option(ChannelOption.SO_TIMEOUT,5000)
                .childHandler(new HttpChannelInitialzer());     // 添加channelhandler
        ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(FourEightPort));   //绑定端口，成为服务器
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("future回调，已经绑定成功");
                }else {
                    System.out.println("绑定失败");
                    future.cause().printStackTrace();
                }
            }
        });
        System.out.println("启动服务器");
    }
}
