package com.codersongs.algorithm.linkedlist;

import com.codersongs.algorithm.base.ListNode;
import com.codersongs.algorithm.base.ListNodeUtils;

import java.util.LinkedList;

public class L445addTwoNumbers {
    public static void main(String[] args) {
        L445addTwoNumbers l445addTwoNumbers = new L445addTwoNumbers();
        ListNode listNode = l445addTwoNumbers.addTwoNumbers(ListNodeUtils.convertArray2ListNode(new int[]{9, 9}), ListNodeUtils.convertArray2ListNode(new int[]{1}));
        System.out.println(ListNodeUtils.convertListNode2List(listNode));
    }

    /**
     * 思路一：反转链表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode r1 = reverseNode(l1);
        ListNode r2 = reverseNode(l2);

        int flag = 0;
        ListNode sentinel = new ListNode(0);
        while (r1 != null && r2 != null){
            int sum = r1.val + r2.val + flag;
            ListNode sumNode = new ListNode(sum % 10);
            sumNode.next = sentinel.next;
            sentinel.next = sumNode;
            flag = sum / 10;
            r1 = r1.next;
            r2 = r2.next;
        }

        while (r1 != null){
            int sum = r1.val + flag;
            ListNode sumNode = new ListNode(sum % 10);
            sumNode.next = sentinel.next;
            sentinel.next = sumNode;
            flag = sum / 10;
            r1 = r1.next;
        }

        while (r2 != null){
            int sum = r2.val + flag;
            ListNode sumNode = new ListNode(sum % 10);
            sumNode.next = sentinel.next;
            sentinel.next = sumNode;
            flag = sum / 10;
            r2 = r2.next;
        }
        if (flag > 0){
            ListNode sumNode = new ListNode(flag);
            sumNode.next = sentinel.next;
            sentinel.next = sumNode;
        }
        return sentinel.next;
    }

    private ListNode reverseNode(ListNode l1) {
        if (l1 == null){
            return null;
        }

        ListNode sentinel = new ListNode(0);
        while (l1 != null){
            ListNode l1Next = l1.next;
            l1.next = sentinel.next;
            sentinel.next = l1;
            l1 = l1Next;
        }
        return sentinel.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        LinkedList<Integer> stack1 = new LinkedList<>();
        LinkedList<Integer> stack2 = new LinkedList<>();

        while (l1 != null){
            stack1.addFirst(l1.val);
            l1 = l1.next;
        }
        while (l2 != null){
            stack2.addFirst(l2.val);
            l2 = l2.next;
        }

        ListNode sentinel = new ListNode(0);
        int flag = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || flag != 0){
            int num1 = stack1.isEmpty() ? 0 : stack1.removeFirst();
            int num2 = stack2.isEmpty() ? 0 : stack2.removeFirst();

            int sum = num1 + num2 + flag;
            ListNode cur = new ListNode(sum % 10);
            cur.next = sentinel.next;
            sentinel.next = cur;

            flag = sum / 10;
        }
        return sentinel.next;
    }
}
