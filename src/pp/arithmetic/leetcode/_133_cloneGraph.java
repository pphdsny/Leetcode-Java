package pp.arithmetic.leetcode;


import java.util.*;

/**
 * Created by wangpeng on 2020-01-20.
 * 133. 克隆图
 * <p>
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * <p>
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 *  
 * <p>
 * 测试用例格式：
 * <p>
 * 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1，第二个节点值为 2，以此类推。该图在测试用例中使用邻接列表表示。
 * <p>
 * 邻接列表是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
 * <p>
 * 给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：adjList = [[2,4],[1,3],[2,4],[1,3]]
 * 输出：[[2,4],[1,3],[2,4],[1,3]]
 * 解释：
 * 图中有 4 个节点。
 * 节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
 * 节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
 * 节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
 * 节点 4 的值是 4，它有两个邻居：节点 1 和 3 。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：adjList = [[]]
 * 输出：[[]]
 * 解释：输入包含一个空列表。该图仅仅只有一个值为 1 的节点，它没有任何邻居。
 * 示例 3：
 * <p>
 * 输入：adjList = []
 * 输出：[]
 * 解释：这个图是空的，它不含任何节点。
 * 示例 4：
 * <p>
 * <p>
 * <p>
 * 输入：adjList = [[2],[1]]
 * 输出：[[2],[1]]
 *  
 * <p>
 * 提示：
 * <p>
 * 节点数介于 1 到 100 之间。
 * 每个节点值都是唯一的。
 * 无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
 * 由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
 * 图是连通图，你可以从给定节点访问到所有节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/clone-graph
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _133_cloneGraph {

    public static void main(String[] args) {
        _133_cloneGraph cloneGraph = new _133_cloneGraph();
        Node node1 = new Node();
        node1.val = 1;
        Node node2 = new Node();
        node2.val = 2;
        Node node3 = new Node();
        node3.val = 3;
        Node node4 = new Node();
        node4.val = 4;
        //[[2,4],[1,3],[2,4],[1,3]]
        node1.neighbors = new ArrayList<>();
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors = new ArrayList<>();
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors = new ArrayList<>();
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors = new ArrayList<>();
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        Node clone = cloneGraph.cloneGraph(node1);
        System.out.println();
    }

    /**
     * 解题思路：
     * 先花了很大的力气读题目，最后发现就是图的深度遍历，由于每个节点值都是唯一的，用一个HashMap保存遍历过的节点，防止无限循环
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if (node == null){
            return null;
        }
        HashMap<Integer, Node> map = new HashMap<>();
        Node cloneNode = dfs(node, map);
        return cloneNode;
    }

    private Node dfs(Node node, HashMap<Integer, Node> map) {
        if (map.get(node.val) != null) {
            return map.get(node.val);
        }
        Node cloneNode = new Node();
        cloneNode.val = node.val;
        map.put(node.val, cloneNode);
        if (node.neighbors != null) {
            cloneNode.neighbors = new ArrayList<>();
            for (int i = 0; i < node.neighbors.size(); i++) {
                cloneNode.neighbors.add(dfs(node.neighbors.get(i), map));
            }
        }
        return cloneNode;
    }

    private static class Node {
        public int val;
        public List<Node> neighbors;
    }
}
