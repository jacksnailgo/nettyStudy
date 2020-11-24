package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * 服务器启动类 初级版本
 */
public class ServerBootStrapMain {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                        System.out.println("channel Read , msg :" + msg.toString(CharsetUtil.UTF_8) );
                    }

                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {

                        super.channelActive(ctx);
                    }
                });
        ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(8888));
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
