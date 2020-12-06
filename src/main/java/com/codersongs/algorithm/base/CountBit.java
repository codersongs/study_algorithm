package com.codersongs.algorithm.base;

public class CountBit {
    public static void main(String[] args) {
        CountBit countBit = new CountBit();
        int i = 67827342;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(countBit.swar(i));
    }

    /**
     * 使用swar算法计算汉明重量
     * @param i
     * @return
     */
    public int swar(int i){
        //0101 0101，每两个为一组，先计算奇数位的1个数，必为1或0，所以每组必为00或01，然后计算偶数位1的个数，同样为00或01，将二者相加，为00，01或10
        i = (i & 0x55555555) + ((i >> 1) & 0x55555555);
        System.out.println(Integer.toBinaryString(i));
        //0011 0011 每四位为一组，每组分成两部分，各两位，与11做与运算得出的为后半部分1的个数，然后前半部分再做与运算，得出前半部分1的数量，相加即为每四个位1的数量
        i = (i & 0x33333333) + ((i >> 2) & 0x33333333);
        System.out.println(Integer.toBinaryString(i));
        //0000 1111，每八个为一组，分别计算两部分的数量求和
        i = (i & 0x0F0F0F0F) + ((i >> 4) & 0x0F0F0F0F);
        System.out.println(Integer.toBinaryString(i));
        //0000 0001 第四个八位正好是所有四部分八位的和，右移24位放到最低位正好可以得到求得的值
        i = (i * (0x01010101) >> 24);
        System.out.println(Integer.toBinaryString(i));
        return i;
    }
}
