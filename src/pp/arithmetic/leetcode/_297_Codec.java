package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by wangpeng on 2019-08-16.
 * 297. 二叉树的序列化与反序列化
 * <p>
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 示例: 
 * <p>
 * 你可以将以下二叉树：
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _297_Codec {

    public static void main(String[] args) {
        _297_Codec codec = new _297_Codec();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        String serialize = codec.serialize(root);
        System.out.println(serialize);
        TreeNode deserialize = codec.deserialize(serialize);
        Util.printTree(deserialize);
    }

    // Encodes a tree to a single string.

    /**
     * 按层次遍历，序列化格式：1 2 3 null null 4 5 null null null null
     * 借助队列实现树的BFS
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode pop = queue.poll();
            builder.append(pop != null ? pop.val : null).append(" ");
            if (pop != null) {
                queue.add(pop.left);
                queue.add(pop.right);
            }
        }

        return builder.toString();

    }

    // Decodes your encoded data to tree.

    /**
     * "1 2 3 null null 4 5 null null null null"遍历数组，使用BFS反序列化生成Tree
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
        String[] split = data.split(" ");
        if (split.length == 0) return null;
        String top = split[0];
        if (top.equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(toInt(top));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty() && index < split.length) {
            TreeNode poll = queue.poll();
            String left = split[index++];
            String right = split[index++];
            if (!left.equals("null")) {
                TreeNode leftNode = new TreeNode(toInt(left));
                poll.left = leftNode;
                queue.add(leftNode);
            }
            if (!right.equals("null")) {
                TreeNode rightNode = new TreeNode(toInt(right));
                poll.right = rightNode;
                queue.add(rightNode);
            }
        }

        return root;
    }

    private int toInt(String str) {
        return Integer.parseInt(str);
    }
}
