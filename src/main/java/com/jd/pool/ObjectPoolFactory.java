package com.jd.pool;

import org.apache.commons.pool.PoolableObjectFactory;

/**
 * Created by liushilang on 2017/9/5.
 */
public class ObjectPoolFactory implements PoolableObjectFactory {
    private Class cls;
    private static final String INIT_METHOD = "clearObject"; //有状态对象恢复初始化的方法

    public ObjectPoolFactory(Class cls){
        this.cls = cls;
    }
    public Object makeObject() throws Exception {
        System.out.println("创建新对象");
        return cls.newInstance();
    }

    public void destroyObject(Object o) throws Exception {

    }

    public boolean validateObject(Object o) {
        return false;
    }

    public void activateObject(Object o) throws Exception {
        System.out.println("有状态对象恢复初始化");
        try {
            cls.getDeclaredMethod(INIT_METHOD).invoke(o);
        }catch (Exception e){

        }
    }

    public void passivateObject(Object o) throws Exception {

    }
}
