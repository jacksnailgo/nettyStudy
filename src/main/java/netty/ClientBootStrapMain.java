package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * 服务器内部创建一个客户端绑定 ，但是失败了   ：从 Channel 引导客户端
 *
 *
 * 需求情景： 假设你的服务器正在处理一个客户端的请求，这个请求需要它充当第三方系统的客户端。当
 * 一个应用程序（如一个代理服务器）必须要和组织现有的系统集成
 * 时，就可能发生这种情况。
 * 在这种情况下，将需要从已经被接受的子 Channel 中引导一个客户端 Channel。
 */
public class ClientBootStrapMain {

   static final int port = 8888;
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
                    ChannelFuture connectFuture ;
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        Bootstrap bootstrap = new Bootstrap();//5
                        bootstrap.channel(NioSocketChannel.class) //6
                                .handler(new SimpleChannelInboundHandler<ByteBuf>() {  //7
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
                                        System.out.println("Reveived data");
                                    }
                                });
                        bootstrap.group(ctx.channel().eventLoop()); //8
                         connectFuture = bootstrap.connect(new InetSocketAddress(port + 1));  //9  客户端连接服务器
                        connectFuture.addListener(new ChannelFutureListener() {
                            @Override
                            public void operationComplete(ChannelFuture future) throws Exception {
                                if(future.isSuccess()){
                                    System.out.println("客户端完成绑定");
                                } else {
                                    System.out.println("客户端绑定 port :"+ port + 1  + "+失败 ");
                                }
                            }
                        });
                    }
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                        System.out.println("channel Read , msg :" + msg.toString(CharsetUtil.UTF_8) );
                        if(connectFuture.isSuccess()){
                            System.out.println("服务器接收到客户端的连接");
                        }else {
                            System.out.println("from clients failed");
                        }
                    }
                });
        ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(port));
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("服务器绑定成功");
                }else {
                    System.out.println("绑定失败");
                    future.cause().printStackTrace();
                }
            }
        });
    }
}
