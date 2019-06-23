package com.sai.demo.leetcode;

/**
 * https://leetcode.com/problems/zigzag-conversion/
 * TODO 性能太差
 */
public class ZigZagConversion {
    public static void main(String[] args) {
//        System.out.println(convert("PAYPALISHIRING", 4));
        System.out.println(convert("PAYPALISHIRING", 3));
//        System.out.println(convert("ABCD", 2));
//        System.out.println(convert("AB", 1));

//        int num = 3;
//        System.out.println(allSum(num)-num);
    }

    public static String convert(String s, int numRows) {
        if (s.length() < 2 || numRows < 2) {
            return s;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        boolean toDown = true;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            int cur = numRows - i;
            int diffIndex = allSum(numRows - i) - cur+1;
            if (diffIndex == 0) {
                diffIndex = 2;
            }
            int index = i;
            do {
                result.append(chars[index]);
                index = index + diffIndex;
            } while (index < len);

        }
        return result.toString();
    }

    public static int allSum(int num) {
        if (num == 0) {
            return 0;
        }
        return num + allSum(num - 1);
    }

    public static String convert1(String s, int numRows) {
        if (s.length() < 2 || numRows < 2) {
            return s;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        char[] resultChars = new char[numRows * len];
        int index = 0;
        int page = 0;
        boolean toDown = true;
        for (int i = 0; i < len; i++) {
            if (chars[i] == 0) {
                continue;
            }
            if (index > 0 && page % numRows == 0) {
                toDown = true;
            } else if (page == (numRows - 1)) {
                toDown = false;
            }
            resultChars[len * page + index] = chars[i];
            if (toDown) {
                ++page;
            } else {
                ++index;
                --page;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < resultChars.length; i++) {
            char tmp = resultChars[i];
            if (tmp != 0) {
                result.append(tmp);
            }
        }
        return result.toString();
    }


    public static String convert2(String s, int numRows) {
        if (s.length() < 2 || numRows < 2) {
            return s;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        char[][] resultChars = new char[numRows][len];
        int col = 0;
        int row = 0;
        boolean toDown = true;
        for (int i = 0; i < len; i++) {
            if (chars[i] == 0) {
                break;
            }
            if (col > 0 && row == 0) {
                toDown = true;
            } else if (row == (numRows - 1)) {
                toDown = false;
            }
            resultChars[row][col] = chars[i];
            if (toDown) {
                ++row;
            } else {
                ++col;
                --row;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < len; j++) {
                char tmp = resultChars[i][j];
                if (tmp != 0) {
                    result.append(tmp);
                }
            }
        }
        return result.toString();
    }
}
