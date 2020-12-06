package com.framework.orm;

import com.framework.async.entity.BaseEntity;
import com.framework.dao.BaseDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Transactional
public class MyDaoSupport<T> extends HibernateDaoSupport implements BaseDao<T> {

    private Class<T> clazz;

    @Autowired
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @SuppressWarnings({"unchecked,rawtypes"})
    public MyDaoSupport() {
        Type type = getClass().getGenericSuperclass();
        System.out.println(type.getTypeName());
        if(!ParameterizedType.class.isAssignableFrom(type.getClass())){
            System.out.println(type.getTypeName() + "需要继承泛型");
        }
        this.clazz =(Class)((ParameterizedType)type).getActualTypeArguments()[0];
    }

    @Override
    protected HibernateTemplate createHibernateTemplate(SessionFactory sessionFactory) {
        return super.createHibernateTemplate(sessionFactory);
    }

    @Override
    public boolean insert(Object t) {
        try{
            getHibernateTemplate().save(t);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Object t) {
        try{
        getHibernateTemplate().delete(t);
        return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<T> getALl() {
        List<T> all = getHibernateTemplate().loadAll(clazz);
        for(T t : all){
            BaseEntity entity = (BaseEntity) t;
            entity.serialize();  //这一步操作是干嘛的？
        }
        return all;
    }

    @Override
    public T getBy(long id) {
        T t = getHibernateTemplate().get(clazz, id);
        if(t != null){
            BaseEntity entity = (BaseEntity) t;
            entity.serialize();
        }
        return t;
    }

    @Override
    public boolean update(Object data) {
        try{
            getHibernateTemplate().update(data);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


}
