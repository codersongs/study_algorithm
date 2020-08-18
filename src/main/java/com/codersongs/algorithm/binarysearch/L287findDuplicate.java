package com.codersongs.algorithm.binarysearch;

/**
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 *
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 */
public class L287findDuplicate {
    public static void main(String[] args) {
        L287findDuplicate l287findDuplicate = new L287findDuplicate();
        System.out.println(l287findDuplicate.findDuplicate(new int[]{1,3,4,2,2}));
    }

    /**
     * 解法一：二分查找，核心思想：
     * ①.当target < i时，cnt[i] <= i
     * ②.当target >= i时，cnt[i] > i
     * @param nums
     * @return
     */
    public int findDuplicate1(int[] nums) {
        //最小值为1，最大值为nums.length - 1
        int left = 1;
        int right = nums.length - 1;
        int ans = -1;
        while (left <= right){
            int cnt = 0;
            int mid = left + ((right - left) >> 1);
            for (int num : nums) {
                if (num <= mid){
                    cnt++;
                }
            }
            if (cnt <= mid){
                left = mid + 1;
            }else {
                right = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }

    /**
     * 解法二：位运算，这个有点奇技淫巧了
     * 这个方法我们来将所有数二进制展开按位考虑如何找出重复的数，如果我们能确定重复数每一位是 1 还是 0 就可以按位还原出重复的数是什么。
     *
     * 考虑到第 i 位，我们记 \textit{nums}[]nums[] 数组中二进制展开后第 ii 位为 11 的数有 xx 个，数字 [1,n][1,n] 这 nn 个数二进制展开后第 ii 位为 11 的数有 yy 个，那么重复的数第 ii 位为 11 当且仅当 x>yx>y。
     *
     * 仍然以示例 1 为例，如下的表格列出了每个数字二进制下每一位是 11 还是 00 以及对应位的 xx 和 yy 是多少：
     *
     *  	1	3	4	2	2	x	y
     * 第 0 位	1	1	0	0	0	2	2
     * 第 1 位	0	1	0	1	1	3	2
     * 第 2 位	0	0	1	0	0	1	1
     * 那么按之前说的我们发现只有第 11 位 x>yx>y ，所以按位还原后 \textit{target}=(010)_2=(2)_{10}target=(010)
     * 2
     * ​
     *  =(2)
     * 10
     * ​
     *  ，符合答案。
     *
     * 正确性的证明其实和方法一类似，我们可以按方法一的方法，考虑不同示例数组中第 ii 位 11 的个数 xx 的变化：
     *
     * 如果测试用例的数组中 \textit{target}target 出现了两次，其余的数各出现了一次，且 \textit{target}target 的第 ii 位为 11，那么 \textit{nums}[]nums[] 数组中第 ii 位 11 的个数 xx 恰好比 yy 大一。如果\textit{target}target 的第 ii 位为 00，那么两者相等。
     * 如果测试用例的数组中 \textit{target}target 出现了三次及以上，那么必然有一些数不在 \textit{nums}[]nums[] 数组中了，这个时候相当于我们用 \textit{target}target 去替换了这些数，我们考虑替换的时候对 xx 的影响：
     * 如果被替换的数第 ii 位为 11，且 \textit{target}target 第 ii 位为 11：xx 不变，满足 x>yx>y。
     * 如果被替换的数第 ii 位为 00，且 \textit{target}target 第 ii 位为 11：xx 加一，满足 x>yx>y。
     * 如果被替换的数第 ii 位为 11，且 \textit{target}target 第 ii 位为 00：xx 减一，满足 x\le yx≤y。
     * 如果被替换的数第 ii 位为 00，且 \textit{target}target 第 ii 位为 00：xx 不变，满足 x\le yx≤y。
     * 也就是说如果 \textit{target}target 第 ii 位为 11，那么每次替换后只会使 xx 不变或增大，如果为 00，只会使 xx 不变或减小，始终满足 x>yx>y 时 \textit{target}target 第 ii 位为 11，否则为 00，因此我们只要按位还原这个重复的数即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        int n = nums.length, ans = 0;
        int bit_max = 31;
        while (((n - 1) >> bit_max) == 0) {
            bit_max -= 1;
        }

        for (int bit = 0; bit <= bit_max; ++bit) {
            int x = 0, y = 0;
            for (int i = 0; i < n; ++i) {
                if ((nums[i] & (1 << bit)) != 0) {
                    x += 1;
                }
                if (i >= 1 && ((i & (1 << bit)) != 0)) {
                    y += 1;
                }
            }
            if (x > y) {
                ans |= 1 << bit;
            }
        }
        return ans;
    }

    /**
     * 解法三：弗洛伊德判圈算法
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
