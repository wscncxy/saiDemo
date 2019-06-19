package com.sai.demo.leetcode;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = 1;
        char[] chars = s.toCharArray();
        int charsLen = chars.length;
        for (int i = 0; i < charsLen; i++) {
            int curLen = 1;
            for (int j = i + 1; j < charsLen; j++) {
                if (chars[j - 1] == chars[j]) {
                    break;
                }
                if (!hasChar(chars, chars[j], i, j)) {
                    ++curLen;
                } else {
                    break;
                }
            }
            if (curLen > len) {
                len = curLen;
            }
        }
        return len;
    }

    public static boolean hasChar(char[] subChar, char c, int start, int end) {
        for (int i = start; i < end; i++) {
            if (subChar[i] == c) {
                return true;
            }
        }
        return false;
    }
}
