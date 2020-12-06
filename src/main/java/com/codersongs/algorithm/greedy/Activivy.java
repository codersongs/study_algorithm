package com.codersongs.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class Activivy {
    public static void main(String[] args) {
        Activivy activivy = new Activivy();
        System.out.println(activivy.activity(new int[][]{
                new int[]{2,6},
                new int[]{1,4},
                new int[]{5,7},
                new int[]{6,12},
                new int[]{7,10}
        }));
    }
    
    public int activity(int[][] array){
        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int ans = 0;
        int end = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i][0] >= end){
                ans+=1;
                end = array[i][1];
            }
        }

        return ans;
    }
}
