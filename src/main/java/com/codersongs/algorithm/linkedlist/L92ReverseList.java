package com.codersongs.algorithm.linkedlist;

import com.codersongs.algorithm.base.ListNode;

/**
 * 反转链表
 */
public class L92ReverseList {
    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    /**
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode root = new ListNode(0);
        ListNode next = null;
        while (head != null){
            next = head.next;
            head.next = root.next;
            root.next = head;
            head = next;
        }
        return root.next;
    }

    public ListNode reverseList3(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = head;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
