package com.zy.code;

public class ThreadMain {

}


//内存可见性问题  8-34  不会跳出循环  死循环，  在10加上volatile 才能跳出
class RunThread extends Thread{
    private boolean isrun = true;
    public void setIsrun(boolean isrun) {
        this.isrun = isrun;
    }
    @Override
    public void run(){
        System.out.println("进到run方法中");
        while (isrun){
        }
        System.out.println("线程执行完毕");
    }
}

class run{
    public static void main(String[] args) {
        try {
            RunThread thread = new RunThread();
            thread.start();
            Thread.sleep(100);
            thread.setIsrun(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


//volatile非原子性 i++并不是一个原子操作  事实并不能加到10000
// volatile修饰的变量并不保证对它的操作（自增）具有原子性。（对于自增操作，可以使用JAVA的原子类AutoicInteger类保证原子自增）
class MyThread extends Thread{
    private volatile static int count = 0;
    private static void add(){
        for (int i = 0;i<100;i++){
            count++;
        }
    }
    @Override
    public void run(){
        add();
    }
}

class run1{
    public static void main(String[] args) {
        MyThread[] threads = new MyThread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new MyThread();
        }
        for (int a = 0; a<100;a++){
            threads[a].start();
        }

    }
}

//一个死锁的简单例子
class Deadlocal implements Runnable{

    static Object o1 = new Object();
    static Object o2 = new Object();
    boolean fa = false;

    public Deadlocal (boolean fa){
        this.fa = fa;
    }

    @Override
    public void run() {
        if (fa){
            synchronized (o1){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println(" i am dead");
                }
            }
        }else {
            synchronized (o2){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println(" i am dead too");
                }
            }
        }
    }
}

class run3{
    public static void main(String[] args){
        Deadlocal d1 = new Deadlocal(true);
        Deadlocal d2 = new Deadlocal(false);
        Thread t1 = new Thread(d1);
        Thread t2 = new Thread(d2);
        t1.start();;
        t2.start();
    }
}

//双重验证代码实现
class Singleton{
    private volatile static  Singleton singleton;
    private Singleton(){}
    public static Singleton getSingleton(){
        if (singleton == null){
            synchronized (Singleton.class){
                if (singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}







