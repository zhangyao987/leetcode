package com.zy.code;

import java.util.*;


public class TrueQuestion {

    public static void main(String[] args){
        /*
        360春招
        沫璃有一个画板，画板可以抽象成有100行每行100个像素点的正方形。沫璃在画板上画画，她一共画了n次，
        每次将一个矩形涂上颜色。沫璃想知道一共有多少个像素点被她涂过颜色。
        若一个像素点被涂了k次，那么认为有k个像素点被涂过颜色。
         */
        Scanner sc = new Scanner(System.in);
        int groudNum = Integer.parseInt(sc.nextLine());
        List<Integer> resList = new ArrayList<>();
        for (int i = 0; i<groudNum; i++) {
//            System.out.println("请输入第"+i+"组的数据");
            int zb = Integer.parseInt(sc.nextLine());
            List<Integer> zuList = new ArrayList<>();
            for (int a = 0; a<zb; a++) {
//                System.out.println("请输入4个整数");
                String zbList = sc.nextLine();
                String[] zbList1 = zbList.split(" ");
                int[] groud = new int[zbList1.length];
                for (int num = 0; num<zbList1.length; num++){
                    groud[num] = Integer.parseInt(zbList1[num]);
                }
                if (groud.length>=4){
                    zuList.add((groud[2]-groud[0]+1) *(groud[3]-groud[1]+1));
                }
            }
            int cur = 0;
            for (int zuListnum = 0; zuListnum<zuList.size(); zuListnum++) {
                cur+=zuList.get(zuListnum);
            }
            resList.add(cur);
        }

        for (int res = 0; res<resList.size(); res++){
            System.out.println(resList.get(res));
        }


/*
        360春招
        沫璃发起了一场交易，她将她的5个朋友聚在一起准备进行一场交易。交易开始前，大家各有b(b>0)个硬币，交易后，每个人有ai个硬币。
        由于硬币不方面携带，在交易过程中可能会丢失。现在沫璃想知道是否一定丢失硬币，或者在可能没有丢失硬币的情况下，交易前每个人的硬币数b。
        沫璃只是组织者，不参与交易。

        Scanner sc = new Scanner(System.in);
        int groudNum = Integer.parseInt(sc.nextLine());
        List<Integer> resList = new ArrayList<>();
        for (int i = 0; i<groudNum; i++) {
//            List<Integer> curList = new ArrayList<>();
            System.out.println("输入五个数");
            String fiveNum = sc.nextLine();
            String five[] = fiveNum.split(" ");
            int[] fiveList = new int[five.length];
            for (int a = 0; a<five.length; a++) {
                fiveList[a] = Integer.parseInt(five[a]);
            }
            int cur = 0;
            for (int c = 0; c<fiveList.length; c++){
                cur += fiveList[c];
            }
            if ((cur%5) == 0 && cur!=0) {
                resList.add(cur/5);
            }else {
                resList.add(-1);
            }
        }
        for (int num = 0; num<resList.size(); num++) {
            System.out.println(resList.get(num));
        }


        第二题，父序列，子序列， down其中一个判断有多少个会down
         private static int fun(int zinum,int [] fu,int[] zi){
        int c = 0;
        for (int i = 0;i<fu.length;i++){
            if (zinum == fu[i]){
                c += fun(zi[i],fu,zi)+1;
            }
        }
        return c;
    }
         public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String  strzi = sc.nextLine();
        String[] zi = strzi.split(" ");
        int[] zilist = new int[zi.length];
        for (int a = 0; a<zi.length; a++) {
            zilist[a] = Integer.parseInt(zi[a]);
        }
        String  strfu = sc.nextLine();
        String[] fu = strfu.split(" ");
        int[] fulist = new int[fu.length];
        for (int a = 0; a<fu.length; a++) {
            fulist[a] = Integer.parseInt(fu[a]);
        }
        int n = Integer.parseInt(sc.nextLine());
        int count = 0;
        for (int a = 0;a<zi.length;a++){
            if (zilist[a] == n){
                count = 1;
            }
        }
        for (int i = 0;i<zi.length;i++){
            if (zilist[i]==n){
                count+=fun(zilist[i],fulist,zilist);
            }

        }

        System.out.println(count);



    }

*/











/*

            沫璃邀请她的朋友参加周末的派对。沫璃买了3种颜色的气球，现在她要有这些气球来装饰餐桌，
            * 每个餐桌只用恰好3个气球装饰，要求3个气球的颜色不能完全一样，可以是2种或者3种颜色。
            * 沫璃想知道这些气球最多能装饰多少张餐桌。
            Arrays.sort(num);
            if(num[2]/2>=num[0]+num[1]){
                System.out.println(num[0]+num[1]);
            }else
                System.out.println((num[0]+num[1]+num[2])/3);
         */


//        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        List<Integer> reslist = new ArrayList<>();
        for (int i = 0; i<n; i++) {
            String num = sc.nextLine();
            String[] nums = num.split(" ");
            int[] list = new int[nums.length];
            for (int a = 0; a<nums.length; a++) {
                list[a] = Integer.parseInt(nums[a]);
            }
            int cur = 0;
            while ((list[0]+list[1]+list[2])>=3) {
                if (list[0]>0 && list[1]>0 && list[2]>0){
                    int min = 99999;
                    min = Math.min(list[0],list[1]);
                    min = Math.min(min,list[2]);
                    list[0] = list[0] - min;
                    list[1] = list[1] - min;
                    list[2] = list[2] - min;
                    cur = min;
                    continue;
                }
                if (list[0]<=0 && list[1]>0 && list[2]>0) {
                    list[1]--;
                    list[2]--;
                    if (list[1]>list[2]){
                        list[1]--;
                    }else {
                        list[2]--;
                    }
                    cur++;
                    continue;
                }
                if (list[1]<=0 && list[0]>0 && list[2]>0){
                    list[0]--;
                    list[2]--;
                    if (list[0]>list[2]){
                        list[0]--;
                    }else {
                        list[2]--;
                    }
                    cur++;
                    continue;
                }
                if (list[2]<=0 && list[0]>0 && list[1]>0) {
                    list[1]--;
                    list[0]--;
                    if (list[1]>list[0]){
                        list[1]--;
                    }else {
                        list[0]--;
                    }
                    cur++;
                    continue;
                }
            }
            reslist.add(cur);
        }
        for (int num = 0; num<reslist.size(); num++) {
            System.out.println(reslist.get(num));
        }









    }

}
