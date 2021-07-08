package taiji.test.controller;



import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    //206. 反转链表
    public ListNode reverseList(ListNode head) {
        ListNode newhead = head,pp = null,next = null,res = null;
        while (newhead != null){
            if (newhead.next == null){
                res = newhead;
            }
            next = newhead.next;
            newhead.next = pp;
            pp = newhead;
            newhead = next;
        }

        return res;
    }
    //LRU
    class LRUCache{
        class LRUNode {
            int key;
            int value;
            LRUNode next;
            LRUNode prev;

            public LRUNode() {
            }

            public LRUNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
        private LRUNode head;
        private LRUNode tail;
        private int maxSize;
        private int size;
        private Map<Integer,LRUNode> cache = new HashMap<>();

        public LRUCache(int maxSize) {
            this.head = new LRUNode();
            this.tail = new LRUNode();
            this.maxSize = maxSize;
            this.size = 0;
            head.next = tail;
            tail.prev = head;
        }



        public int get(int key){
            LRUNode node = cache.get(key);
            if (node == null){
                return -1;
            }
            moveToHead(node);
            return node.value;
        }
        public void put(int key,int value){
            LRUNode n = cache.get(key);
            if (n == null){
                LRUNode cur = new LRUNode(key,value);
                cache.put(key,cur);
                addToHead(cur);
                size++;
                if (size>maxSize){
                    LRUNode removeTail = removeTail();
                    cache.remove(removeTail.key);
                    size--;
                }
            }else {
                n.value = value;
                moveToHead(n);
            }
        }
        private void addToHead(LRUNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(LRUNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void moveToHead(LRUNode node) {
            removeNode(node);
            addToHead(node);
        }

        private LRUNode removeTail() {
            LRUNode tailNode = tail.prev;
            removeNode(tailNode);
            return tailNode;
        }

    }

    //215数组中的第K个最大元素
    public int findKthLargest(int[] nums, int k) {
        int left = 0,right = nums.length-1;
        int index = nums.length-k;
        while (true){
            int res = findKthLargest(nums,left,right);
            if (res == index){
                return nums[index];
            }else if (res>index){
                right = res-1;
            }else {
                left = res+1;
            }
        }
    }
    private int findKthLargest(int[] nums,int left,int right){
        int i = left,temp = nums[left];
        for (int a = left+1;a<=right;a++){
            if (nums[a]<temp){
                i++;
                swap(nums,a,i);
            }
        }
        swap(nums,left,i);
        return i;
    }
    private static void swap(int[] arr, int l, int r){
        int cur = arr[l];
        arr[l] = arr[r];
        arr[r] = cur;
    }

    //3无重复字符的最长子串
    public int lengthOfLongestSubstring(String s){
        char[] chars = s.toCharArray();
        int[] all = new int[256];
        int i = 0,j = 0,max = 0;
        while (i<chars.length){
            if (j<chars.length && all[chars[j]] == 0){
                all[chars[j]]++;
                j++;
                max = Math.max(max,j-i);
            }else {
                all[chars[i]]--;
                i++;
            }
        }
        return max;
    }

    //25K 个一组翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dhead = new ListNode(0);
        dhead.next = head;
        ListNode pre = dhead,end = dhead;
        while (end.next != null){
            for (int i = 0;i<k && end != null;i++){
                end = end.next;
            }
            if (end == null) {
                break;
            }
            ListNode start = pre.next,next = end.next;
            end.next = null;
            ListNode cur = reverseKGroup(start);
            pre.next = cur;
            start.next = next;
            pre = start;
            end = pre;
        }
        return dhead.next;
    }

    private ListNode reverseKGroup(ListNode head){
        ListNode pp = null,newhead = null,cur = head,next = null;
        while (cur != null){
            if (cur.next == null){
                newhead = cur;
            }
            next = cur.next;
            cur.next = pp;
            pp = cur;
            cur = next;
        }
        return newhead;
    }

    //补充题4. 手撕快速排序
    public static int[] sortArray(int[] nums) {
        int left = 0,right = nums.length-1;
        sortArray(nums,left,right);
        return nums;
    }
//5231
    private static void sortArray(int[] nums,int left,int right){
        if (left>right) return;
        int i = left,j = right,temp = nums[left];
        while (i<j){
            while (i<j && nums[j]>=temp){
                j--;
            }
            while (i<j && nums[i]<=temp){
                i++;
            }
            if (i<j){
                swap(nums,i,j);
            }
        }
        nums[left] = nums[i];
        nums[i] = temp;
        sortArray(nums,left,i-1);
        sortArray(nums,i+1,right);
    }

    //15. 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0;i<nums.length-2;i++){
            int l = i+1,r =nums.length-1;
            while (l<r){
                if (nums[i]+nums[l]+nums[r] == 0){
                    res.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    l++;
                    r--;
                }else if (nums[i]+nums[l]+nums[r] > 0){
                    r--;
                }else if (nums[i]+nums[l]+nums[r] < 0){
                    l++;
                }
            }
        }
        return res;
    }
    //160. 相交链表
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode ha = headA,hb = headB;
        while (ha != hb){
            ha = ha == null?headB:ha.next;
            hb = hb == null?headA:hb.next;
        }
        return ha;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //103. 二叉树的锯齿形层序遍历
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        zigzagLevelOrder(root,0,res);
        return res;
    }

    private void zigzagLevelOrder(TreeNode root,int level,List<List<Integer>> res){
        if (root == null){
            return;
        }
        if (res.size()<=level){
            res.add(new ArrayList<>());
        }
        if (level%2 == 1){
            res.get(level).add(0,root.val);
        }else {
            res.get(level).add(root.val);
        }
        zigzagLevelOrder(root.left,level+1,res);
        zigzagLevelOrder(root.right,level+1,res);
    }

    //141. 环形链表
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next==null) return false;
        ListNode slow = head,fast = head.next;
        while (slow != fast){
            if (fast == null || fast.next==null) return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    //102. 二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrder(root,0,res);
        return res;
    }
    private void levelOrder(TreeNode node,int level,List<List<Integer>> res){
        if (node == null) return;
        if (res.size()<=level) res.add(new ArrayList<>());
        res.get(level).add(node.val);
        levelOrder(node.left,level+1,res);
        levelOrder(node.right,level+1,res);
    }

    //121. 买卖股票的最佳时机
    public int maxProfit(int[] prices) {
        int res = 0,mairu = prices[0];
        for (int i = 1;i<prices.length;i++){
            if (prices[i]>mairu){
                res = Math.max(res,prices[i] - mairu);
            }else {
                mairu = prices[i];
            }
        }
        return res;
    }

    //92. 反转链表 II
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head ==null || head.next==null||left>right) {
            return head;
        }
        ListNode dhead = new ListNode(-1);
        dhead.next = head;
        ListNode pre = dhead,start = null,end = dhead,next = null;
        for (int i = 0;i<left-1;i++){
            pre = pre.next;
        }
        start = pre.next;
        for (int j = 0;j<right-1;j++){
            end = end.next;
        }
        next = end.next;
        pre.next = null;
        end.next = null;
        fanzhuan(start);
        pre.next = end;
        start.next = next;
        return dhead.next;
    }
    private ListNode fanzhuan(ListNode head){
        ListNode pp =null,newhead = null,cur = head,next = null;
        while (cur != null){
            if (cur.next == null){
                newhead = cur;
            }
            next = cur.next;
            cur.next = pp;
            pp = cur;
            cur = next;
        }
        return newhead;
    }

    //33. 搜索旋转排序数组
    public int search(int[] nums, int target) {
        int len = nums.length,left = 0,right = len-1,mid;
        while (left<=right){
            mid = right-left/2;
            if (nums[mid] == target) return mid;
            if (nums[left]<=nums[mid]){
                if (nums[left]<=target&&target<nums[mid]){
                    right = mid-1;
                }else {
                    left = mid+1;
                }
            }else {
                if (nums[right]>=target&&target>nums[mid]){
                    left = mid+1;
                }else {
                    right = mid-1;
                }
            }
        }
        return -1;
    }

    //236. 二叉树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == q|| root == p) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    //1.两数之和
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0;i<nums.length;i++){
            if (map.get(target-nums[i]) != null){
                return new int[]{map.get(target-nums[i]),i};
            }else {
                map.put(nums[i],i);
            }
        }
        return null;
    }

    //42. 接雨水
//    0,1,0,2,1,0,1,3,2,1,2,1
    public int trap(int[] height) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
        if (height.length<3) return 0;
        int left = 0,right = height.length-1,res = 0;
        int lmax = height[left],rmax = height[right];
        while (left<right){
            lmax = Math.max(lmax,height[left]);
            rmax = Math.max(rmax,height[right]);
            if (lmax<rmax){
                res += lmax-height[left];
                left++;
            }else {
                res += rmax-height[right];
                right--;
            }
        }
        return res;
    }

    static class User{
        private Integer id;
        private String name;

        public User(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return Objects.equals(id, user.id) &&
                    Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }

    public static int[] merge(int[] a,int[] b){
        int alen = a.length,blen = b.length;
        int[] res = new int[alen+blen];
        int i = 0, j = 0,k=0;
        while (i<alen||j<blen){
            if (i==alen){
                res[k] = b[j];
                j++;
                k++;
                continue;
            }
            if (j==blen){
                res[k] = a[i];
                i++;
                k++;
                continue;
            }
            if (a[i]<b[j]){
                res[k] = a[i];
                i++;
            }else {
                res[k] = b[j];
                j++;
            }
            k++;
        }

        return res;
    }


    //199. 二叉树的右视图
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        reightSideView(root,0,res);
        return res;
    }
    private void reightSideView(TreeNode node,int level,List<Integer> res){
        if (node == null) return;
        if (res.size()<=level){
            res.add(node.val);
        }
        reightSideView(node.right,level+1,res);
        reightSideView(node.left,level+1,res);

    }

    //415. 字符串相加
    public String addStrings(String num1, String num2) {
        int temp = 0,i = num1.length()-1,j = num2.length()-1;
        StringBuilder result = new StringBuilder();
        while (i>=0||j>=0){
            int n1 = i>=0? num1.charAt(i)-'0':0;
            int n2 = j>=0?num2.charAt(j)-'0':0;
            int res= n1+n2+temp;
            temp = res/10;
            result.append(res%10);
            i--;
            j--;
        }
        if (temp==1) result.append("1");
        return result.reverse().toString();

    }

    //23. 合并K个升序链表
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len<1) return null;
        while (len>1){
            for (int i = 0;i<len/2;i++){
                lists[i] = mergeListNode(lists[i],lists[len-i-1]);
            }

            len = (len+1)/2;
        }
        return lists[0];

    }
    private ListNode mergeListNode(ListNode l1,ListNode l2){
        ListNode dhead = new ListNode(-1);
        ListNode pp = dhead;
        while (l1!=null&&l2!=null){
            if (l1.val<l2.val){
                pp.next = l1;
                l1 = l1.next;
            }else {
                pp.next = l2;
                l2 = l2.next;
            }
            pp = pp.next;
        }
        pp.next = l1==null?l2:l1;
        return dhead.next;
    }

    //142. 环形链表 II
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head,fast = head;
        while (true){
            if (fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
            if (slow==fast) break;
        }
        slow = head;
        while (slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //54. 螺旋矩阵
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int left = 0,right = matrix[0].length-1,shang = 0,xia = matrix.length-1;
        while (left<=right&&shang<=xia){
            for (int i = left;i<=right;i++){
                res.add(matrix[shang][i]);
            }
            for (int j = shang+1;j<=xia;j++){
                res.add(matrix[j][right]);
            }
            if (shang!=xia){
                for (int a = right-1;a>=left;a--){
                    res.add(matrix[xia][a]);
                }
            }
            if (left!=right){
                for (int b = xia-1;b>shang;b--){
                    res.add(matrix[b][left]);
                }
            }
            left++;
            right--;
            shang++;
            xia--;
        }
        return res;
    }

    //46. 全排列
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        permute(nums,res,list);
        return res;
    }
    private void permute(int[] nums,List<List<Integer>> res,List<Integer> list){
        if (list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0;i<nums.length;i++){
            if (list.contains(nums[i])) continue;
            list.add(nums[i]);
            permute(nums,res,list);
            list.remove(list.size()-1);
        }

    }

    //53. 最大子序和
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        for (int i = 1;i<nums.length;i++){
            if (nums[i-1]>0){
                nums[i] += nums[i-1];
            }
            res = Math.max(res,nums[i]);
        }
        return res;
    }

    //5. 最长回文子串
    public String longestPalindrome(String s) {
        int len = s.length(),start = 0,max = 1;
        if (len <= 1) return s;
        char[] chars = s.toCharArray();
        int[][] dp = new int[len][len];
        for (int i = 0;i<s.length();i++){
            dp[i][i] = 1;
        }
        for (int i = 0;i<len;i++){
            for (int j = i-1;j>=0;j--){
                if (chars[i] == chars[j] && (i-j==1 || dp[i-1][j+1] > 0)){
                    dp[i][j] = dp[i-1][j+1] +2;
                }else {
                    dp[i][j] = 0;
                }
                if (dp[i][j]>max){
                    start = j;
                    max = dp[i][j];
                }
            }
        }
        return s.substring(start,start+max);
    }

    //94. 二叉树的中序遍历
    List<Integer> l94 = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        inorderTraversal(root.left);
        l94.add(root.val);
        inorderTraversal(root.right);
        return l94;

    }

    //20. 有效的括号
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0;i<chars.length;i++){
            if (chars[i] == '('){
                stack.push(')');
            }else if (chars[i] == '{'){
                stack.push('}');
            }else if (chars[i] == '['){
                stack.push(']');
            }else if (stack.isEmpty() || chars[i] != stack.pop()){
                return false;
            }
        }
        if (!stack.isEmpty()){
            return false;
        }
        return true;
    }

    //143. 重排链表
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        ListNode newmid = reorderList143(mid);
        while (newmid != null){
            ListNode cur = newmid.next;
            newmid.next = head.next;
            head.next = newmid;
            head = newmid.next;
            newmid = cur;
        }

    }
    private ListNode reorderList143(ListNode head){
        ListNode pp = null,dhead = null,next = null,newhead = head;
        while (newhead != null){
            if (newhead.next == null){
                dhead = newhead;
            }
            next = newhead.next;
            newhead.next = pp;
            pp = newhead;
            newhead = next;
        }
        return dhead;
    }

    //300. 最长递增子序列
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res =1;
        for (int i = 1;i<nums.length;i++){
            dp[i] = 1;
            for (int j = i-1;j>=0;j--){
                if (nums[j]<nums[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            res = Math.max(dp[i],res);
        }
        return res;
    }

    public static int lengthOfLIS1(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res =1;
        for (int i = 1;i<nums.length;i++){
            dp[i] = 1;
            if (nums[i-1]<nums[i]){
                dp[i] = Math.max(dp[i],dp[i-1]+1);
            }
            res = Math.max(dp[i],res);
        }
        return res;
    }


    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() ->{
            try {
                blockingQueue.put("1");
                blockingQueue.put("2");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"aaa").start();

        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(5);
                blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"aaa").start();


//        int[] a = {10,9,2,5,3,7,101,18};
//        lengthOfLIS1(a);

//        int[] a = {1,2,5};
//        int[] b = {2,3,6};
//        int[] merge = merge(a, b);
//        for (int i = 0;i<merge.length;i++){
//            System.out.println(merge[i]);
//        }

//        Integer a = new Integer(5);
//        Integer b = new Integer(5);
//        Integer c = 5;
//        Integer d = 5;
//        Integer e = 128;
//        Integer f = 128;
//        int g = 128;
//        System.out.println(a==b);
//        System.out.println(b==c);
//        System.out.println(c==d);
//        System.out.println(e==f);
//        System.out.println(f==g);

        /*
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = 3;
        int[][] res ={{1,0,0},{0,1,0},{0,0,1}};
        int a = res[0].length;
        int b = res.length;

        int r = 0;
        for (int i = 0;i<a;i++){
            for (int j = 0;j<b;j++){
                if (res[i][j] == 0 && res[j][i] == 1){
                    res[i][j] = 1;
                }
                boolean cur = false;
                int c = 0;
                while (c<n){
                    if (res[c][i] == 1 && res[c][j] ==1){
                        cur = true;
                        break;
                    }
                    c++;
                }
                //res[i][j] == 0 代表直接不通：1.查看对位是否通2.循环看一下123..分别和ij通不通
                if (res[i][j] == 0 && cur){
                    res[i][j] = 1;
                }else {
                    r++;
                    res[i][j] = 1;
                }
            }
        }
        System.out.println(r);
*/
    }
}

