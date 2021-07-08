package taiji.test.controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class share{

    private int nums = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increat(){
        lock.lock();
        while (nums != 0){
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nums++;
        System.out.println(Thread.currentThread().getName()+"   "+nums);
        condition.signalAll();
        lock.unlock();
    }
    public void decreat(){
        lock.lock();
        while (nums == 0){
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nums--;
        System.out.println(Thread.currentThread().getName()+"   "+nums);
        condition.signalAll();
        lock.unlock();
    }

}

class share1{
    private volatile Boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);

    public void prod(){
        String res = "";
        while (FLAG){
            res = atomicInteger.incrementAndGet()+"";
            try {
                blockingQueue.offer(res,2, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName()+" "+res+" 插入队列成功");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+" ================stop");
    }
    public void consumer(){
        String poll = null;
        while (FLAG){
            try {
                poll = blockingQueue.poll(3, TimeUnit.SECONDS);
                if (poll == null){
                    System.out.println("消费失败！退出！");
                    FLAG = false;
                    return;
                }
                System.out.println(Thread.currentThread().getName()+" "+poll+ "   消费成功");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void stop(){
        FLAG = false;
    }


}

public class producter {
    public static void main(String[] args) {
        share1 share1 = new share1();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"生产线程启动");
            share1.prod();
        },"AA").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"生产线程启动");
            share1.prod();
        },"CC").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" 消费线程启动");
            share1.consumer();
        },"BB").start();

        try {
            TimeUnit.SECONDS.sleep(10);
            share1.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        share share = new share();
//        new Thread(()->{
//            for (int i = 0;i<5;i++){
//                share.increat();
//            }
//        },"AA").start();
//        new Thread(()->{
//            for (int i = 0;i<5;i++){
//                share.decreat();
//            }
//        },"BB").start();
    }


}
