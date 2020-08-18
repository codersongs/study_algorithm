package com.codersongs.algorithm.base;

import java.util.ArrayList;
import java.util.List;

public class ListNodeUtils {
    public static ListNode convertArray2ListNode(int[] array){
        if (array.length == 0) return null;
        ListNode head = new ListNode(array[0]);
        ListNode tmp = head;
        for (int i = 1; i < array.length; i++) {
            tmp.next = new ListNode(array[i]);
            tmp = tmp.next;
        }
        return head;
    }

    public static List<Integer> convertListNode2List(ListNode head){
        ListNode tmp = head;
        List<Integer> res = new ArrayList<>();
        while (tmp != null){
            res.add(tmp.val);
            tmp = tmp.next;
        }

        return res;
    }
}
