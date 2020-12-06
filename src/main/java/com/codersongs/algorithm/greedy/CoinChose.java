package com.codersongs.algorithm.greedy;

public class CoinChose {
    public static void main(String[] args) {
        CoinChose coinChose = new CoinChose();
        System.out.println(coinChose.coinChose(new int[]{3,2,1,3,0,2}, 620));
    }

    /**
     * 硬币选择问题，
     * @param N
     * @param target
     * @return
     */
    public int coinChose( int[] N, int target){
        int[] V = new int[]{1,5,10,50,100,500};
        int res = 0;
        for (int i = 5; i >= 0; i--) {
            int count = Math.min(target / V[i], N[i]);
            target -= count * V[i];
            res += count;
        }
        return res;
    }
}
