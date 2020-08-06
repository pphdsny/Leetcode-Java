package pp.arithmetic.offer;

/**
 * Created by wangpeng on 2020-08-05.
 * <p>
 * 剑指 Offer 12. 矩阵中的路径
 * <p>
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。
 * 如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 * <p>
 * [["a","b","c","e"],
 * ["s","f","c","s"],
 * ["a","d","e","e"]]
 * <p>
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 * 提示：
 * <p>
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _12_exist {

    public static void main(String[] args) {
        _12_exist exist = new _12_exist();
//        char[][] board = new char[][]{
//                {'A', 'B', 'C', 'E'},
//                {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'E'}
//        };
//        System.out.println(exist.exist(board,"ABFACED"));
//        System.out.println(exist.exist(new char[][]{
//                {'a','b'},
//                {'c','d'}
//        },"abcd"));
        System.out.println(exist.exist(new char[][]{
                {'C','A','A'},
                {'A','A','A'},
                {'B','C','D'}
        },"AAB"));
    }

    /**
     * 解题思路：
     * 从board的[0,0]开始向上、左、下、右进行深度遍历，逐步去匹配word中的字符，新建个history保存遍历路径，防止死循环
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) return false;
        int[][] history = new int[board.length][board[0].length];
        return dfs(board, word, history, 0, 0, 0);
    }

    private boolean dfs(char[][] board, String word, int[][] history, int wi, int nx, int ny) {
        if (wi >= word.length()) {
            //word遍历结束才返回true
            return true;
        }
        //遍历越界
        if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[nx].length) return false;
        //之前走过这个位置
        if (history[nx][ny] == 1) return false;
        if (board[nx][ny] == word.charAt(wi)) {
            history[nx][ny] = 1;
            if (dfs(board, word, history, wi + 1, nx, ny + 1)
                    || dfs(board, word, history, wi + 1, nx + 1, ny)
                    || dfs(board, word, history, wi + 1, nx, ny - 1)
                    || dfs(board, word, history, wi + 1, nx - 1, ny)) {
                return true;
            }
            history[nx][ny] = 0;
        }
        if (wi == 0) {
            //定位首个字符的标识位
            if (nx < board.length - 1) {
                if (dfs(board, word, history, wi, nx + 1, ny)) {
                    return true;
                }
            } else if (ny < board[0].length - 1) {
                if (dfs(board, word, history, wi, 0, ny + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

}
