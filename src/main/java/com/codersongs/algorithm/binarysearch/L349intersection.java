package com.codersongs.algorithm.binarysearch;

import java.util.*;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 *  
 *
 * 说明：
 *
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 *
 */
public class L349intersection {
    public static void main(String[] args) {
        L349intersection l349intersection = new L349intersection();
        int[] res = l349intersection.intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2});
        System.out.println(Arrays.toString(res));
    }

    /**
     * 思路一，利用hashset 去重查询
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection1(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (Integer num : nums1) {
            set1.add(num);
        }
        Set<Integer> set2 =  new HashSet<>();
        for (Integer num :nums2){
            set2.add(num);
        }
        List<Integer> res = new ArrayList<>();
        Set<Integer> iterator = null;
        Set<Integer> other = null;
        if (set1.size() > set2.size()){
            iterator = set2;
            other = set1;
        }else {
            iterator = set1;
            other = set2;
        }

        for (Integer num : iterator) {
            if (other.contains(num)){
                res.add(num);
            }
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    /**
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (Integer n : nums2) set2.add(n);

        set1.retainAll(set2);
        
        int[] res = new int[set1.size()];
        int index = 0;
        for (int re : set1) {
            res[index++] = re;
        }
        return res;
    }
}
