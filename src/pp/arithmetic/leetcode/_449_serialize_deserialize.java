package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2018/9/27.
 * 449.序列化和反序列化二叉搜索树
 * <p>
 * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
 * <p>
 * 设计一个算法来序列化和反序列化二叉搜索树。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 * <p>
 * 编码的字符串应尽可能紧凑。
 * <p>
 * 注意：不要使用类成员/全局/静态变量来存储状态。 你的序列化和反序列化算法应该是无状态的。
 *
 * @see <a href="https://leetcode-cn.com/problems/serialize-and-deserialize-bst/description/">serialize-and-deserialize-bst</a>
 */
public class _449_serialize_deserialize {
    public static void main(String[] args) {
        TreeNode srcRoot = new TreeNode(2);
        TreeNode leftTree = new TreeNode(0);
        TreeNode rightTree = new TreeNode(3);
        srcRoot.left = leftTree;
        srcRoot.right = rightTree;
        leftTree.left = new TreeNode(-4);
        leftTree.right = new TreeNode(1);
        Util.printTree(srcRoot);
        String serialize = serialize(srcRoot);
        System.out.println(serialize);
        Util.printDivideLine();
        TreeNode deserialize = deserialize(serialize);
        Util.printTree(deserialize);
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        dfs(root, builder);
        return builder.toString();
    }

    private static void dfs(TreeNode node, StringBuilder builder) {
        if (node == null) {
            return;
        }
        builder.append(node.val + "#");
        dfs(node.left, builder);
        dfs(node.right, builder);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] array = data.split("#");
        if (array.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(array[0]));
        for (int i = 1; i < array.length; i++) {
            insert(root, new TreeNode(Integer.parseInt(array[i])));
        }
        return root;
    }

    private static void insert(TreeNode root, TreeNode insert) {
        if (insert.val <= root.val) {
            if (root.left == null) {
                root.left = insert;
            } else {
                insert(root.left, insert);
            }
        } else {
            if (root.right == null) {
                root.right = insert;
            } else {
                insert(root.right, insert);
            }
        }
    }
}
