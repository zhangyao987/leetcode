package com.zy.code;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class ShareData{
    public static AtomicInteger atomicInteger = new AtomicInteger();
    public volatile Boolean flag = true;
    public static final int count = 10;
    public static final List<Integer> pool = new ArrayList<>();

    public void producer(){
        while (flag){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (pool){
                while (pool.size() == count){
                    System.out.println("pool is full, wating...");
                    try {
                        pool.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                pool.add(atomicInteger.incrementAndGet());
                System.out.println("produce number:" + atomicInteger.get() + "\t" + "current size:" + pool.size());
                pool.notifyAll();
            }
        }

    }

    public void consumer(){
        while (flag){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (pool){
                while (pool.size() == 0){
                    System.out.println("pool is empty, wating...");
                    try {
                        pool.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int temp = pool.get(0);
                pool.remove(0);
                System.out.println("cousume number:" + temp + "\t" + "current size:" + pool.size());
                //通知
                pool.notifyAll();
            }


        }


    }

    public void stop() {
        flag = false;
    }
}
public class producerConsumer {



    public static void main(String[] args) {
        ShareData shareDataV1 = new ShareData();
        new Thread(() -> {
            shareDataV1.producer();
        }, "AAA").start();

        new Thread(() -> {
            shareDataV1.consumer();
        }, "BBB").start();

        new Thread(() -> {
            shareDataV1.producer();
        }, "CCC").start();

        new Thread(() -> {
            shareDataV1.consumer();
        }, "DDD").start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shareDataV1.stop();
    }

}
