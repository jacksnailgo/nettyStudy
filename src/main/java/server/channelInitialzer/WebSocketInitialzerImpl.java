package server.channelInitialzer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import server.HeartbeatHandler;
import server.MySimpleHandler;

import java.util.concurrent.TimeUnit;

public class WebSocketInitialzerImpl extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new HttpServerCodec())
                .addLast(new HttpObjectAggregator(65536))
                .addLast(new WebSocketServerProtocolHandler("/wb"))
                .addLast(new TextFrameHandler())
                .addLast(new BinaryFrameHandler())
                .addLast(new ContinuationFrameHandler())
                .addLast(new MySimpleHandler())
                .addLast(new IdleStateHandler(0, 0, 60, TimeUnit.SECONDS))
                //.addLast(new HeartbeatHandler())
                .addLast(new ReadTimeoutHandler(10000));
        ;

    }


}

final class TextFrameHandler extends
        SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    public void channelRead0(ChannelHandlerContext ctx,
                             TextWebSocketFrame msg) throws Exception {
// Handle text frame
    }
}

final class BinaryFrameHandler extends
        SimpleChannelInboundHandler<BinaryWebSocketFrame> {
    @Override
    public void channelRead0(ChannelHandlerContext ctx,
                             BinaryWebSocketFrame msg) throws Exception {
// Handle binary frame
    }
}

final class ContinuationFrameHandler extends
        SimpleChannelInboundHandler<ContinuationWebSocketFrame> {
    @Override
    public void channelRead0(ChannelHandlerContext ctx,
                             ContinuationWebSocketFrame msg) throws Exception {
// Handle continuation frame
    }
}


