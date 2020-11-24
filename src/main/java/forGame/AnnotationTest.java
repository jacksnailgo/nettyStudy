package forGame;

import com.sun.org.apache.bcel.internal.classfile.Field;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import io.netty.bootstrap.ServerBootstrap;
import server.HeartbeatHandler;
import server.channelInitialzer.ChannelInitzerImp;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class AnnotationTest {



    public static void main(String[] args) throws IOException {

        Class<ServerBootstrap> serverBootstrapClass = ServerBootstrap.class;
        // Class<AnnotationTest> annotationTestClass = AnnotationTest.class;
        JavaProjectBuilder builder = new JavaProjectBuilder();
        String pathName = "";
        builder.addSource(new File(pathName));
        JavaClass classByName = builder.getClassByName("AnnotationTest");
        List<JavaField> fields = classByName.getFields();
        for(JavaField field : fields){
            String comment = field.getComment();
            System.out.println(comment);
        }

    }
}
