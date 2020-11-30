package com.sai.demo.leetcode;

public class DivideTwoIntegers {
    public static void main(String[] args) {
        divide(7,3);
    }

    public static int divide(int dividend, int divisor) {
        if(dividend == 0 || divisor == 0){
            return 0;
        }
        int result =0;
        System.out.println(dividend & divisor);
        System.out.println(dividend | divisor);
        System.out.println(dividend % divisor);
        System.out.println(dividend / divisor);
        System.out.println((dividend << divisor) >> (dividend % divisor));
        return result;
    }
}
