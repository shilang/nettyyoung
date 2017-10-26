package com.jd.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by liushilang on 2017/10/23.
 */
public class UnsafeTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {

        Player p = (Player) getUnsafe().allocateInstance(Player.class);
        System.out.println(p.getAge());
        p.setAge(45);
        System.out.println(p.getAge());

        //隐藏密码做法
        String password = new String("100k@myhor$e");
        String fake = new String(password.replaceAll(".", "?"));
        System.out.println(password);
        System.out.println(fake);
        getUnsafe().copyMemory(fake, 0l, null, getUnsafe().oAddress(password), getUnsafe.sizeOf(password));
    }

    private static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
    }

    class Player {
        private int age = 12;

        private Player() {
            this.age = 50;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
