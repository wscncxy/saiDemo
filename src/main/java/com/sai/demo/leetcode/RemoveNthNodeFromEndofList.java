package com.sai.demo.leetcode;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthNodeFromEndofList {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(3);
        l1.next = new ListNode(5);
        l1.next.next = new ListNode(7);
        l1.next.next.next = new ListNode(4);
        System.out.println(JSONObject.toJSONString(removeNthFromEnd(l1, 1)));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n < 1) {
            return head;
        }
        int len = 1;
        List<ListNode> listNodeList = new ArrayList<>();
        ListNode cur = head;
        do {
            listNodeList.add(cur);
        } while ((cur = cur.next) != null);

        int size = listNodeList.size();
        int pre = size - n - 1;
        int next = size - n + 1;
        if (pre < 0) {
            if (size < 2) {
                head = null;
            } else {
                head = listNodeList.get(1);
            }
        } else if (next >= size) {
            listNodeList.get(pre).next = null;
        } else {
            listNodeList.get(pre).next = listNodeList.get(next);
        }
        System.out.println(len);
        return head;
    }
}
