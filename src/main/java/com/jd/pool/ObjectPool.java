package com.jd.pool;

import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * Created by liushilang on 2017/9/5.
 */
public class ObjectPool {
    private GenericObjectPool pool;

    public ObjectPool(Class cls) {
        this.pool = new GenericObjectPool(new ObjectPoolFactory(cls));
        pool.setMaxActive(2);
        pool.setMaxIdle(1); //
        pool.setMaxWait(100000);
    }

    /**
     * 从池中取出对象
     * @param <T>
     * @return
     */
    public <T> T  borrowObject(){
        T obj = null;
        try{
            obj = (T)pool.borrowObject();
            System.out.println("获取对象");
        }catch (Exception e){
            System.out.println(e);
        }
        return obj;
    }

    public void returnObject(Object obj){
        try {
            pool.returnObject(obj);
            System.out.println("返还对象");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
