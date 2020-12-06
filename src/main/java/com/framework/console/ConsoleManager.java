package com.framework.console;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class ConsoleManager implements ApplicationContextAware {

    Map<String,MethodWrapper> wrapperMap = new HashMap<>();


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, IConsoleService> beansOfType = applicationContext.getBeansOfType(IConsoleService.class);
        for(IConsoleService service : beansOfType.values()){
            Method[] methods = service.getClass().getDeclaredMethods();
            for(Method method : methods){
                Console annotation = method.getAnnotation(Console.class);
                if(annotation != null){
                    MethodWrapper wrapper = new MethodWrapper();
                    wrapper.setMethod(method);
                    wrapper.setOwner(service);
                    wrapper.setMethodName(annotation.method());
                    wrapperMap.put(annotation.method(),wrapper);
                }
            }
        }
    }

    @PostConstruct
    public void init(){
        Thread daemon = new Thread(){
            @Override
            public void run(){
                System.out.println("检查控制台输入线程启动");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                while(true){
                    try {
                        String content = reader.readLine();
                        String[] split = content.split("\\s");  // 空格分隔
                        String readMethodName = split[0];
                        Object[] objects = new Object[split.length-1];
                        for(int i=0;i<split.length-1;i++){
                            objects[i] = split[i+1];
                        }
                        if(wrapperMap.containsKey(readMethodName)){
                            MethodWrapper wrapper = wrapperMap.get(readMethodName);
                            if(wrapper == null){
                                System.err.println("检查输入内容，no wrapper:"+readMethodName);
                                continue;
                            }
                            boolean accessible = wrapper.getMethod().isAccessible();
                            if(!accessible){
                                wrapper.getMethod().setAccessible(true);
                            }
                            wrapper.getMethod().invoke(wrapper.getOwner(),objects);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        daemon.setDaemon(true);
        daemon.setName("ConsoleCmdThread");
        daemon.start();
    }
}
