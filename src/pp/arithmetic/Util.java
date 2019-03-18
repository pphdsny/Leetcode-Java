package pp.arithmetic;

import pp.arithmetic.leetcode._206_ReverseList;
import pp.arithmetic.model.ListNode;
import pp.arithmetic.model.TreeNode;

import java.util.List;
import java.util.Random;

/**
 * Created by wangpeng on 2018/7/9.
 */
public class Util {
    public static void printListNode(ListNode listNode) {
        if (listNode == null) {
            System.out.println("null");
            return;
        }
        ListNode head = listNode;
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
        System.out.println("--------------");
    }

    public static ListNode generateListNodeBySize(int maxSize) {
        ListNode tempNode = new ListNode(0);
        for (int i = 0; i < maxSize; i++) {
            ListNode node = new ListNode(new Random().nextInt(maxSize * maxSize));
            node.next = tempNode.next;
            tempNode.next = node;
        }
        return tempNode.next;
    }

    public static ListNode generateOrderListNode(int maxSize) {
        ListNode tempNode = new ListNode(0);
        ListNode next = tempNode;
        int preVal = 0;
        for (int i = 0; i < maxSize; i++) {
            int val = new Random().nextInt(maxSize * maxSize) + preVal;
            preVal = val;
            ListNode node = new ListNode(val);
            next.next = node;
            next = node;
        }
        return tempNode.next;
    }

    public static ListNode generateListNodeBySort(int maxSize) {
        int startNum = new Random().nextInt(maxSize);
        ListNode tempNode = new ListNode(0);
        for (int i = 0; i < maxSize; i++) {
            ListNode node = new ListNode(startNum);
            node.next = tempNode.next;
            tempNode.next = node;
            startNum = startNum + new Random().nextInt(maxSize);
        }
        return _206_ReverseList.reverseList(tempNode.next);
    }

    public static ListNode getLastNode(ListNode node) {
        ListNode lastNode = null;
        ListNode tempNode = node;
        while (tempNode != null) {
            lastNode = tempNode;
            tempNode = tempNode.next;
        }
        return lastNode;
    }

    public static int[] generateArrayBySize(int maxSize) {
        int[] nums = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            nums[i] = new Random().nextInt(maxSize + maxSize + maxSize);
        }
        return nums;
    }

    public static TreeNode generateTreeNode() {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(11);
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(2);
        TreeNode node8 = new TreeNode(5);
        TreeNode node9 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.left = node8;
        node5.right = node9;
        return root;
    }

    public static void printArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public static void printList(List<Integer> nums) {
        for (int i = 0; i < nums.size(); i++) {
            System.out.print(nums.get(i) + " ");
        }
        System.out.println();
    }

    public static void printStringList(List<String> nums) {
        if (nums == null){
            System.out.println("list is null");
            return;
        }
        if (nums.size() == 0){
            System.out.println("list size is 0");
            return;
        }
        for (int i = 0; i < nums.size(); i++) {
            System.out.print(nums.get(i) + " ");
        }
        System.out.println();
    }

    public static void printDivideLine() {
        System.out.println("-------我是分割线-------");
    }

    public static void printTree(TreeNode treeNode) {
        printTree(treeNode, 0);
    }

    private static void printTree(TreeNode treeNode, int floor) {
        if (treeNode == null) {
            return;
        }
        for (int i = 0; i < floor; i++) {
            System.out.print("----");
        }
        System.out.println("[" + treeNode.val + "]");
        printTree(treeNode.left, floor + 1);
        printTree(treeNode.right, floor + 1);
    }
}
