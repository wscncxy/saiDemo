package com.sai.demo.leetcode;

/**
 * https://leetcode.com/problems/container-with-most-water/
 * TODO 性能不行
 */
public class MaxArea {
    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    public static int maxArea(int[] height) {
        int maxArear = 0;
        for (int i = 0; i < height.length - 1; i++) {
            int curHeight = height[i];
            int secondHeight = 0;
            for (int j = height.length - 1; j > i; j--) {
                if (height[j] > secondHeight) {
                    secondHeight = height[j];
                    int curArea = 0;
                    int line = j - i;
                    if (height[j] > curHeight) {
                        curArea = curHeight * line;
                    } else {
                        curArea = height[j] * line;
                    }
                    if (curArea > maxArear) {
                        maxArear = curArea;
                    }
                }else{
                    break;
                }
            }
        }
        return maxArear;
    }
}
