package com.sai.demo.leetcode;

import java.math.BigDecimal;

public class StringToInteger {
    public static void main(String[] args) {
        System.out.println(myAtoi(" -91283472332"));

    }

    public static int myAtoi(String str) {
        char[] chars = str.toCharArray();
        char[] resultChar = new char[chars.length];
        boolean isStartNum = false;
        boolean hasSign = false;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (47 < c && c < 58) {
                resultChar[i] = c;
                isStartNum = true;
            } else if (!hasSign && !isStartNum) {
                if (c == 45 || c == 43) {
                    if (hasSign) {
                        break;
                    } else {
                        resultChar[i] = c;
                        hasSign = true;
                    }
                } else if (c == 32) {
                    continue;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        if (!isStartNum) {
            return 0;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < resultChar.length; i++) {
            char c = resultChar[i];
            if (c != 0) {
                result.append(c);
            }
        }

        BigDecimal bigDecimal = new BigDecimal(result.toString());
        if (bigDecimal.compareTo(BigDecimal.valueOf(Integer.MAX_VALUE)) == 1) {
            return Integer.MAX_VALUE;
        } else if (bigDecimal.compareTo(BigDecimal.valueOf(Integer.MIN_VALUE)) == -1) {
            return Integer.MIN_VALUE;
        }
        return bigDecimal.intValue();
    }
}
