package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-07-05.
 * 79. 单词搜索
 * <p>
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _79_exist {

    public static void main(String[] args) {
        _79_exist exist = new _79_exist();
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(exist.exist(board, "ABCCED"));
        System.out.println(exist.exist(board, "SEE"));
        System.out.println(exist.exist(board, "ABCB"));
    }

    /**
     * 解题思路：
     * 初步构思，类似图的BFS
     * 执行用时 :20 ms, 在所有 Java 提交中击败了21.03%的用户
     * 内存消耗 :45 MB, 在所有 Java 提交中击败了75.00%的用户
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        if (board.length == 0) return false;
        boolean[][] find = new boolean[board.length][board[0].length];
        int wordIndex = 0;
        boolean isExist;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(wordIndex)) {
                    isExist = bfs(board, find, i, j, 0, word);
                    if (isExist) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    int[][] rec = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private boolean bfs(char[][] board, boolean[][] find, int rowI, int colI, int wordI, String word) {
        if (wordI >= word.length()) {
            return false;
        }
        if (rowI >= board.length) {
            return false;
        }
        boolean isExist = false;
        if (board[rowI][colI] == word.charAt(wordI)) {
            //保存已遍历的结果，防止重复死循环找
            find[rowI][colI] = true;
            if (wordI == word.length() - 1) return true;
            //向四个方向开始遍历
            for (int i = 0; i < rec.length; i++) {
                int nextRowI = rowI + rec[i][0];
                int nextColI = colI + rec[i][1];
                if (nextRowI < 0 || nextRowI >= board.length || nextColI < 0 || nextColI >= board[rowI].length) {
                    //位置越界
                    continue;
                }
                if (find[nextRowI][nextColI]) {
                    //已遍历过
                    continue;
                }
                //找到下一个单词，开始到下一个位置进行寻找
                if (board[nextRowI][nextColI] == word.charAt(wordI + 1)) {
                    isExist = bfs(board, find, nextRowI, nextColI, wordI + 1, word);
                }
                if (isExist) {
                    break;
                }
            }
            find[rowI][colI] = false;
        }
        return isExist;
    }

}
