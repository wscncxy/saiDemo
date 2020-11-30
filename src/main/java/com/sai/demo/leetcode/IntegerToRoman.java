package com.sai.demo.leetcode;

public class IntegerToRoman {

    public static void main(String[] args) {
        System.out.println(int2Rm2(3, 1));
    }

    public static String int2Rm2(int num, int index) {
        int base = getBaseNum(index);
        int rmCodeIndex = base/10;
        if(base> 10000){
            return "";
        }
        int curNum = (num / rmCodeIndex) % 10;

        if (curNum == 0) {
            if (num >= 10) {
                return int2Rm2(num, ++index);
            } else {
                return "";
            }
        }

        boolean is9 = curNum == 9;
        if (is9) {
            return int2Rm2(num, ++index)+getRmCode(rmCodeIndex) + getRmCode(base);
        }

        boolean less4 = curNum < 4;
        String curRm = less4?getRmCode(rmCodeIndex):getRmCode(rmCodeIndex*5);

        String result = "";
        if (less4) {
            for (int i = 0; i < curNum; i++) {
                result += curRm;
            }
        } else {
            result = curRm;
            String padRam = getRmCode(rmCodeIndex);
            if (curNum < 5) {
                result = padRam + result;
            } else {
                int limit = curNum - 5;
                for (int i = 0; i < limit; i++) {
                    result += padRam;
                }
            }
        }
        return int2Rm2(num, ++index) + result;
    }

    public static String getRmCode(int num) {
        switch (num) {
            case 1:
                return "I";
            case 5:
                return "V";
            case 10:
                return "X";
            case 50:
                return "L";
            case 100:
                return "C";
            case 500:
                return "D";
            case 1000:
                return "M";
        }
        return "";
    }

    public static int getBaseNum(int count) {
        int result = 1;
        for (int i = 0; i < count; i++) {
            result = result * 10;
        }
        return result;
    }


}
