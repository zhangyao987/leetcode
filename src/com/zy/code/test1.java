package com.zy.code;

import java.util.concurrent.TimeUnit;

public class test1 implements Runnable{

    private static demo d1 = new demo();
    private static demo d2 = new demo();
    @Override
    public void run() {
        for (int i = 0; i<10;i++) {
        d1.t();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        d1.tt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
       test1 t = new test1();
       test1 t1 = new test1();
       new Thread(t).start();
       new Thread(t1).start();
    }


}

class demo{

    public  synchronized void t()  {
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("同步");
    }

    public void tt(){
        System.out.println("普通");
    }
}
