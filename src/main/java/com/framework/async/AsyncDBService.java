package com.framework.async;

import com.framework.async.entity.AsyncDBEntity;
import com.framework.async.util.DBOperation;
import com.framework.async.util.ExceptionCallBack;
import com.framework.async.util.ISyncStrategy;
import com.framework.async.util.SimpleThreadFactory;
import com.framework.dao.Dao;
import com.framework.dao.Updater;
import com.framework.server.ServerCloser;
import com.framework.server.ServerStarter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AsyncDBService implements ApplicationContextAware, ServerStarter, ServerCloser {

    private int threads = 16;
    /**
     *
     */
    private final Map<Class<?>, Updater> updaterMap = new ConcurrentHashMap<>();
    /**
     * 2者通过Spring注入
     */
    @Autowired
    private ISyncStrategy syncStrategy;
    @Autowired
    private ExceptionCallBack exceptionCallBack;

    private SyncQueuePool syncQueuePool;

    @PostConstruct
    public void init() {
        this.syncQueuePool = new SyncQueuePool(threads, new SimpleThreadFactory(), syncStrategy, exceptionCallBack);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Updater> daoMap = applicationContext.getBeansOfType(Updater.class);
        for (Updater updater : daoMap.values()) {
            //所有添加@updater的类，都将会被用来传输实体
            Class<?> updateInterface  = updater.getClass().getInterfaces()[0];
            updaterMap.put(updateInterface,updater);
        }
    }


    private boolean doOperation(AsyncDBEntity entity, DBOperation operation) {
        if(entity.submit(operation)){
            if(entity.getUpdater() == null){
                //所以这到底是个啥玩意儿？
               /** 分2步走，1是将entity加入SyncQueuePool中，
                * 会有一个特定的Queue，来消费entity的提交操作，而这个消费是放在SyncQueue中，run方法会将entity取出，
                根据entity的Updater，找到Dao,然后调用Hibernate进行入库*/
                Class<? extends AsyncDBEntity> aClass = entity.getClass();
                Dao annotation = aClass.getAnnotation(Dao.class);
                Updater updater = updaterMap.get(annotation.update());
                if(updater == null){
                    System.err.println(entity +"没有@Dao或者Dao中未包含指定Dao");
                    return false;
                }
                entity.setUpdater(updater);
                return syncQueuePool.submit(entity);
            }
        }
        return true;
    }

    public void delete(AsyncDBEntity entity) {
        entity.serialize();
        doOperation(entity, DBOperation.DELETE);
    }

    public void insert(AsyncDBEntity entity) {
        entity.serialize();
       try{
           doOperation(entity, DBOperation.INSERT);
       }catch (Exception e ){
           e.printStackTrace();
       }
    }

    public void update(AsyncDBEntity entity) {
        entity.serialize();
        try{
            doOperation(entity, DBOperation.UPDATE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void serverClose() {
        try {
            syncQueuePool.shutDown(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void serverStart() {

    }
}
