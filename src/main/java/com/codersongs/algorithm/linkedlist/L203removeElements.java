package com.codersongs.algorithm.linkedlist;

import com.codersongs.algorithm.base.ListNode;
import com.codersongs.algorithm.base.ListNodeUtils;

public class L203removeElements {
    public static void main(String[] args) {
        L203removeElements l203removeElements = new L203removeElements();
//        ListNode listNode = l203removeElements.removeElements(ListNodeUtils.convertArray2ListNode(new int[]{1, 2, 6, 3, 4, 5, 6}), 6);
        ListNode listNode = l203removeElements.removeElements(ListNodeUtils.convertArray2ListNode(new int[]{1, 1}), 1);
        System.out.println(ListNodeUtils.convertListNode2List(listNode));
    }

    /**
     * 203. 移除链表元素
     * 删除链表中等于给定值 val 的所有节点。
     *
     * 示例:
     *
     * 输入: 1->2->6->3->4->5->6, val = 6
     * 输出: 1->2->3->4->5
     * 通过次数96,200提交次数208,591
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null){
            return null;
        }
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode prev = sentinel, cur = head;
        while (cur != null){
            if (cur.val == val){
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
        return sentinel.next;
    }
}
