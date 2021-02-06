package com.zy.code;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Lazy {
//    private static volatile Lazy instance = null;
//    private Lazy(){};
//    public static Lazy getInstance() {
//        if (instance == null) {
//            synchronized(Lazy.class){
//                if (instance == null) instance = new Lazy();
//            }
//        }
//        return instance;
//    }
    public static void main(String[] args) {
       int a = 1;
       Integer b = 1;
       Integer c = new Integer(1);
        ArrayList<Integer> list = new ArrayList<>();


       System.out.println(b.equals(a));
        System.out.println(a==c);
        System.out.println(c==b);
    }

}




/*
*
* public class DeadLock implements Runnable {
    public int flag = 1;
    //静态对象是类的所有对象共享的
    private static Object o1 = new Object();
    private static Object o2 = new Object();

    @Override
    public void run() {
        System.out.println("flag=" + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("1");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("0");
                }
            }
        }
    }

    public static void main(String[] args) {

        DeadLock td1 = new DeadLock();
        DeadLock td2 = new DeadLock();
        td1.flag = 1;
        td2.flag = 0;
        //td1,td2都处于可执行状态，但JVM线程调度先执行哪个线程是不确定的。
        //td2的run()可能在td1的run()之前运行
        new Thread(td1).start();
        new Thread(td2).start();

    }
}
* */
