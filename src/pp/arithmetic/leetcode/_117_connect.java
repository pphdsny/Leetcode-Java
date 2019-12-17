package pp.arithmetic.leetcode;

import pp.arithmetic.model.Node;

/**
 * Created by wangpeng on 2019-12-16.
 * 117. 填充每个节点的下一个右侧节点指针 II
 *
 * 给定一个二叉树
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 *  
 *
 * 进阶：
 *
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *  
 *
 * 示例：
 *
 * https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/15/117_sample.png
 *
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 *  
 *
 * 提示：
 *
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _117_connect {

    public static void main(String[] args) {
        //[1,2,3,4,5,null,6,7,null,null,null,null,8]
        //[1,#,2,3,#,4,5,6,#,7,#]
        //[1,#,2,3,#,4,5,6,#,7,8,#]
        _117_connect connect = new _117_connect();
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);
        node.left.left = new Node(4);
        node.left.right = new Node(5);
        node.right.right = new Node(6);
        node.left.left.left = new Node(7);
        node.right.right.right = new Node(8);
        connect.connect(node);
        System.out.println();
    }

    /**
     * 解题思路：题目和{@link _116_connect}类似，唯一区别是此题不是完美二叉树，可能存在子树为空，所以不能使用116的解法，
     * 得求解每一层需要连接的左右子树
     * 
     * @param root
     * @return
     */
    public Node connect(Node root) {
        Node cur = root;
        while (cur != null) {
            Node dummy = new Node();
            Node tail = dummy;
            //遍历 cur 的当前层
            while (cur != null) {
                if (cur.left != null) {
                    tail.next = cur.left;
                    tail = tail.next;
                }
                if (cur.right != null) {
                    tail.next = cur.right;
                    tail = tail.next;
                }
                cur = cur.next;
            }
            //更新 cur 到下一层
            cur = dummy.next;
        }
        return root;
    }

}
