package server.channelInitialzer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import server.HeartbeatHandler;
import server.MySimpleHandler;

import javax.net.ssl.SSLContext;
import java.util.concurrent.TimeUnit;

public class HeartBeatChannelInitialzer  extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                .addLast(new IdleStateHandler(0,0,60, TimeUnit.SECONDS))
                .addLast(new HeartbeatHandler());
    }

}
