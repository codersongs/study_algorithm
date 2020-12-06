package com.codersongs.algorithm.compete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Compete {
    public static void main(String[] args) {
        Compete compete = new Compete();
//        System.out.println(compete.canFormArray(new int[]{85}, new int[][]{
//                new int[]{85}
//        }));
//        System.out.println(compete.canFormArray(new int[]{15,88}, new int[][]{
//                new int[]{88},
//                new int[]{15}
//        }));
//        System.out.println(compete.canFormArray(new int[]{49,18,16}, new int[][]{
//                new int[]{16,18,49}
//        }));
//        System.out.println(compete.canFormArray(new int[]{91,4,64,78}, new int[][]{
//                new int[]{78},
//                new int[]{4,64},
//                new int[]{91}
//        }));
//        System.out.println(compete.canFormArray(new int[]{1,3,5,7}, new int[][]{
//                new int[]{2,4,6,8}
//        }));
        System.out.println(compete.countVowelStrings(1));
        System.out.println(compete.countVowelStrings(2));
        System.out.println(compete.countVowelStrings(3));
        System.out.println(compete.countVowelStrings(33));
        System.out.println(compete.countVowelStrings(50));

    }

    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, int[]> memory = new HashMap<>();
        for (int[] piece : pieces) {
            memory.put(piece[0], piece);
        }
        int i = 0;
        while (i < arr.length) {
            int[] tmp = memory.get(arr[i]);
            if (tmp == null){
                return false;
            }
            for (int value : tmp) {
                if (arr[i] != value) {
                    return false;
                }
                i++;
            }
        }
        return true;
    }
    int res;
    public int countVowelStrings(int n) {
        res = 0;
        for (int i = 1; i <= 5; i++) {
            dfs(i, 1, n);
        }
        return res;
    }

    private void dfs(int chose, int level, int max) {
        if (level == max){
            res++;
            return;
        }
        for (int i = chose; i <= 5; i++) {
            dfs(i, level+1, max);
        }
    }

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        List<Integer> index = new ArrayList<>();
        List<Integer> need = new ArrayList<>();
        for (int i = 1; i < heights.length; i++) {
            if (heights[i] > heights[i-1]){
                index.add(i);
                need.add(heights[i]-heights[i-1]);
            }
        }
        //选择使用梯子的位置
        if (index.size() <= ladders){
            return heights.length - 1;
        }
        //使用二分的方式逼近最佳位置
        while (true){

        }
    }
}
