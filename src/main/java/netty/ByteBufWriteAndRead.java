package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

public class ByteBufWriteAndRead {
    public static void main(String[] args) {
        CompositeByteBuf byteBufs = Unpooled.compositeBuffer();
        int i = 0;
        /*while(byteBufs.isWritable() ){

            if(byteBufs.capacity() == 1000){
                break;
            }
        }*/
        byteBufs.writeInt(i++);
        byteBufs.writeInt(2);
        byteBufs.writeBoolean(false);

        byteBufs.writeChar(';');
        if(byteBufs.isReadable()){
            System.out.println(true);
        }
        System.out.println(byteBufs.toString());
       /* //标记索引
        byteBufs.markReaderIndex();
        byteBufs.markWriterIndex();
        //重置索引
        byteBufs.resetReaderIndex();
        byteBufs.resetWriterIndex();*/

        //clear可以将索引置为0，但是并不会清除里面的内容
       // byteBufs.clear();
        System.out.println(byteBufs.capacity());
        System.out.println(byteBufs.toString());

        //ByteBuf查找  用ByteProcessor
        byteBufs.writeChar('\r');
        ByteProcessor byteProcessor = ByteProcessor.FIND_CR;
        int index = byteBufs.forEachByte(byteProcessor);
        System.out.println("SEMI_COLON at " + index);



        //派生缓冲区  返回一个新的实例，她的创建成本低廉，但是如果修改了这个实例，也会修改她的源实例(byteBufs)
        Charset utf8 = CharsetUtil.UTF_8;
        ByteBuf buf = Unpooled.copiedBuffer("I'm learning netty!",utf8);

        ByteBuf duplicate = buf.duplicate();
        System.out.println(duplicate.toString(utf8));
        ByteBuf slice = buf.slice(0,9);
        System.out.println(slice.toString(utf8));
        //打印十六进制的byteBuf
        System.out.println(ByteBufUtil.hexDump(duplicate));


        //引用计数

    }
}
