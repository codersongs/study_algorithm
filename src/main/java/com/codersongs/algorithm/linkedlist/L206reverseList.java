package com.codersongs.algorithm.linkedlist;

import com.codersongs.algorithm.base.ListNode;
import com.codersongs.algorithm.base.ListNodeUtils;

/**
 * 206. 反转链表
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * 通过次数309,137提交次数439,959
 * 在真实的面试中遇到过这道题？
 */
public class L206reverseList {
    public static void main(String[] args) {
        L206reverseList l206reverseList = new L206reverseList();
        ListNode listNode = l206reverseList.reverseList(ListNodeUtils.convertArray2ListNode(new int[]{1,2,3,4,5}));
        System.out.println(ListNodeUtils.convertListNode2List(listNode));
    }

    /**
     * 方法一：直接反转
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
//        1.边界判断
        if (head == null || head.next == null){
            return head;
        }
//        2.遍历反转
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null){
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        return prev;
    }

    /**
     * 思路二：使用头插法
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null){
            return null;
        }
        ListNode res = new ListNode(0);
        while (head != null){
            ListNode headNext = head.next;
            head.next = res.next;
            res.next = head;
            head = headNext;
        }

        return res.next;
    }
}
