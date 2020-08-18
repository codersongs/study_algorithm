package com.codersongs.algorithm.dynamic;

/**
 * 300. 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 */
public class L300lengthOfLIS {
    public static void main(String[] args) {
//        L300lengthOfLIS l300lengthOfLIS = new L300lengthOfLIS();
//        System.out.println(l300lengthOfLIS.lengthOfLIS(new int[]{
//                10,9,2,5,3,7,101,18
//                4,10,4,3,8,9
//        }));
    }

    /**
     * 包含 i的 dp[i]
     * 可以确定的一点是 最大值一定是包含第i个值的最大值
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0){

        }
        return 0;
    }
}
