package taiji.test.controller;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class producterconsumern {

    static class  share{
        private volatile Boolean FLAG = true;
        private AtomicInteger atomicInteger = new AtomicInteger();
        private BlockingQueue<String>  blockingQueue= null;

        public share(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        public void producter(){
            String res = "";
            while (FLAG){
                res = atomicInteger.incrementAndGet()+"";
                try {
                    blockingQueue.offer(res,2, TimeUnit.SECONDS);
                    System.out.println(Thread.currentThread().getName()+"  生产了 "+res);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public void consumer(){
            String res = null;
            while (FLAG){
                try {
                    res = blockingQueue.poll(2,TimeUnit.SECONDS);
                    if (null == res){
                        System.out.println(Thread.currentThread().getName()+"消费失败！");
                        FLAG = false;
                        return;
                    }
                    System.out.println(Thread.currentThread().getName()+" 消费了  "+res);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public void stop(){
            FLAG = false;
        }

    }


    public static void main(String[] args) {
        share s = new share(new ArrayBlockingQueue<>(5));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"生产线程启动");
            s.producter();
        },"p1").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"生产线程启动");
            s.producter();
        },"p2").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"消费线程启动");
            s.consumer();
        },"c1").start();

        try {
            TimeUnit.SECONDS.sleep(5);
            s.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
