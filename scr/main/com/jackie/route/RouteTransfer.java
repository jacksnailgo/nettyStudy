package com.jackie.route;

import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 解析方法method，获得方法名和参数，通过反射调用方法，如果未找到则返回404
 */
public class RouteTransfer implements InitializingBean, ApplicationContextAware {

    private ApplicationContext ctx;

    private Map<Class<?>,String> addressHandlers;


    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Object> handlers = ctx.getBeansWithAnnotation(AddressHandler.class);
        Map<Class<?> ,AddressHandlerWrapper> handlerWrapperMap = Maps.newHashMap();
        for(Object handler : handlers.values()){
            Class<?>[] interfaces = handler.getClass().getInterfaces();
            for(Class<?> interfa : interfaces){
                if(!interfa.isAnnotationPresent(AddressHandler.class)){
                    continue;
                }
                Method[] methods = interfa.getMethods();
                for(Method method : methods){
                    if(isAddressMethod(method)){
                        AddressHandlerWrapper wrapper = new AddressHandlerWrapper(handler, method);
                        //handlerWrapperMap.put()
                    }
                }
            }
        }
    }

    private boolean isAddressMethod(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        for(Class<?> parameterType : parameterTypes){
            //todo 对方法类型进行校验
        }
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }
}
