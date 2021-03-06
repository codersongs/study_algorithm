package com.codersongs.algorithm.linkedlist;

import com.codersongs.algorithm.base.ListNode;
import com.codersongs.algorithm.base.ListNodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 725. 分隔链表
 * 给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
 *
 * 每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。
 *
 * 这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。
 *
 * 返回一个符合上述规则的链表的列表。
 *
 * 举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ]
 *
 * 示例 1：
 *
 * 输入:
 * root = [1, 2, 3], k = 5
 * 输出: [[1],[2],[3],[],[]]
 * 解释:
 * 输入输出各部分都应该是链表，而不是数组。
 * 例如, 输入的结点 root 的 val= 1, root.next.val = 2, \root.next.next.val = 3, 且 root.next.next.next = null。
 * 第一个输出 output[0] 是 output[0].val = 1, output[0].next = null。
 * 最后一个元素 output[4] 为 null, 它代表了最后一个部分为空链表。
 * 示例 2：
 *
 * 输入:
 * root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
 * 输出: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
 * 解释:
 * 输入被分成了几个连续的部分，并且每部分的长度相差不超过1.前面部分的长度大于等于后面部分的长度。
 */
public class L725splitListToParts {
    public static void main(String[] args) {
        L725splitListToParts l725splitListToParts = new L725splitListToParts();
        l725splitListToParts.splitListToParts(ListNodeUtils.convertArray2ListNode(new int[]{0,1}), 2);
    }

    /**
     * 思路一：遍历两次链表
     * @param root
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        List<ListNode> res = new ArrayList<>();

        ListNode head = root;
        int count = 0;
        while (head != null){
            count++;
            head = head.next;
        }
        int gruopLen = count / k;
        int spare = count % k;
        head = root;
        //1,2,3,4,  5,6,7,8,  9,10,11,  12,13,14
        int base = (gruopLen + 1) * spare;
        for (int i = 0; i < count; i++) {
            ListNode headNext = head.next;
            if (base > i){
                if (i % (gruopLen + 1) == 0){
                    res.add(head);
                }
                if (i % (gruopLen + 1) == gruopLen){
                    head.next = null;
                }
            }else {
                if ((i - base) % gruopLen == 0){
                    res.add(head);
                }
                if ((i - base) % gruopLen == gruopLen - 1){
                    head.next = null;
                }
            }
            head = headNext;
        }
        int rest = k - res.size();
        for (int i = 0; i < rest; i++) {
            res.add(null);
        }
        return res.toArray(new ListNode[0]);
    }
}
