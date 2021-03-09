package com.zy.code;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
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

    private int capacity;
    private LRUNode head, tail;
    private int size;
    private Map<Integer, LRUNode> cache = new HashMap<>();


    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new LRUNode();
        this.tail = new LRUNode();
        this.size = 0;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        LRUNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        LRUNode node = cache.get(key);
        if (node == null) {
            //如果不存在，在双向链表头创建一个新的节点
            LRUNode cur = new LRUNode(key, value);
            cache.put(key, cur);
            addToHead(cur);
            size++;
            //容量不够，删除尾节点
            if (size > capacity) {
                LRUNode tailNode = removeTail();
                cache.remove(tailNode.key);
                size--;
            }

        } else {
            //更新value并将node移动到头
            node.value = value;
            moveToHead(node);
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
