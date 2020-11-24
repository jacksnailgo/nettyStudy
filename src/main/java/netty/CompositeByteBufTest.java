package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

public class CompositeByteBufTest {
    /**
     * 测试复合缓冲区对不同Http协议，复用Headers
     * @param args
     */
    public static void main(String[] args) {
        CompositeByteBuf messageByteBuf = Unpooled.compositeBuffer();
        ByteBuf headerBuf = null;
        ByteBuf bodyBuf = null;
        messageByteBuf.addComponent(headerBuf);
        messageByteBuf.addComponent(bodyBuf);

        messageByteBuf.removeComponent(0);  // 去掉头部
        for(ByteBuf buf : messageByteBuf){
            System.out.println(buf.toString());
        }

    }
}
