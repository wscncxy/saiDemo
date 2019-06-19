package com.sai.demo.leetcode;

/**
 * https://leetcode.com/problems/add-two-numbers/
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(3);
        l1.next = new ListNode(5);
        l1.next.next = new ListNode(7);
        l1.next.next.next = new ListNode(4);
        ListNode l2 = new ListNode(7);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        l2.next.next.next = new ListNode(4);
        System.out.println(addTwoNumbers(l1, l2));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbers(l1, l2, null, false);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2, ListNode root, boolean gt10) {
        int sum = 0;
        if (l1 != null || l2 != null) {
            sum = ((l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0));
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }

            if (gt10) {
                ++sum;
            }
            root = new ListNode(sum > 9 ? sum % 10 : sum);
            root.next = addTwoNumbers(l1, l2, root.next, sum > 9);
        } else {
            if (gt10) {
                return new ListNode(1);
            }
        }
        return root;
    }
}
