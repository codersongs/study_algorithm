package com.codersongs.algorithm.binarysearch;

import java.util.Arrays;

/**
 * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 *
 * 现在，给出位于一条水平线上的房屋和供暖器的位置，找到可以覆盖所有房屋的最小加热半径。
 *
 * 所以，你的输入将会是房屋和供暖器的位置。你将输出供暖器的最小加热半径。
 *
 * 说明:
 *
 * 给出的房屋和供暖器的数目是非负数且不会超过 25000。
 * 给出的房屋和供暖器的位置均是非负数且不会超过10^9。
 * 只要房屋位于供暖器的半径内(包括在边缘上)，它就可以得到供暖。
 * 所有供暖器都遵循你的半径标准，加热的半径也一样。
 * 示例 1:
 *
 * 输入: [1,2,3],[2]
 * 输出: 1
 * 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
 * 示例 2:
 *
 * 输入: [1,2,3,4],[1,4]
 * 输出: 1
 * 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
 *
 */
public class L475findRadius {
    public static void main(String[] args) {
        L475findRadius l475findRadius = new L475findRadius();
        System.out.println(l475findRadius.findRadius(new int[]{1,2,3,4}, new int[]{1,4}));
    }

    /**
     * 思路一：穷举
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius1(int[] houses, int[] heaters) {
        if (houses.length == 0 || heaters.length == 0){
            return 0;
        }
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int res = 0;
        int i = 0;
        for (int house : houses) {
            for (i = 0; i < heaters.length - 1; i++) {
                if (Math.abs(heaters[i] - house) < Math.abs(heaters[i+1] - house)){
                    break;
                }
            }
            res = Math.max(res, Math.abs(heaters[i] - house));
        }
        return res;
    }

    /**
     * 解法二：二分查找
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius2(int[] houses, int[] heaters) {
        int res = 0;
        int n = heaters.length;

        Arrays.sort(houses);
        Arrays.sort(heaters);

        for (int house : houses) {
            //二分查找找到每个house 两侧的 heat，这两个是离 house最近的，这里我们找 house右侧的
            int left = 0;
            int right = n-1;
            if (house <= heaters[left]){
                res = Math.max(res, heaters[left]- house);
                continue;
            }
            if (house >= heaters[right]){
                res = Math.max(res, house - heaters[right]);
                continue;
            }
            while (left < right){
                int mid = left + ((right - left) >> 1);
                if(house > heaters[mid]){
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            int dist1 = heaters[right] - house;
            int dist2 = house - heaters[right-1];
            res = Math.max(res, Math.min(dist1, dist2));
        }
        return res;
    }

    /**
     * 解法三：二分查找
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        int res = 0;
        int n = heaters.length;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        for (int house : houses) {
            int left = 0;
            int right = n;
            while (left < right){
                int mid = left + ((right - left) >> 1);
                if (house > heaters[mid]){
                    left = mid + 1;
                }else {
                    right = mid;
                }
            }
            int dist1 = right == n ? Integer.MAX_VALUE : heaters[right] - house;
            int dist2 = right == 0 ? Integer.MAX_VALUE : house - heaters[right-1];
            res = Math.max(res, Math.min(dist1, dist2));
        }
        return res;
    }
}
