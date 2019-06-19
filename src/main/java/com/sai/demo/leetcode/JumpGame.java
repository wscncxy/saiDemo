package com.sai.demo.leetcode;

/**
 * https://leetcode.com/problems/jump-game/
 */
public class JumpGame {
    public static void main(String[] arg) {
        System.out.println(canJump(new int[]{3, 0, 8, 2, 0, 0, 1}));
    }

    public static boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        if (nums[0] == 0) {
            return false;
        }
        int lastZeroIndex = 0;
        int len = nums.length;
        int lastIndex = len - 1;
        for (int i = 0; i < nums.length; i++) {
            if (i >= lastIndex) {
                return true;
            }
            int num = nums[i];
            if (num == 0) {
                lastZeroIndex = i;
                for (int j = i + 1; j < len; j++) {
                    if (nums[j] == 0) {
                        lastZeroIndex = j;
                    } else {
                        break;
                    }
                }
                boolean cannotJumpzero = true;
                for (int z = i - 1; z >= 0; z--) {
                    if ((z + nums[z]) > lastZeroIndex) {
                        cannotJumpzero = false;
                        i = lastZeroIndex;
                        break;
                    }
                }
                if (cannotJumpzero) {
                    return false;
                }
            } else if ((i + num) >= lastIndex) {
                return true;
            }
        }
        return false;
    }
}
