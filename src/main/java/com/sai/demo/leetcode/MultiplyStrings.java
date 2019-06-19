package com.sai.demo.leetcode;

import com.alibaba.fastjson.JSONObject;

/**
 * https://leetcode.com/problems/multiply-strings/
 */
public class MultiplyStrings {

    public static void main(String[] args) {
        System.out.println(JSONObject.toJSONString(multiply("98", "9")));
    }

    public static String multiply(String num1, String num2) {
        char[] num1Chars = num1.toCharArray();
        if (num1Chars[0] == 48) {
            return "0";
        }

        char[] num2Chars = num2.toCharArray();
        if (num2Chars[0] == 48) {
            return "0";
        }
        int len1 = num1Chars.length;
        int len2 = num2Chars.length;

        int[] tempChars = new int[len1 + len2];
        int pre = 0;
        for (int i = len1; i > 0; i--) {
            int index = 0;
            for (int j = len2; j > 0; j--) {
                index = i + j - 1;
                int tmp = (((int) num1Chars[i - 1]) - 48) * (((int) num2Chars[j - 1]) - 48) + pre + tempChars[index];
                tempChars[index] = tmp % 10;
                if (tmp > 9) {
                    pre = tmp / 10;
                } else {
                    pre = 0;
                }
            }
            if (pre > 0) {
                tempChars[index - 1] = pre;
            }
            pre = 0;
        }

        String sb = "";
        boolean start = false;
        for (int tmp : tempChars) {
            if (tmp > 0) {
                start = true;
            }
            if (start) {
                sb += tmp;
            }
        }
        return sb;
    }
}
