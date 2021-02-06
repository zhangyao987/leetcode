package com.zy.code;


import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main {



    //niuke1
    public boolean Find(int target, int [][] array) {
        int h = array.length;
        int l = array[0].length;
        for (int i = 0; i<h;i++) {
            if (l == 0) return false;
            if (array[i][l-1] == target) return true;
            if (array[i][l-1] > target){
                for (int a = l-1;a>=0;a--) {
                    if (target == array[i][a]) return true;
                }
            }
        }
        return false;
    }

    //niuke2
    public String replaceSpace(StringBuffer str) {
        if (str == null) return "";
        StringBuffer res = new StringBuffer();
        for (int i = 0;i<str.length();i++){
            if (str.charAt(i) == ' '){
                res.append("%");
            }else {
                res.append(str.charAt(i));
            }
        }
        return res.toString();
    }


     public class ListNode {
         int val;
         ListNode next = null;

         ListNode(int val) {
             this.val = val;
         }
     }

    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }


    //niuke3
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList();
        if (listNode == null) return list;
        while (listNode != null){
            list.add(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> reslist = new ArrayList<>();
        for (int i = list.size()-1;i>=0;i--) {
            reslist.add(list.get(i));
        }
        return reslist;
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }


    //niuke4
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        TreeNode root =reConstructBinaryTree(pre,0,pre.length-1, in, 0,in.length-1);
        return root;
    }
    private TreeNode reConstructBinaryTree(int [] pre,int startpre , int endpre,int [] in,int startin, int endin){
        if (startpre>endpre || startin>endin){
            return null;
        }
        TreeNode root = new TreeNode(pre[startpre]);
        for (int i = startin ;i<=endin;i++){
            if (in[i] == pre[startpre]){
                root.left = reConstructBinaryTree(pre,startpre+1,startpre+i-startin,in,startin,i-1);
                root.right = reConstructBinaryTree(pre,i-startin+startpre+1,endpre,in,i+1,endin);
            }
        }
        return root;
    }

    //niuke5
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }else {
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
    }

    //niuke6
    public int minNumberInRotateArray(int [] array) {
        if (array == null) return 0;
        int res = -1;
        for (int i = 0;i<array.length;i++){
            if (array[i]<array[0]){
                res =  array[i];
                return res;
            }
        }
        return 0;
    }

    //niuke 7
    public int Fibonacci(int n) {
        if (n<=0) return 0;
        if (n>0 && n<=2) return 1;
        return Fibonacci(n-1)+Fibonacci(n-2);
    }

    //niuke8
    public int JumpFloor(int target) {
        if (target<=0) return 0;
        if (target>0 && target<=1) return 1;
        if (target>1 && target<=2) return 2;
        return JumpFloor(target-1)+JumpFloor(target-2);
    }

    //niuke9
    public int JumpFloorII(int target) {
        if(target==0)
            return 1;
        if(target==1)
            return 1;
        return 2*JumpFloorII(target-1);
    }

    //niuke10
    public int RectCover(int target) {
        if(target==0)
            return 0;
        if(target==1)
            return 1;
        if (target == 2) return 2;
        return RectCover(target-1)+RectCover(target-2);
    }

   //niuke13
   public void reOrderArray(int [] array) {
       for(int i= 0;i<array.length-1;i++){
           for(int j=0;j<array.length-1-i;j++){
               if(array[j]%2==0&&array[j+1]%2==1){
                   System.out.println(array[j]+"---"+array[j+1]);
                   int t = array[j];
                   array[j]=array[j+1];
                   array[j+1]=t;
               }
           }
       }
//       List<Integer> ji = new ArrayList<>();
//       List<Integer> ou = new ArrayList<>();
//       for (int i = 0;i<array.length;i++){
//           if (array[i]%2==0){
//               ou.add(array[i]);
//           } else {
//               ji.add(array[i]);
//           }
//       }
//       for (int i = 0;i<ji.size();i++) {
//           System.out.print(ji.get(i));
//       }
//       for (int i = 0;i<ou.size();i++){
//           System.out.print(ou.get(i));
//       }
   }







    //niuke40
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0;i<array.length;i++) {
            if(map.containsKey(array[i])) {
                map.put(array[i], map.get(array[i])+1);
            }else {
                map.put(array[i], 1);
            }
        }
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer integer = (Integer) iterator.next();
            if(map.get(integer)==1&&num1[0]==0) {
                num1[0] = integer;
                System.out.println(num1[0]);
            }
            else if(map.get(integer)==1&&num1[0]!=0){
                num2[0] = integer;
                System.out.println("num2"+num2[0]);
            }
        }
    }


//    niuke 链表中环的入口
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode low = pHead;
        ListNode fast = pHead;
        while (low != null && fast != null && fast.next != null) {
            low = low.next;
            fast = fast.next.next;
            if (low == fast) break;
        }
        if (fast == null || fast.next == null) return null;
        low = pHead;
        while (low != fast) {
            low = low.next;
            fast = fast.next;
        }
        return low;
    }


    //niuke 删除链表中重复的节点
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next ==null) return pHead;
        if (pHead.val == pHead.next.val) {
            ListNode node = pHead.next;
            while (node.next != null && node.next.val == pHead.val) {
                node = node.next;
            }
            return deleteDuplication(node.next);
        }else {
            pHead.next = deleteDuplication(pHead.next);
            return pHead;
        }
    }

    //niuke 二叉树的下一个节点
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) return null;
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }
        while (pNode.next != null) {
            if (pNode.next.left == pNode) return pNode.next;
            pNode = pNode.next;
        }
        return null;
    }

    //niuke 对称二叉树
    public boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) return true;
        return isSymmetrical(pRoot.left,pRoot.right);
    }
    private boolean isSymmetrical(TreeNode left, TreeNode right) {
        if (left == null) return  right == null;
        if (right == null) return false;
        if (left.val!= right.val) return false;
        return isSymmetrical(left.left,right.right) && isSymmetrical(left.right,right.left);
    }

    //niuke 之字打印二叉树
    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer> > reslist = new ArrayList<>();
        if (pRoot == null) return reslist;
        Stack<TreeNode> s1 = new Stack<>();
        s1.push(pRoot);
        Stack<TreeNode> s2 = new Stack<>();
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (!s1.isEmpty()) {
                ArrayList<Integer> list = new ArrayList<>();
                while (!s1.isEmpty()) {
                    TreeNode node = s1.pop();
                    if (node.left != null) s2.push(node.left);
                    if (node.right != null) s2.push(node.right);
                    list.add(node.val);
                }
                reslist.add(list);
            }else {
                ArrayList<Integer> list1 = new ArrayList<>();
                while (!s2.isEmpty()) {
                    TreeNode node = s2.pop();
                    if (node.right != null) s1.push(node.right);
                    if (node.left != null) s1.push(node.left);
                    list1.add(node.val);
                }
                reslist.add(list1);
            }
        }
        return reslist;
    }

    //niuke 多行打印
    public ArrayList<ArrayList<Integer> > Print1(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;
        depth(pRoot,1,res);
        return res;
    }
    private void depth(TreeNode node, int depth, ArrayList<ArrayList<Integer>> res){
        if (node == null) return;
        if (depth > res.size()) res.add(new ArrayList<>());
        res.get(depth-1).add(node.val);
        depth(node.left,depth+1,res);
        depth(node.right,depth+1,res);
    }
    //niuke 二分搜索树中第K小的数
    public TreeNode KthNode(TreeNode pRoot, int k) {
        List<TreeNode> res = new ArrayList<>();
        if(pRoot == null || k<1) return null;
        KthNode(pRoot,res);
        if (k> res.size()) return null;
        return res.get(k);
    }
    private void KthNode(TreeNode node, List<TreeNode> res) {
        if (node.left != null) KthNode(node.left,res);
        res.add(node);
        if (node.right != null) KthNode(node.right,res);

    }
    //niuke 滑动窗口的最大值
    public ArrayList<Integer> maxInWindows(int [] num, int size){
        if (num == null || num.length == 0 || size <= 0 || num.length < size) {
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> res = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i<num.length;i++) {
            while (!linkedList.isEmpty() && num[linkedList.peekLast()] < num[i]){
                linkedList.pollLast();
            }
            linkedList.add(i);
            if (linkedList.peekFirst() == i-size) linkedList.pollFirst();
            if (i >= size-1) res.add(num[linkedList.peekFirst()]);
        }

        return res;

    }

    //niuke机器人运动范围
//    public int movingCount(int threshold, int rows, int cols) {
//
//
//
//    }


    //leetcode 两个数的和
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0;i<nums.length-1; i++) {
            for (int j = i+1; j<nums.length; j++) {
                if (nums[i]+nums[j] == target){
                    res[0]=i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
        //超时
        //        Arrays.sort(nums);
//        int[] res = new int[2];
//        int l = 0,r = nums.length-1;
//        while (l<r) {
//            if (nums[l]+nums[r] == target) {
//                res[0] = l;
//                res[1] = r;
//            }
//            if (nums[l]+nums[r] >target) r--;
//            if (nums[l]+nums[r] <target) l++;
//        }
//        return res;
    }



    //leetcode5最长回文子串
    public String longestPalindrome(String s) {

        int len = s.length();
        if (len <= 1) return s;
        int[][] db = new int[len][len];
        char[] chars = s.toCharArray();
        for (int i = 0; i<len; i++) {
            db[i][i] = 1;
        }
        int start = 0, max = 1;
        for (int j = 0;j<len;j++) {
            for (int i = j-1;i>=0; i--) {
                if (chars[i] == chars[j]) {
                    if (j-i==1){
                        db[i][j] =2;
                    } else {
                        if (db[i+1][j-1] > 0) {
                            db[i][j] = db[i+1][j-1] + 2;
                        }else {
                            db[i][j] = 0;
                        }
                    }
                } else {
                    db[i][j] = 0;
                }
                if (db[i][j]>max) {
                    start = i;
                    max = db[i][j];
                }
            }
        }
        System.out.println(s.substring(start,max));
        return s.substring(start,start+max);
    }

    //leetcodeZ字形变换
    public String convert(String s, int numRows) {
        if(numRows < 2) return s;
        int len = s.length();
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i<numRows; i++) {
            list.add(new StringBuilder());
        }
        int i = 0 , cur = -1;
        for (char c : s.toCharArray()) {
            list.get(i).append(c);
            if (i == 0 || i == numRows-1) cur = -cur;
            i += cur;
        }
        StringBuilder res = new StringBuilder();
        for (int a = 0; a<list.size(); a++) {
            res.append(list.get(a));
        }
        return res.toString();
    }

    //leetcode7整数反转
    public int reverse(int x) {
        int res = 0;
        while (x != 0){
            int cur = x % 10;
            if (res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE/10 && cur > 7)) return 0;
            if (res < Integer.MIN_VALUE/10 || (res == Integer.MIN_VALUE/10&& cur <-8)) return 0;
            res = res*10 + cur;
            x = x /10;
        }
        return res;
    }


    //leetcode9 回文数
    public boolean isPalindrome(int x) {
        if (x<0) return false;
        int res = 0;
        int cur = x;
        while (x != 0) {
            int pop = x%10;
            if (res > Integer.MAX_VALUE/10 || res == Integer.MAX_VALUE/10 && pop >7 ) return false;
            if (res < Integer.MIN_VALUE/10 || res == Integer.MIN_VALUE/10 && pop <-8) return false;
            res = res *10 +pop;
            x = x/10;
        }
        if (cur == res) return true;
        return false;
    }



    //leetcode15 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        if (nums.length<3) return resList;
        Arrays.sort(nums);
        for (int i = 0;i<nums.length-2;i++){
            if (i>0&&nums[i]==nums[i-1]) continue;
            int min = nums[i]+nums[i+1]+nums[i+2];
            int max = nums[nums.length-1]+nums[nums.length-2]+nums[nums.length-3];
            if (max<0) break;
            if (min>0) continue;
            int a = i+1, b=nums.length-1;
            while (a<b){
                int cur = nums[i]+nums[a]+nums[b];
                if (cur == 0){
                    resList.add(Arrays.asList(nums[i],nums[a],nums[b]));
                    a++;
                    while (a<b&&nums[a]==nums[a-1]){
                        a++;
                    }
                    b--;
                    while (a<b&&nums[b]==nums[b+1]){
                        b--;
                    }
                }else if (cur>0){
                    b--;
                }else {
                    a++;
                }
            }
        }
        return resList;
    }

    //leetcode16
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        //nums[0] + nums[1]+ nums[2];
        int res = 1000000;//不能为Integer.MAX_VALUE 不然会存在越界
        for (int i = 0; i<nums.length-2; i++) {
            int l = i+1;
            int r = nums.length-1;
            while (l<r) {
                int cur = nums[i] + nums[l]+ nums[r];
                // -1 -1 1 1 3
                if (Math.abs(cur - target) < Math.abs(res-target)){
                    res = cur;
                }
                if (cur<target){
                    l++;
                }else if (cur>target) {
                    r--;
                }else {
                    return target;
                }
            }
        }
        return res;
    }


    //leetcode18
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> resList = new ArrayList<>();
        if (nums.length<4) return resList;
        Arrays.sort(nums);
        for (int k = 0;k<nums.length-3;k++){
            if (k>0&&nums[k]==nums[k-1]) continue;
            int kmin = nums[k]+nums[k+1]+nums[k+2]+nums[k+3];
            if (kmin>target) continue;
            int kmax = nums[nums.length-4]+nums[nums.length-1]+nums[nums.length-2]+nums[nums.length-3];
            if (kmax<target) break;
            for (int i = k+1;i<nums.length-2;i++){
                if (i>k+1&&nums[i]==nums[i-1]) continue;
                int imin = nums[k]+nums[i]+nums[i+1]+nums[i+2];
                int imax = nums[k]+nums[i]+nums[nums.length-1]+nums[nums.length-2];
                if (imin>target) continue;
                if (imax<target) continue;
                int j = i+1;
                int h = nums.length-1;
                while (j<h){
                    int cur = nums[k]+nums[i]+nums[j]+nums[h];
                    if (cur == target){
                        resList.add(Arrays.asList(nums[k],nums[i],nums[j],nums[h]));
                        j++;
                        while (j<h&&nums[j]==nums[j-1]){
                            j++;
                        }
                        h--;
                        while (j<h&&nums[h]==nums[h+1]){
                            h--;
                        }
                    }else if (cur>target){
                        h--;
                    }else {
                        j++;
                    }

                }

            }
        }
        return resList;
    }

    //leetcode22 括号生成 mid
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesis(n,res,new String(),0,0);
        return res;

    }
    private void generateParenthesis(int n, List<String> res, String s,int open,int close){
        if (s.length() == 2*n) {
            res.add(s);
            return;
        }else {
            if (open<n) {
                s += "(";
                generateParenthesis(n,res,s,open+1,close);
                s = s.substring(0,s.length()-1);
            }
            if (close<open){
                s += ")";
                generateParenthesis(n,res, s,open,close+1);
                s = s.substring(0,s.length()-1);
            }
        }
    }

    //leetcode 23 合并k个排序链表
    public ListNode mergeKLists(ListNode[] lists) {

        int len = lists.length;
        if (len == 0) return null;
        while (len > 1) {
            for (int i = 0 ; i<len/2; i++) {
                lists[i] = mergeKLists(lists[i],lists[len-i-1]);
            }
            len = (len+1) / 2;
        }
        return lists[0];

    }
    private ListNode mergeKLists(ListNode l1 , ListNode l2) {
        ListNode head  = new ListNode(-1);
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            }else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        } else if (l2 != null) {
            cur.next = l2;
        }
        return head.next;
    }

    //leetcode29 两数相除
    public int divide(int dividend, int divisor) {
        int ans = -1;
        int sign = 1;
        if (dividend > 0) {
            sign = opposite(sign);
            dividend = opposite(dividend);
        }
        if (divisor > 0) {
            sign = opposite(sign);
            divisor = opposite(divisor);
        }

        int origin_dividend = dividend;
        int origin_divisor = divisor;
        if (dividend > divisor) {
            return 0;
        }

        dividend -= divisor;
        while (divisor >= dividend) {
            ans = ans + ans;
            divisor += divisor;
            dividend -= divisor;
        }
        //此时我们传进的是两个负数，正常情况下，它就返回正数，但我们是在用负数累加，所以要取相反数
        int a = ans + opposite(divide(origin_dividend - divisor, origin_divisor));
        if(a == Integer.MIN_VALUE){
            if( sign > 0){
                return Integer.MAX_VALUE;
            }else{
                return Integer.MIN_VALUE;
            }
        }else{
            if(sign > 0){
                return opposite(a);
            }else{
                return a;
            }
        }
    }
    public int opposite(int x) {
        return ~x + 1;
    }

    //leetcode33 搜索旋转数组 时间复杂度logn
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int start = 0;
        int end = nums.length-1;
        int mid;
        while(start<=end){
            mid = start + (end-start)/2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[start]<=nums[mid]){//前面部分是有序的
                if(nums[start]<=target && target<nums[mid]) {
                    end = mid -1;
                }else{
                    start = mid +1;
                }
            }else {//后半部分是有序的
                if(nums[mid]<target && nums[end] >= target) {
                    start = mid+1;
                }else{
                    end = mid -1;
                }
            }
        }
        return -1;



    }


    //leetcode42接雨水
    public int trap(int[] height) {
        if (height.length<3) return 0;
        int left=0,right = height.length-1;
        int res = 0;
        int lmax = height[0];
        int rmax = height[height.length-1];
        while (left < right) {
            lmax = Math.max(lmax,height[left]);
            rmax = Math.max(rmax,height[right]);
            if (lmax < rmax) {
                res += lmax-height[left];
                left++;
            } else {
                res += rmax-height[right];
                right--;
            }
        }

        return res;
    }


    //leetcode53最大子序和
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        for (int i = 1; i<nums.length; i++) {
            if (nums[i-1]>0){
                nums[i] += nums[i-1];
            }
            res = Math.max(res,nums[i]);
        }
        return res;
    }

    //leetcode62不同路径
    public int uniquePaths(int m, int n) {
//        //递归实现 超出时间限制
//        if (m == 1 && n == 1) return 1;
//        if (m == 1 && n != 1) return uniquePaths(m,n-1);
//        if (m != 1 && n == 1) return uniquePaths(m-1,1);
//        return uniquePaths(m-1,n)+uniquePaths(m,n-1);

        //动态规划
        int[][] dp = new int[m][n];
        for (int i = 0; i<m; i++) dp[i][0] = 1;
        for (int i = 0; i<n; i++) dp[0][i] = 1;
        for (int i = 1; i<m; i++) {
            for (int j = 1; j<n;j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    //leetcode63不同路径带路障
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int l = obstacleGrid.length;
        int h = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1){
            return 0;
        }
        obstacleGrid[0][0] = 1;
        for (int i = 1; i<l; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i-1][0] == 1) ? 1:0;
        }
        for (int i= 1 ;i<h; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i-1] == 1) ? 1:0;
        }
        for (int i = 1; i<l; i++) {
            for (int j = 1; j<h; j++) {
                if (obstacleGrid[i][j] == 0){
                    obstacleGrid[i][j] = obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
                }else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }
        return obstacleGrid[l-1][h-1];
    }


    //leetcode39组合总和
    List<List<Integer>> list39 = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> list = new ArrayList<>();
        if (candidates == null || candidates.length == 0 || target < 0) {
            return list39;
        }
        process(0,candidates,target,list);
        return list39;
    }

    private void process(int start , int[] candidates, int target, List<Integer> list){
        if (target<0) {
            return;
        }
        if (target == 0) {
            list39.add(new ArrayList<>(list));
        }else {
            for (int i = start;i<candidates.length;i++) {
                list.add(candidates[i]);
                process(i,candidates,target-candidates[i],list);
                list.remove(list.size()-1);
            }
        }
    }

    //leetcode46 全排列
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list46 = new ArrayList<>();
        if (nums.length <= 0) return list46;
        List<Integer> list = new ArrayList<>();
        permute(list46,nums,list);
        return list46;
    }

    private void permute(List<List<Integer>> list46, int[] nums, List<Integer> list){
        if (list.size() == nums.length) {
            list46.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i<nums.length; i++) {
            if (list.contains(nums[i])) continue;
            list.add(nums[i]);
            permute(list46,nums, list);
            list.remove(list.size()-1);
        }

    }

    //leetcode77组合
    List<List<Integer>> list77 = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        if (n<=0 || k<=0 || k>n){
            return list77;
        }
        List<Integer> list = new ArrayList<>();
        process77(1,n,k,list);
        return list77;
    }
    private void process77(int start,int n, int k, List<Integer> list){
        if (list.size() == k) {
            list77.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i<=n;i++) {
            list.add(i);
            process77(i+1,n,k,list);
            list.remove(list.size()-1);
        }
    }

//    leetcode78 子集
    List<List<Integer>> list78 = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
    if(nums == null || nums.length ==0){
        return list78;
    }
    List<Integer> list = new ArrayList<>();
    process78(0,nums,list);
    return list78;
    }

    private void process78(int start,int[] nums, List<Integer> list){
        list78.add(new ArrayList<>(list));
        for (int i = start; i<nums.length; i++) {
            list.add(nums[i]);
            process78(i+1,nums,list);
            list.remove(list.size()-1);
        }
    }

    //leetcode94 二叉树的中序遍历 mid
    List<Integer> list94 = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        inorderTraversal(root.left);
        list94.add(root.val);
        inorderTraversal(root.right);
        return list94;
    }

    //leetcode118杨辉三角 easy
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows < 1) return res;
        res.add(new ArrayList<>());
        res.get(0).add(1);
        for (int row = 1;row<numRows; row++) {
            List<Integer> list = new ArrayList<>();
            List<Integer> list1 = res.get(row-1);
            list.add(1);
            for (int i= 1; i<row; i++) {
                list.add(list1.get(i-1)+list1.get(i));
            }
            list.add(1);
            res.add(list);
        }
        return res;
    }



    //leetcoede 128 最长连续序列
    //暴力解法
    public int longestConsecutive(int[] nums) {
        int res = 0;
        for (int i = 0; i<nums.length; i++) {
            int target = nums[i];
            int curres = 1;
            while (longestConsecutive(nums,target+1)) {
                curres+=1;
                target+=1;
            }
            res = Math.max(res,curres);
        }
        return res;
    }
    private boolean longestConsecutive(int[] nums, int  target) {

        for (int i = 0; i<nums.length; i++) {
            if (nums[i] == target) return true;
        }
        return false;
    }
    //O(n) 解法
    public int longestConsecutive1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            set.add(num);
        }
        int res = 0;
        for (int i = 0; i<nums.length; i++) {
            int curNum = nums[i];
            int curres = 1;
            while (set.contains(curNum+1)) {
                curNum++;
                curres++;
            }
            res = Math.max(res,curres);
        }
        return res;

    }


    //leetcode 171 excel 表序列号  easy
    public int titleToNumber(String s) {
        int res = 0;
        for (int i = 0; i<s.length(); i++ ) {
            int nums = s.charAt(i) - 'A' +1;
            res = res * 26 + nums;
        }
        return res;
    }


    //leetcode239 滑动窗口的最大值 heard
    public int[] maxSlidingWindow(int[] nums, int k) {

        int[] res = new int[nums.length-k+1];
        if (nums.length<1 ) return new int[0];
        maxSlidingWindow(nums,k,res,0);
        return res;

    }
    private void maxSlidingWindow(int[] nums, int k, int[] res, int i){
        while (i < res.length) {
            int curmax = Integer.MIN_VALUE;
            for (int c = i; c<k+i; c++) {
                curmax = Math.max(curmax,nums[c]);
            }
            res[i] = curmax;
            i++;
        }
    }


    //leetcode344 反转字符串  easy
    public void reverseString(char[] s) {
        int len = s.length;
        for (int i = 0; i<len/2;i++) {
            swap(i,len-i-1,s);
        }

    }
    private void swap (int a, int b, char[] chars) {
        char cur = chars[a];
        chars[a] = chars[b];
        chars[b] = cur;
    }



    //leetcode 455
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int gs = g.length-1;
        int ss = s.length-1;
        int res = 0;
        while (gs>=0 && ss>=0) {
            if (s[ss] >= g[gs]){
                gs--;
                ss--;
                res++;
            }else {
                gs--;
            }
        }
        return res;
    }

    //leetcode55
    public boolean canJump(int[] nums) {
        if (nums.length<1) return true;
        int max = nums[0];
        for (int i = 1;i<nums.length;i++){
            if (i<=max){
                max = Math.max(max,nums[i]+i);
            }
        }
        return max>=nums.length-1;
    }

    //leetcode100相同的二叉树
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null ) return false;
        if (p.val != q.val) return false;
        if (isSameTree(p.left,q.left) != true) return false;
        if (isSameTree(p.right,q.right) != true) return false;
        return true;
    }
    //leetcode226 翻转二叉树
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;

    }
    //leetcode101对称二叉树
    public boolean isSymmetric(TreeNode root) {
        if (root.left==null && root.right == null) return true;
        if (root.left ==null || root.right == null) return false;
        TreeNode cur = invertTree(root.left);
        return isSameTree(cur,root.right);
    }
    //leetcode102二叉树的层序遍历（非递归实现）
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        int level = 0;
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            res.add(new ArrayList<>());
            int size = queue.size();
            for (int i = 0 ; i<size;i++) {
                res.get(level).add(queue.poll().val);
                if (root.left != null) queue.add(root.left);
                if (root.right != null) queue.add(root.right);
            }
            level++;
        }
        return res;
    }
    //leetcode102二叉树的层序遍历 (递归实现)
    List<List<Integer>> levels = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return levels;
        levelOrder(root,0);
        return levels;
    }
    private void levelOrder(TreeNode root, int level) {
        if (levels.size() == level) levels.add(new ArrayList<>());
        levels.get(level).add(root.val);
        if (root.left != null) levelOrder(root.left,level+1);
        if (root.right != null) levelOrder(root.right, level+1);

    }

    //leetcode104二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }

    //leetcode 108将有序数组转换成二分搜索树
    int[] nums108;
    public TreeNode helper(int left, int right) {
        if (left > right) return null;
        int p = (left + right) / 2;
        TreeNode root = new TreeNode(nums108[p]);
        root.left = helper(left, p - 1);
        root.right = helper(p + 1, right);
        return root;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums108 = nums;
        return helper(0, nums.length - 1);
    }

    //leetcode113 mid 二叉树路径总和
    List<List<Integer>> list113 = new ArrayList<>();
    List<Integer> list113r = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return list113;
        list113r.add(root.val);
        sum-=root.val;
        if (root.left == null && root.right == null && sum == 0) list113.add(new ArrayList<>(list113r));
        if (root.left != null) pathSum(root.left,sum);
        if (root.right != null) pathSum(root.right,sum);
        list113r.remove(list113r.size()-1);

        return list113;

    }


//        int ret = 1;
//        for (int i = 1; i < n - 1; i++)
//        {
//            //以中间元素和左右两个元素大小作比较，因此i取值最大是n-2
//            if ((arr[i - 1]<=arr[i] && arr[i]>=arr[i + 1]) || (arr[i - 1] >= arr[i] && arr[i] <= arr[i + 1]))
//            {
//                ret++;//每进来一次说明有一个波峰/波谷
//                //正常情况下，i-1,i,i+1位置都判断过来了，应该判断i+2位置了，
//                //此时是i的位置，则需要i加1一次，循环上去i再加一次，就是i+2了，
//                //但是由于i的最大取值是i-2,因此当i==n-3时，如果i加1一次是n-2,
//                //循环上去再加一次1就是n-1,越界了，因此当i==n-3时无需再加1
//                if (i != n - 3)
//                {
//                    i++;
//                }
//            }
//        }


    //leetcode 122 easy   峰谷法
    public int maxProfit(int[] prices) {
        if (prices.length<2) return 0;
        int ru = prices[0];
        int chu = prices[0];
        int res = 0;
        int l = 0;
        while (l<prices.length-1) {
            while (l<prices.length-1 && prices[l]>=prices[l+1])
                l++;
            ru = prices[l];
            while (l<prices.length-1 && prices[l]<=prices[l+1])
                l++;
            chu = prices[l];
            res+= (chu-ru);
        }
        return res;

    }




    //leetcode135分发糖果
    public int candy(int[] ratings) {
        int[] res = new int[ratings.length];
        for (int i = 0; i<res.length; i++) res[i] = 1;
        //左遍历
        for (int i = 1; i<ratings.length;i++) {
            if (ratings[i]>ratings[i-1]) res[i] = Math.max(res[i],res[i-1]+1);
        }
        //右遍历
        for (int i = ratings.length-2; i>=0; i--) {
            if (ratings[i]>ratings[i+1]) res[i] = Math.max(res[i],res[i+1]+1);
        }
        int result = 0;
        for (int i = 0; i<res.length; i++) {
            result += res[i];
        }
        return result;
    }

    //leetcode230二叉搜索树中第K小的元素 mid
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = kthSmallest(root);
        return list.get(k-1);
    }
    List<Integer> list230 = new ArrayList<>();
    private List<Integer> kthSmallest(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        kthSmallest(root.left);
        list230.add(root.val);
        kthSmallest(root.right);
        return list230;
    }


    //leetcode237删除链表下一个节点
    public void deleteNode(ListNode node) {
        ListNode next = node.next;
        node.val = next.val;
        node.next = next.next;

    }



    //leetcode 289生命游戏mid
    public void gameOfLife(int[][] board) {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = checkLoc(board, i, j);
            }
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = board[i][j] == 1 || board[i][j] == -2 ? 1 : 0;
            }
        }
    }

    public int checkLoc(int[][] board, int i, int j){
        int count = 0;
        int left = Math.max(j - 1, 0);
        int right = Math.min(j + 1, board[i].length - 1);
        int top = Math.max(i - 1, 0);
        int bottom = Math.min(i + 1, board.length - 1);
        for(int x = top; x <= bottom; x++){
            for(int y = left; y <= right; y++){
                count = board[x][y] == 1 || board[x][y] == -1 ? count + 1 : count;
            }
        }
        return board[i][j] == 1 ? (count == 3 || count == 4 ? 1 : -1) : (count == 3 ? -2 : 0);
    }

    //leetcode392
    public boolean isSubsequence(String s, String t) {

        int index = -1;
        for (char c : s.toCharArray()) {
            index = t.indexOf(c,index+1);
            if (index == -1) return false;
        }
        return true;
    }


    //leetcode617合并二叉树
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode res = new TreeNode(0);
        if (t1 != null && t2 != null) {
            res.val = t1.val+t2.val;
            res.left = mergeTrees(t1.left,t2.left);
            res.right = mergeTrees(t1.right,t2.right);
        }else if (t1 == null && t2 !=null) {
            res.val = t2.val;
            res.left = mergeTrees(null,t2.left);
            res.right = mergeTrees(null,t2.right);
        }else if (t1 !=null && t2 == null) {
            res.val = t1.val;
            res.left = mergeTrees(t1.left,null);
            res.right = mergeTrees(t1.right,null);
        }else {
            res = null;
        }
        return res;
    }


    //leetcode643 子数组最大平均数1
    public double findMaxAverage(int[] nums, int k) {
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i<k; i++){
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = k; i<len; i++){
            sum = sum - nums[i-k] + nums[i];
            maxSum = Math.max(sum,maxSum);
        }
        return 1.0*maxSum/k;
    }

    //leetcode 1208 尽可能使字符串相等
    public static int equalSubstring(String s, String t, int maxCost) {
        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();
        int res = 0;
        for (int cur = 0; cur<c1.length; cur++){
            int cost = maxCost;
            int i = cur;
            while (cost>=0 && i<c1.length){
                cost -= Math.abs(c1[i] - c2[i]);
                if (cost >= 0){
                    i++;
                }
            }
            res = Math.max(res,i-cur);
        }
        return res;
    }


    //leetcode 1261 受污染的二叉树
//    private TreeNode root;
//    public FindElements(TreeNode root) {
//        recover(root,0);
//
//    }
//    private void recover(TreeNode root, int val) {
//        root.val = val;
//        if (root.left != null) {
//            recover(root.left, 2 * val + 1);
//        }
//        if (root.right != null) {
//            recover(root.right, 2 * val + 2);
//        }
//    }
//
//    public boolean find(int target) {
//        return find(root,target);
//    }
//    private boolean find(TreeNode root, int target) {
//        if (root.val == target) return true;
//        if (root.left != null && find(root.left,target)) return true;
//        return root.right!=null && find(root.right,target);
//
//    }

    //leetcode 695 岛屿的最大面积
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int i = 0; i<grid.length;i++) {
            for (int j = 0; j<grid[0].length;j++) {
                if (grid[i][j] == 1) res = Math.max(res,maxAreaOfIsland(i,j,grid));
            }
        }
        return res;
    }
    private int maxAreaOfIsland(int i, int j, int[][] grid0) {
        if (i<0 || j<0 || i>=grid0.length || j>=grid0[0].length || grid0[i][j] == 0) return 0;
        int cur = 1;
        grid0[i][j] = 0; //防止一个岛屿计算两次
        cur += maxAreaOfIsland(i+1,j,grid0);
        cur += maxAreaOfIsland(i-1,j,grid0);
        cur += maxAreaOfIsland(i,j+1,grid0);
        cur += maxAreaOfIsland(i,j-1,grid0);
        return cur;
    }


    //leetcode最长不重复子串
    public int fff(){

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        int all[] = new int[256];
        int f = 0,l = -1;
        int max = 0;

        while(f<s.length()) {
            if(l+1<s.length() && all[s.charAt(l+1)] == 0) {
                all[s.charAt(l+1)] ++;
                l++;
                max = Math.max(max, l-f+1);
            }else {
                all[s.charAt(f)]--;
                f++;
            }
        }



        return max;
    }


    //发现连续序列
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        int start = 1, end = 2;
        ArrayList<ArrayList<Integer> > res = new ArrayList<>();
        while (end>start){
            int cur = (start+end) * (end+1-start)/2;
            if (cur == sum){
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = start;i<end;i++){
                    list.add(i);
                }
                res.add(list);
                end++;
            }else if (cur<sum){
                end++;
            }else {
                start++;
            }
        }
        return res;
    }

    //排序之直接插入排序
    public void insertionSort(int[] arr){
        for (int i = 0;i<arr.length-1;i++){
            for (int j = i+1;j>0;j--){
                if (arr[j-1]>arr[j]){
                    int cur = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = cur;
                }
                System.out.println(Arrays.toString(arr));
            }
        }
    }

    //希尔排序
    public void shellSort(int[] arr){
        int gap = arr.length/2;
        for (;gap>0;gap/=2){
            for (int a = 0;(a+gap)<arr.length;a++){
                for (int b = 0;(b+gap)<arr.length;b+=gap){
                    if (arr[b]>arr[b+gap]){
                        int cur = arr[b];
                        arr[b] = arr[b+gap];
                        arr[b+gap] = cur;
                    }
                    System.out.println(Arrays.toString(arr));
                }
            }
        }
    }

    //选择排序selectionSort
    public void selectionSort(int[] arr){
        for (int i = 0;i<arr.length-1;i++){
            int min = i;
            for (int j =i+1;j<arr.length; j++){
                if (arr[min]>arr[j]){
                    min = j;
                }
            }
            if (min != i){
                int cur = arr[min];
                arr[min] = arr[i];
                arr[i] = cur;
            }
            System.out.println(Arrays.toString(arr));
        }
    }
    //归并排序
    public static int[] MergeSort(int[] array) {
        if (array.length < 2) return array;
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(MergeSort(left), MergeSort(right));
    }
    /**
     * 归并排序——将两段排序好的数组结合成一个排序数组
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length)
                result[index] = right[j++];
            else if (j >= right.length)
                result[index] = left[i++];
            else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];
        }
        return result;
    }

    //快速排序
    public static void quickSort(int[] arr,int low,int high){
        int i,j,temp,t;
        if(low>high){
            return;
        }
        i=low;//#左边哨兵的索引
                j=high;//#右边哨兵的索引
                //temp就是基准位
                temp = arr[low];//#以最左边为  基准位
        while (i<j) {
            while (temp<=arr[j]&&i<j) { j--; }
            while (temp>=arr[i]&&i<j) { i++; }
            //如果满足条件则交换
            if (i<j) {
                int z = arr[i];
                int y = arr[j];
                arr[i] = y;
                arr[j] = z;
            }
        }
//#这时 跳出了 “while (i<j) {}” 循环
//#说明 i=j 左右在同一位置
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];//#或 arr[low] = arr[j];
        arr[i] = temp;//#或 arr[j] = temp;
//#i=j
//#这时  左半数组<(i或j所在索引的数)<右半数组
//#也就是说(i或j所在索引的数)已经确定排序位置， 所以就不用再排序了，
//# 只要用相同的方法 分别处理  左右数组就可以了
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }


    //堆排序（最大堆）
    public void heapSort(int[] arr){
        for(int i = arr.length; i > 0; i--){
            heapSort(arr, i);
            int temp = arr[0];      //堆顶元素(第一个元素)与Kn交换
            arr[0] = arr[i-1];
            arr[i-1] = temp;
        }
    }
    private void heapSort(int[] arr,int limit){
        if(arr.length <= 0 || arr.length < limit) return;
        int parentIdx = limit / 2;

        for(; parentIdx >= 0; parentIdx--){
            if(parentIdx * 2 >= limit){
                continue;
            }
            int left = parentIdx * 2;       //左子节点位置
            int right = (left + 1) >= limit ? left : (left + 1);    //右子节点位置，如果没有右节点，默认为左节点位置

            int maxChildId = arr[left] >= arr[right] ? left : right;
            if(arr[maxChildId] > arr[parentIdx]){   //交换父节点与左右子节点中的最大值
                int temp = arr[parentIdx];
                arr[parentIdx] = arr[maxChildId];
                arr[maxChildId] = temp;
            }
        }
        System.out.println( Arrays.toString(arr));

    }

    //凑零钱问题 多少种
    public static int countWays(int[] coins,int n) {

        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i = 0;i < coins.length;++i){
            for(int j = coins[i];j <= n;++j){ //coins分别为 1，2，5，10 dp
//	                dp[j] += dp[j-coins[i]];
                dp[j] = dp[j]+dp[j-coins[i]];
            }
        }

//        int[] dd = new int[n+1];
//        dd[0] = 1;
//        for (int i = 0; i<coins.length;i++){
//            for (int j = coins[i];j<=n;++j){
//                dd[j] = dd[j]+dd[j-coins[i]];
//            }
//
//        }

        return dp[n];
    }

    //凑零钱 最小硬币数
    public int minyingbi(int n){
        int[] db = new int[++n];
        int[] v = {1,2,5,10};
        db[0] = 0;
        for (int i = 1; i<db.length; i++) {
            db[i] = db[i-1]+1;
            for (int j = 0; j<v.length;j++) {
                if (v[j] > i) break;
                if (db[i-v[j]] < db[i-1]) {
                    db[i] = db[i-v[j]] + 1;
                }
            }

        }
        return db[n-1];
    }

    public int minyingbi2(int n){
        int[] dp = new int[n+1];
        int[] coins = {1,2,5,10};
        dp[0] = 1;
        for (int i = 0; i<coins.length; i++) {
            for (int j = coins[i]; j<n; j++) {
                dp[j] = dp[j]+dp[j-coins[i]];
                if (dp[j-coins[i]] < dp [j-1])
                    dp[j] = dp[j-coins[i]]+1;
            }
        }
        return dp[n-1];

    }


    //美团小象 双栈排序
    public ArrayList<Integer> twoStacksSort(int[] numbers) {
        //牛客解法
//        ArrayList<Integer> resList = new ArrayList<>();
//        //构建栈，并初始化栈
//        Stack<Integer> initStack = new Stack<>();
//        for (int i = 0 ; i < numbers.length; i++){
//            initStack.push(numbers[i]);
//        }
//
//        //构建辅助栈,用来存放排好序的数
//        Stack<Integer> tempStack = new Stack<>();
//        while (!initStack.isEmpty()) {
//            if (tempStack.isEmpty()){
//                tempStack.push(initStack.pop());
//            }else {
//                int cur = initStack.pop();
//                while (!tempStack.isEmpty() && cur<tempStack.peek()) {
//                    initStack.push(tempStack.pop());
//                }
//                tempStack.push(cur);
//            }
//        }
//        while(!tempStack.isEmpty()){
//            resList.add(tempStack.pop());
//        }
//        return resList;


        ArrayList<Integer> res = new ArrayList<>();
        Stack<Integer> s1 = new Stack<>();
        for (int i = numbers.length-1;i>=0;i--){
            s1.push(numbers[i]);
        }
        Stack<Integer> s2 = new Stack<>();
        twoStacksSort(s1,s2);


        // write code here
        return res;
    }
    private Stack<Integer> twoStacksSort(Stack<Integer> s1, Stack<Integer> s2){
        while (s2.isEmpty() || s2.peek() < s1.peek()) {
            s2.push(s1.pop());
            if (s1.isEmpty()) {
                return s2;
            }
        }
        int cur = s1.pop();
        while (!s2.isEmpty()){
            if (s2.peek()>cur){
                s1.push(s2.pop());
                continue;
            }
            if (s2.peek()<cur) {
                s1.push(cur);
                cur = -1;
                continue;
            }
        }
        twoStacksSort(s1,s2);
        return s2;
    }

    //leetcode283移动零
    public void moveZeroes(int[] nums) {
        int i = 0 , j = 1;
        if (nums.length<2) return;
        for (;j<nums.length && i<nums.length;i++) {
            if (nums[i] == 0 ) {
                while (nums[j] == 0) {
                     j++;
                     if (j>=nums.length) return;
                }
                int cur = nums[i];
                nums[i] = nums[j];
                nums[j] = cur;
            }
            j = i+1;
        }

    }



    public static String readTxt(String txtPath) {
        File file = new File(txtPath);
        if(file.isFile() && file.exists()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuffer sb = new StringBuffer();
                String text = null;
                while((text = bufferedReader.readLine()) != null){
                    sb.append(text);
                }
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**使用FileOutputStream来写入txt文件
     * @param txtPath txt文件路径
     * @param content 需要写入的文本
     */
    public static void writeTxt(String txtPath,String content){
        FileOutputStream fileOutputStream = null;
        File file = new File(txtPath);
        try {
            if(file.exists()){
                //判断文件是否存在，如果不存在就新建一个txt
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file,true);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



//    leetcode11水槽
    public int maxArea(int[] height) {
        int max = -1;
        int l = 0;
        int r = height.length-1;
        while (l<r) {
            if (height[l] < height[r]) {
                max = Math.max(max,height[l]* (r-l));
                l++;
            } else {
                max = Math.max(max, height[r]*(r-l));
                r--;
            }

        }
        return max;
    }


//    public int tallestBillboard(int[] rods) {
//        Arrays.sort(rods);
//        int len = rods.length;
//        int curMax = rods[len-1];
//
//
//    }


    public int sumSubseqWidths(int[] A) {
        int res = 0;
        List<List<Integer>> list = subsets(A);
        for (int i = 0; i<list.size(); i++) {
            res = res + list.get(i).size()-1;
        }
        return res;

    }

    //二叉树最小深度
    public int run(TreeNode root) {
        if (root == null) return 0;
        int l = run(root.left);
        int r = run(root.right);
        if (l == 0 || r == 0) return l+r+1;
        return Math.min(l,r)+1;

    }


    //逆波兰表达式
    public int evalRPN(String[] tokens) {
        Stack<String> s1 = new Stack();
        for(int i = 0; i<tokens.length;i++){
            if(tokens[i].equals("+")){
                int a = Integer.valueOf(s1.pop());
                int b = Integer.valueOf(s1.pop());
                s1.push(String.valueOf(a+b));
            }else if (tokens[i].equals("-")) {
                int a = Integer.valueOf(s1.pop());
                int b = Integer.valueOf(s1.pop());
                s1.push(String.valueOf(b-a));
            }else if (tokens[i].equals("*")) {
                int a = Integer.valueOf(s1.pop());
                int b = Integer.valueOf(s1.pop());
                s1.push(String.valueOf(a*b));
            }else if (tokens[i].equals("/")){
                int a = Integer.valueOf(s1.pop());
                int b = Integer.valueOf(s1.pop());
                s1.push(String.valueOf(b/a));
            }else {
                s1.push(tokens[i]);
            }

        }
        return Integer.valueOf(s1.peek());
    }

    //hanoi
    public void hanoi(int n, char a, char b, char c) {
        if (n == 1) {
            System.out.println("盘"+n+"由 "+a+" 移动到: "+c);
        }else {
            hanoi(n-1,a,c,b);
            System.out.println("盘"+n+"由 "+a+" 移动到: "+c);
            hanoi(n-1,b,a,c);
        }
    }

    //距离最近的素数
    private static int makeNearestPrime(int a) {
        int[] sushu = new int[100010];
        int[] pd = new int[100010];
        int num = 0;
        pd[0] = 1;
        pd[1] = 1;
        for (int i = 2; i<100010; i++) {
            if (pd[i] == 0) sushu[num++] = i;
            for (int j = 0; j<num && i*sushu[j]< 100010; j++) {
                pd[i*sushu[j]] = 1;
                if (i%sushu[j]==0) break;
            }
        }


        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        for (int i = 0; i<n; i++) {
            int max = 0;
            int min = 0;
            if (arr[i] == 1 || arr[i] ==0) {
                System.out.println(2);
                continue;
            }
            if (pd[arr[i]] == 0){
                System.out.println(arr[i]);
                continue;
            } else {
                for (int q = arr[i] +1;;q++) {
                    if (pd[q] == 0) {
                        max = q;
                        break;
                    }
                }
                for (int p = arr[i]-1;;p--) {
                    if (pd[p] == 0) {
                        min = p;
                        break;
                    }
                }
                if (Math.abs(max - arr[i]) <= Math.abs(min - arr[i])){
                    System.out.println(max);
                } else {
                    System.out.println(min);
                }
            }
        }

        return -1;
    }
    private static boolean isPrime(int i) {
        double d = Math.sqrt(i);
        for (int j = 2; j<i; j++) {
            if (i%j == 0) return true;
        }
        return false;
    }


    public int ceshi(int[] arr ,int s){
        int l = 0;
        int r = 1;
        int res = arr.length;

        while (l != r && r< arr.length) {
            int sum = 0;
            for (int i = l;i<=r; i++) {
                sum += arr[i];
            }
            if (sum >= s) {
                res = Math.min(res , r-l);
                l++;
            }else {
                r++;
            }
        }
        return res;
    }

    private void swap(int[] arr, int l, int r){
        int cur = arr[l];
        arr[l] = arr[r];
        arr[r] = cur;
    }

    /*
    * 给定一张包含N个点、N-1条边的无向连通图，节点从1到N编号，每条边的长度均为1。假设你从1号节点出发并打算遍历所有节点，那么总路程至少是多少？
    *
    *   4
        1 2
        1 3
        3 4 输出4
    *  */
    public int meituan20191(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] depth = new int[n+1];
        for (int i = 0; i<n-1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            depth[b] = depth[a]+1;
        }
        int max = 0;
        for (int i = 1; i<n+1; i++) {
            if (depth[i] > max) max = depth[i];
        }
        return 2*(n-1-max)+max;
    }

    //给你一个01字符串，定义答案=该串中最长的连续1的长度，现在你有至多K次机会，每次机会可以将串中的某个0改成1，现在问最大的可能答案
    //10 2
    //1 0 0 1 0 1 0 1 0 1  输出5
    public int meituan20192(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n+1];
        for(int i = 0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        int max = 0;
        for(int i = 0; i<n; i++) {
            if(arr[i] == 0) continue;
            int change = k;
            int j = i+1;
            for(; j<n; j++) {
                if(arr[j] == 1) continue;
                if(arr[j] == 0 && change >0){
                    change--;
                }else{
                    break;
                }
            }
            max = Math.max(max,j-i);
        }
       return max;
    }

    //你打开了美了么外卖，选择了一家店，你手里有一张满X元减10元的券，店里总共有n种菜，第i种菜一份需要A_i元，
    // 因为你不想吃太多份同一种菜，所以每种菜你最多只能点一份，现在问你最少需要选择多少元的商品才能使用这张券。
    //5 20
    //18 19 17 6 7 输出23
    public int meituan20193(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int money = sc.nextInt();
        int arr[] = new int[n+1];
        for (int i = 1; i<=n; i++) {
            arr[i] = sc.nextInt();
        }
        int[][] dp = new int[n+1][money+1];
        for (int i = 0; i<=money;i++) {
            dp[0][i] = 10001;
        }
        for (int i = 1; i<=n; i++) {
            for (int j = 0; j<= money; j++) {
                if (j<=arr[i]) {
                    dp[i][j] = Math.min(arr[i],dp[i-1][j]);
                }else {
                    dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j-arr[i]]+arr[i]);
                }
            }
        }
        return dp[n][money];

    }

    //公园里有N个花园，初始时每个花园里都没有种花，园丁将花园从1到N编号并计划在编号为i的花园里恰好种A_i朵花，
    // 他每天会选择一个区间[L，R]（1≤L≤R≤N）并在编号为L到R的花园里各种一朵花，那么园丁至少要花多少天才能完成计划？
    //5
    //4 1 8 2 5 输出14
    public int meituan20194(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        int res = 0;
        for (int i = 1; i<n; i++) {
            if (arr[i-1] > arr[i]) res += arr[i-1]-arr[i];
        }
        return res+arr[n-1];
    }
//小明同学在参加一场考试，考试时间2个小时。试卷上一共有n道题目，小明要在规定时间内，完成一定数量的题目。
// 考试中不限制试题作答顺序，对于 i 第道题目，小明有三种不同的策略可以选择:  (1)直接跳过这道题目，不花费时间，本题得0分。
//(2)只做一部分题目，花费pi分钟的时间，本题可以得到ai分。  (3)做完整个题目，花费qi分钟的时间，本题可以得到bi分。
//小明想知道，他最多能得到多少分。
    //4
    //20 20 100 60
    //50 30 80 55
    //100 60 110 88
    //5 3 10 6 输出94

    public int meituan20195(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] score = new int[n][4];
        for(int i = 0; i<n; i++) {
            score[i][0] = sc.nextInt();
            score[i][1] = sc.nextInt();
            score[i][2] = sc.nextInt();
            score[i][3] = sc.nextInt();
        }
        int[] dp = new int[121];
        for(int i = 0; i<n; i++) {
            for(int j = 120; j >= 0; j--) {
                if(j>= score[i][0]) dp[j] = Math.max(dp[j], dp[j-score[i][0]] + score[i][1]);
                if(j>= score[i][2]) dp[j] = Math.max(dp[j], dp[j-score[i][2]] + score[i][3]);
            }
        }
        return dp[120];
    }

    public void meituan20201(){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        String[] score = new String[2];
        int[][] dp = new int[4][n];
        for(int i = 0; i<2; i++) {
            score[i] = sc.next();
        }
        for (int i = 0; i<n; i++) {
            dp[0][i] = 0;
            dp[3][i] = 0;
            if (score[0].charAt(i) == '.') dp[1][i] = 0;
            if (score[0].charAt(i) == 'X') dp[1][i] = 1;
            if (score[1].charAt(i) == '.') dp[2][i] = 0;
            if (score[1].charAt(i) == 'X') dp[2][i] = 1;
        }
        int l = 4;
        int h = n;
        if (dp[1][0] == 1){
            System.out.println(-1);
        }
        dp[1][0] = 1;
        dp[2][0] = 0;

        for (int j = 1; j<h; j++) {
            for (int i = 1; i<l-1; i++) {
                if (dp[i][j] == 0){
                    dp[i][j] = dp[i][j-1]+dp[i+1][j-1]+dp[i-1][j-1];
                }else {
                    dp[i][j] = 0;
                }
            }
        }
        System.out.println(dp[l-2][h-1]);
    }

    public static void main(String[] args) {
        String a = "krrgw";
        String b = "zjxss";
        equalSubstring(a,b,19);
//        Scanner in = new Scanner(System.in);
//
//        int n = in.nextInt();
//        int maxw = in.nextInt();
//        int[] w = new int[n];
//        int[] v = new int[n];
//        int[][] dp = new int[n+1][maxw];
//        for (int i = 0; i<n; i++) {
//            w[i] = in.nextInt();
//        }for (int i = 0; i<n; i++) {
//            v[i] = in.nextInt();
//        }
//        for (int i = 1; i<dp.length; i++) {
//            for (int j = 1;j<dp[0].length;j++) {
//                if (w[i-1] >j){
//                    dp[i][j] = dp[i-1][j];
//                }else {
//                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-w[i-1]]+v[i-1]);
//                }
//            }
//        }
//        System.out.println(dp[n][maxw-1]);
//
//
//        int res = n;
//        int cmax = in.nextInt();
//        int cmin = cmax;
//        int kmax = in.nextInt();
//        int kmin = kmax;
//        for (int i = 0; i<n-1; i++) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
//            int a = in.nextInt();
//            int b = in.nextInt();
//            if (a<cmin && b<kmin) {
//                res--;
//                cmin = a;
//                kmin = b;
//            }
//            if (a>cmax && b > kmax) {
//                res --;
//                cmax = a;
//                kmax = b;
//            }
//        }
//        System.out.println(res);



//        int[][] dp = new int[m][n];
//        for (int i = 0; i<m; i++) dp[i][0] = 1;
//        for (int i = 0; i<n; i++) dp[0][i] = 1;
//        for (int i = 1; i<m; i++) {
//            for (int j = 1; j<n;j++) {
//                dp[i][j] = dp[i-1][j] + dp[i][j-1];
//            }
//        }
//        return dp[m-1][n-1];
//            String a = "abcabc";
//            String b = a.substring(1,3);
//            if (b.contains("a")) System.out.println(b);

//        Main m = new Main();
//        int[] arr = {9,8,7,6,5,4,3,2,1};



////        m.hanoi(3,'A','B','C');
//        m.meituan20193();
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//
//        int[] arr = new int[3*n];
//        for(int i = 0; i < 3*n; i++){
//            arr[i] = sc.nextInt();
//        }
//        Arrays.sort(arr);
//        int l = 0, r = arr.length-1;
//        int res = 0;
//        while (n>0) {
//            res += arr[r-1];
//            l++;
//            r -= 2;
//            n--;
//        }
//        System.out.println(res);

//        double[] doubles = new double[20];
//        writeTxt("D:/result1.txt", "排序前： ");
//        for (int i = 0; i < doubles.length; i++) {
//            double x = Math.random();//生成[0,1)
//            double y = x * 100; // 转为[0, 1000)
//            doubles[i] = y;
//            writeTxt("D:/result1.txt", String.valueOf(y)+" ");
//        }
//
//        //以上是写入文件
//        //以下是排序并写
//        String str = readTxt("D:/result1.txt");
//        String[] strings = str.split(" ");
//        double[] doubles1 = new double[20];
//        for (int i = 0; i<20; i++) {
//            double d = Double.valueOf(strings[i+1]);
//            doubles1[i] = d;
//        }
//        writeTxt("D:/result1.txt", "排序后：");
//        Arrays.sort(doubles1);
//        for (int i = 0; i<doubles1.length;i++) {
//            writeTxt("D:/result1.txt", String.valueOf(doubles1[i])+" ");
//        }




    }




}
