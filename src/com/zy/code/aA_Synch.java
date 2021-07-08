package com.zy.code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class aA_Synch {

    synchronized void aVoid(){
        String abc = "abcdefghijklmnopqrstuvwxyz";
        String[] strings= abc.split("");
        for (String a : strings){
            System.out.println(a);
            this.notify();
            try {
                this.wait();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized void big(){
        String abc = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
        String[] strings= abc.split("");
        for (String a : strings){
            System.out.println(a);
            this.notify();
            try {
                this.wait();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void primeNumberTest() {
        int res = 0;
        for (int i = 2; i <= 100; i++) {
            boolean flag = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.print(i + ",");
                res +=jiecheng(i);
            }
        }
        System.out.println(res);
    }
    private int jiecheng(int a){
        int sum = 1;
        for (int i = a;i>0;i--){
            sum=sum*i;
        }
        return sum;
    }
    public static void main(String[] args) {
        aA_Synch aA_synch = new aA_Synch();
        Thread t1 = new Thread(aA_synch::aVoid);
        Thread t2 = new Thread(aA_synch::big);
        t1.start();
        t2.start();
//        aA_synch.primeNumberTest();
    }

}


