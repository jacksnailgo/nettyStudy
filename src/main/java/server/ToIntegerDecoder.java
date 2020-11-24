package server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class ToIntegerDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() >= 4){
            out.add(in.readInt());   //检查是否至少有4字节可以读取，一个int的字节长度   Long是8，
        }
        /**
         * 面临的问题： 每一次都要验证是否有足够的数据 ，十分繁琐 ，因此使用ReplayingDecoder
         */
    }
}
class ToIntegerDecoder2 extends ReplayingDecoder<Void>{

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(in.readInt());  // 并不是所有的Bytebuf都会被支持，他的性能比ByteToMeesageDecoder稍慢一点
    }
}