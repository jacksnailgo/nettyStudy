package server.channelInitialzer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslHandler;
import server.MySimpleHandler;

import javax.net.ssl.SSLContext;

public class ChannelInitzerImp extends ChannelInitializer<Channel> {

    private final SSLContext context;

    public ChannelInitzerImp(SSLContext context) {
        this.context = context;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new HttpRequestDecoder())
                //.addLast(new SslHandler(context.createSSLEngine()))
                .addLast(new MySimpleHandler())
                .addLast(new HttpObjectAggregator(1024*1024))
                .addLast(new HttpResponseEncoder());
    }
}
