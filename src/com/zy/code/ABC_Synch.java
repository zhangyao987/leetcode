package com.zy.code;

public class ABC_Synch {


    static class jiou implements Runnable {
        Object ji;
        Object ou;
        int n;
        public jiou(Object ji, Object ou,int n) {
            this.ji = ji;
            this.ou = ou;
            this.n = n;
        }

        @Override
        public void run() {
            int count = 0;
            while (count < 20) {
                synchronized (ji) {
                    synchronized (ou) {
                        if (count%2==n)
                        System.out.println(count);
                        count++;
                        ou.notifyAll();
                    }
                    try {
                        ji.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static int count = 0;
    private static final Object o = new Object();
    static class jo implements Runnable{
        @Override
        public void run() {
            while (count<10){
                synchronized (o){
                    System.out.println(Thread.currentThread().getName()+count++);
                    o.notify();
                    if (count<=10){
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


        //三个线程循环打印
    static class ThreadPrinter implements Runnable{

        private String name;
        private Object prev;
        private Object self;

        private ThreadPrinter(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        @Override
        public void run() {
            int count = 10;
            while (count>0){
                synchronized (prev){
                    synchronized (self){
                        System.out.print(name);
                        count--;

                        self.notifyAll();
                    }
                    try {
                        if (count == 0){
                            prev.notifyAll();
                        }else {
                            prev.wait();
                        }
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }



    private static int fun(int x){
        int res = 0;
        while(x>0){
            int cur = x%10;
            res = res*10 + cur;
            x = x/10;
        }
        return res;
    }

    private static int test(int a){
        try {
            return a++;
        }finally {
            return a--;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(test(5));

//        new Thread(new jo(),"偶").start();
//        new Thread(new jo(),"奇").start();

        //奇偶数交替打印（自己解法）
//        Object ji = new Object();
//        Object ou = new Object();
//        jiou aa = new jiou(ji,ou,0);
//        jiou bb = new jiou(ou,ji,1);
//
//        new Thread(aa).start();
//        Thread.sleep(100);
//        new Thread(bb).start();
        //循环打印asc
//        Object a = new Object();
//        Object b = new Object();
//        Object c = new Object();
//        ThreadPrinter pa = new ThreadPrinter("A", c, a);
//        ThreadPrinter pb = new ThreadPrinter("B", a, b);
//        ThreadPrinter pc = new ThreadPrinter("C", b, c);
//
//        new Thread(pa).start();
//        Thread.sleep(10);// 保证初始ABC的启动顺序
//        new Thread(pb).start();
//        Thread.sleep(10);
//        new Thread(pc).start();
//        Thread.sleep(10);
    }

}
