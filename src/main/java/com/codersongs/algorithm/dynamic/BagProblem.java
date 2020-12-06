package com.codersongs.algorithm.dynamic;

import java.util.Arrays;

public class BagProblem {
    public static void main(String[] args) {
        BagProblem bagProblem = new BagProblem();
//        System.out.println(bagProblem.partBag(
//                new float[]{3, 1, 9},
//                new float[]{12,10, 9},
//                10
//        ));

        System.out.println(bagProblem.backPack2(new int[]{2,3,5,7}, new int[]{1,5,2,4}, 10));
        System.out.println(bagProblem.backPack2(new int[]{1,2,3}, new int[]{1,2,3}, 5));

        System.out.println(bagProblem.backPack3(new int[]{2,3,5,7}, new int[]{1,5,2,4}, 10));
        System.out.println(bagProblem.backPack3(new int[]{1,2,3}, new int[]{1,2,3}, 5));

        System.out.println(bagProblem.backPack4(new int[]{2, 3, 4}, new int[]{10, 6, 4}, new int[]{5, 2, 3}, 10));
    }

    public int maxSubArray(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        int ans = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(nums[i], nums[i] + max);
            ans = Math.max(ans, max);
        }
        return ans;
    }

    public int maxProduct(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int n = nums.length;
        int[] dpMax = new int[n+1];
        int[] dpMin = new int[n+1];
        dpMax[0] = 1;
        dpMin[0] = 1;
        for (int i = 0; i < n; i++) {
            //dpMax[i+1]为nums[i]和 包含之前元素 * 当前元素的最大值，dpMin[i+1]同理
            int max = Math.max(dpMax[i] * nums[i], dpMin[i] * nums[i]);
            int min = Math.min(dpMax[i] * nums[i], dpMin[i] * nums[i]);
            dpMax[i+1] = Math.max(max, nums[i]);
            dpMin[i+1] = Math.min(min, nums[i]);
            ans = Math.max(ans, dpMax[i+1]);
        }
        return ans;
    }

    public float partBag(float[] w, float[] v, float target){
        float res = 0.0f;
        if (w == null || w.length == 0){
            return res;
        }
        WeightValue[] array = new WeightValue[w.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = new WeightValue(w[i], v[i]);
        }
        Arrays.sort(array);

        for (WeightValue weightValue : array) {
            if (target <= weightValue.weight){
                //剩余部分全部选择
                res += target * weightValue.avg;
                break;
            }
            res += weightValue.value;
            target -= weightValue.weight;
        }
        return res;
    }

    static class WeightValue implements Comparable{
        float weight;
        float value;
        float avg;

        public WeightValue(float weight, float value) {
            this.weight = weight;
            this.value = value;
            this.avg = value / weight;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof WeightValue){
                WeightValue other = (WeightValue) o;
                if (this.avg > other.avg){
                    return -1;
                }else if (this.avg < other.avg){
                    return 1;
                } else {
                    return 0;
                }
            }
            return 0;
        }

        @Override
        public String toString() {
            return "WeightValue{" +
                    "weight=" + weight +
                    ", value=" + value +
                    ", avg=" + avg +
                    '}';
        }
    }

    public int backPack2(int m, int[] A) {
        int[] f = new int[m + 1];

        for (int i = 0; i < A.length; i++) {
            for (int j = m; j >= A[i]; j--) {
                f[j] = Math.max(f[j], f[j - A[i]] + A[i]);
            }
        }
        return f[m];
    }

    /**
     * 思路一：
     * f(i)表示当背包大小为i时，获得的最优解，显然f(0)=0。
     * 初始条件: f(0)=0;
     * 状态转移方程：f(j) = max{f(j), f(j-A[i]) + A[i]}(i ∈ (0, n) 且 A[i] <= j)
     * 解释：f(j)必定包含A[i](0, n)中的一个，则可以拆分为f(j-A[i]) + A[i]，子问题f(j-A[i])为最优解。
     * 这里有一个问题：f(j-A[i])中可能已经使用了A[i]，而A[i是不能使用两次的]，因此内循环不能使用递增的遍历方式。
     * @param m
     * @param A
     * @return
     */
    public int backPack(int m, int[] A) {
        int[] f = new int[m + 1];

        for (int i = 0; i < A.length; i++) {
            for (int j = m; j >= A[i]; j--) {
                f[j] = Math.max(f[j], f[j - A[i]] + A[i]);
            }
        }
        return f[m];
    }

    /**
     * 这个答案的问题在于f(i-A[j])锁得的最佳值中可能包含了A[i]
     * 如何解决这个问题呢，可以考虑外层使用A[i]，对于内层使用递减的顺序，这样可以保证：
     * 1. 每一次得到f(i)的最佳值是基于已经遍历的A[i]得到的，直到遍历所有的A[i]得到全局最佳值；
     * 2. 每次f(i)使用A[i]的时候，f(i - A[i]) + A[i]可以保证f(i-A[i])所得的最佳值是没有使用A[i]所得，这是因为内层是递减的
     * 遍历，更小的f(i)显然后使用A[i]来构建局部最佳，当更小的f(i)构建最佳值时，即使使用了A[i]，也对前面更大的值没有影响，因为更
     * 大的值已经构建完成。
     *
     * @param m
     * @param A
     * @return
     */
    public int backPackTest(int m, int[] A) {
        int[] f = new int[m + 1];
        for (int i = 0; i <=m ; i++) {
            for (int j = 0; j < A.length; j++) {
                if (i >= A[j]){
                    f[i] = Math.max(f[i], f[i-A[j]] + A[j]);
                }
            }
        }
        return f[m];
    }


    public int backPack2(int[] A, int[] V, int m){
        int[] dp = new int[m+1];
        for (int i = 0; i < A.length; i++) {
            for (int j = A[i]; j <= m; j++) {
                dp[j] = Math.max(dp[j], dp[j-A[i]] + V[i]);
            }
        }
        return dp[m];
    }

    public int backPack3(int[] A, int[] V, int m){
        int[] dp = new int[m+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < A.length; j++) {
                if (i < A[j]){
                    continue;
                }
                dp[i] = Math.max(dp[i], dp[i-A[j]] + V[j]);
            }
        }
        return dp[m];
    }

    /**
     *
     * @param A 体积
     * @param V 价值
     * @param N 数量
     * @param m 背包大小
     * @return
     */
    public int backPack4(int[] A, int[] V, int[] N, int m){
        int[] dp = new int[m+1];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < N[i]; j++) {
                for (int k = m; k >= A[i]; k--) {
                    dp[k] = Math.max(dp[k], dp[k-A[i]] + V[i]);
                }
            }
        }
        return dp[m];
    }
}
