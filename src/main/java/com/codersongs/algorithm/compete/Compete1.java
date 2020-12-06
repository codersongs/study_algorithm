package com.codersongs.algorithm.compete;

import java.util.*;

public class Compete1 {
    public static void main(String[] args) {
        Compete1 compete1 = new Compete1();
//        System.out.println(compete1.getMaximumGenerated(2));
//        System.out.println(compete1.getMaximumGenerated(3));
//        System.out.println(compete1.getMaximumGenerated(7));
//        System.out.println(compete1.getMaximumGenerated(0));
//        System.out.println(compete1.getMaximumGenerated(1));
//        System.out.println(compete1.minDeletions("ceabaacb"));
//        System.out.println(compete1.minDeletions("ceabaacb"));

//        System.out.println(compete1.maxProfit(new int[]{2,5}, 4));
//        System.out.println(compete1.maxProfit(new int[]{3,5}, 6));
//        System.out.println(compete1.maxProfit(new int[]{2,8,4,10,6}, 20));
//        System.out.println(compete1.maxProfit(new int[]{1000000000}, 1000000000));
//        System.out.println(compete1.maxProfit(new int[]{773160767}, 252264991));
        //[497978859,167261111,483575207,591815159]
        //836556809
        System.out.println(compete1.maxProfit(new int[]{497978859,167261111,483575207,591815159}, 836556809));

//        long res = 0;
//        for (int i = 773160767; i > 773160767 - 252264991; i--) {
//            res += i;
//        }
//        System.out.println(163222581212825552L % 1000000007);
//        System.out.println((int)(163222581212825552L % (Math.pow(10, 9) + 7)));
    }

    public int getMaximumGenerated(int n) {
        if (n <= 1){
            return n;
        }
        int res = 1;
        int[] memory = new int[n+1];
        memory[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0){
                memory[i] = memory[i / 2];
                continue;
            }
            memory[i] = memory[i / 2] + memory[i / 2 + 1];
            res = Math.max(res, memory[i]);
        }
        return res;
    }

    /**
     * 1.先统计26个字母的分别出现次数，再针对相同的次数，从数字最大的开始删除
     * @param s
     * @return
     */
    public int minDeletions(String s) {
        int[] array = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            array[c - 97] += 1;
        }
        List<Integer> allNonzeroCount = new ArrayList<>();
        for (int i : array) {
            if (i != 0){
                allNonzeroCount.add(i);
            }
        }
        Collections.sort(allNonzeroCount);
//        System.out.println(allNonzeroCount);
        Set<Integer> set = new HashSet<>(allNonzeroCount);
        boolean isZero = false;
        int res = 0;
        for (int i = allNonzeroCount.size() - 2; i >= 0; i--) {
            if (allNonzeroCount.get(i).equals(allNonzeroCount.get(i+1))){
                if (isZero){
                    res += allNonzeroCount.get(i);
                    continue;
                }
                int tmp = allNonzeroCount.get(i) - 1;
                //从当前值开始往下减
                while (tmp >= 0){
                    if (set.contains(tmp)){
                        tmp--;
                        continue;
                    }
                    res += (allNonzeroCount.get(i) - tmp);
                    set.add(tmp);
                    if (tmp == 0){
                        isZero = true;
                    }
                    break;
                }
            }
        }
        return res;
    }


    public int maxProfit(int[] inventory, int orders) {
        long res = 0;
        if (orders == 0 || inventory == null || inventory.length == 0){
            return 0;
        }
        Arrays.sort(inventory);
        for (int i = 0; i < inventory.length / 2; i++) {
            int temp = inventory[i];
            inventory[i] = inventory[inventory.length - i - 1];
            inventory[inventory.length - i - 1] = temp;
        }
        while (true){
            for (int i = 1; i < inventory.length; i++) {
                if (inventory[i] == inventory[i-1]){
                    continue;
                }
                int sub = inventory[i-1] - inventory[i];
                //orders 总数大于差值
                if (orders >= sub * i){
//                    long sum = 0;
//                    for (int j = inventory[i]+1; j <= inventory[i-1]; j++) {
//                        sum += j;
//                    }
                    //使用等差数列进行优化
                    long sum = sub * ((long)inventory[i] + 1 + (long)inventory[i-1]) / 2;
                    System.out.println(sum);
                    res += (i * sum);
                    orders -= (sub * i);
                    for (int j = 0; j < i; j++) {
                        inventory[j] = inventory[i];
                    }
                    continue;
                }
                //orders 总数小于差值，只需计算最终结果，无需修改inventory
                long line = orders / i;
                long spare = orders % i;
//                long sum = 0;
//                int j;
//                for (j = inventory[i-1]; j > inventory[i-1] - line; j--) {
//                    sum += j;
//                }
//                等差数列优化
                long sum = line * ((long)inventory[i-1] + (long)inventory[i-1] - line + 1) / 2;
                res += (sum * (long)i);
                res += ((inventory[i-1] - line) * spare);
                return (int) (res % 1000000007);
            }
            //所有的数字都相等
            if (inventory[0] == inventory[inventory.length-1]){
                long line = orders / inventory.length;
                long spare = orders % inventory.length;
//                long sum = 0;
//                int j;
//                for (j = inventory[0]; j > inventory[0] - line; j--) {
//                    sum += j;
//                }
                long sum = line * ((long)inventory[0] + (long)inventory[0] - line + 1) / 2;
                res += (sum * inventory.length);
                res += ((inventory[0] - line) * spare);
                return (int) (res % 1000000007);
            }
        }

    }

}
