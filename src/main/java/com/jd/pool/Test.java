package com.jd.pool;

/**
 * Created by liushilang on 2017/9/5.
 */
public class Test {
    public static ObjectPool pool = new ObjectPool(Object.class);

    public static void main(String[] args) {
        for (int i = 0; i < 2000; i++) {
            new Thread() {
                @Override
                public void run() {
                    Object obj = Test.pool.borrowObject();
                    System.out.println(obj.toString());
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {

                    }
                    Test.pool.returnObject(obj);
                }
            }.start();
        }
    }
}
