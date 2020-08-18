package com.codersongs.algorithm.linkedlist;

import com.codersongs.algorithm.base.ListNode;
import com.codersongs.algorithm.base.ListNodeUtils;

/**
 * 328. 奇偶链表
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 *
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 */
public class L328oddEvenList {
    public static void main(String[] args) {
        L328oddEvenList l328oddEvenList = new L328oddEvenList();
//        ListNode listNode = l328oddEvenList.oddEvenList(ListNodeUtils.convertArray2ListNode(new int[]{1, 2, 3, 4, 5}));
//        ListNode listNode = l328oddEvenList.oddEvenList(ListNodeUtils.convertArray2ListNode(new int[]{2,1,3,5,6,4,7}));
        ListNode listNode = l328oddEvenList.oddEvenList(ListNodeUtils.convertArray2ListNode(new int[]{1,2,3,4}));
        System.out.println(ListNodeUtils.convertListNode2List(listNode));
    }

    /**
     * 思路一，分别记录奇偶链表
     * @param head
     * @return
     */
    public ListNode oddEvenList1(ListNode head) {
        if (head == null || head.next == null) return head;
        boolean isOdd = true;

        ListNode cur = head.next.next;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenStart = head.next;
        even.next = null;
        while (cur != null){
            ListNode curNext = cur.next;
            if (isOdd){
                cur.next = evenStart;
                odd.next = cur;
                odd = odd.next;
            } else {
                even.next = cur;
                even = even.next;
                even.next = null;
            }
            cur = curNext;
            isOdd = !isOdd;
        }
        return head;
    }

    /**
     * 思路二：交替使用odd和event
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
