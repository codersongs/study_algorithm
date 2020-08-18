package com.codersongs.algorithm.linkedlist;

import com.codersongs.algorithm.base.ListNode;

/**
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class L234isPalindrome {
    public static void main(String[] args) {

    }

    /**
     * 思路一：反转前半部分链表
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode slow = head, fast = head, pre = null;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            ListNode tmp = slow.next;
            slow.next = pre;
            pre = slow;
            slow = tmp;
        }
        //此时slow 指向后半部分链表，prev指向前半部分链表
        if (fast != null) slow = slow.next;
        while (slow != null && pre != null){
            if (slow.val != pre.val) return false;
            slow = slow.next;
            pre = pre.next;
        }
        return true;
    }
}
