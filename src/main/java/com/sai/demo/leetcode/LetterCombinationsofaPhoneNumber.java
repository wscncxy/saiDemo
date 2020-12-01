package com.sai.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsofaPhoneNumber {

    public static void main(String[] args) {

        System.out.println(letterCombinations("237"));
    }

    static String[] numLetterArray = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits == "") {
            return null;
        }
        List<String> result;
        List<char[]> numLetterList = new ArrayList<>();
        for (char digitChars : digits.toCharArray()) {
            numLetterList.add(numLetterArray[Integer.parseInt(String.valueOf(digitChars))].toCharArray());
        }
        result = getLetter(numLetterList);
        return result;
    }

    public static List<String> getLetter(List<char[]> numLetterList) {
        List<String> resultList = new ArrayList<>();
        if (numLetterList.size() == 0) {
            return resultList;
        }
        char[] chars = numLetterList.get(0);
        if (numLetterList.size() > 1) {
            List<String> subResultList = getLetter(numLetterList.subList(1, numLetterList.size()));
            for (char c : chars) {
                String cstr = String.valueOf(c);
                for (String sub : subResultList) {
                    resultList.add(cstr + sub);
                }
            }
        } else {
            for (char c : chars) {
                resultList.add(String.valueOf(c));
            }
        }
        return resultList;

    }
}
