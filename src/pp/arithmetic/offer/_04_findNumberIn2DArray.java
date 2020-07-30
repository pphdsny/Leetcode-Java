package pp.arithmetic.offer;

/**
 * Created by wangpeng on 2020-07-29.
 * 剑指 Offer 04. 二维数组中的查找
 *
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 *  
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 *  
 *
 * 限制：
 *
 * 0 <= n <= 1000
 *
 * 0 <= m <= 1000
 *
 *  
 *
 * 注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _04_findNumberIn2DArray {

    public static void main(String[] args) {
        _04_findNumberIn2DArray findNumberIn2DArray = new _04_findNumberIn2DArray();
        int[][] matrix = {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}
        };
        System.out.println(findNumberIn2DArray.findNumberIn2DArray(matrix,5));
        System.out.println(findNumberIn2DArray.findNumberIn2DArray(matrix,20));
    }

    /**
     * 解题思路：一眼看过去，在一个有序的规则里找一个数，第一时间想到的是二分查找，看看能不能找到二分查找的规律，从实例中看
     *
     * 1.n*m的数组中，[0,0]=1肯定最小，[n-1,m-1]=30肯定最大，如果target<[0,0] || target>[n-1,m-1]肯定不存在，返回false
     * 2.如果target=[0,0] || target=[n-1,m-1]，直接返回true
     * 3.如果数组大小2*2，则直接返回结果
     * 4.找到数组的中位数[n/2,m/2] = 9
     * 5.target=[n/2,m/2]，则直接找到结果，返回true
     * 6.target<[n/2,m/2]，则可能存在的区域是[0,0]-[n-1,m/2-1]或者[0,m/2]-[n/2-1,m-1]之间(也就是说除了右下角的两块区域)，递归
     * 7.target>[n/2,m/2]，则可能存在的区域是[n/2+1,0]-[n-1,m-1]或者[0,m/2+1]-[n/2,m-1](也就是说除了左上角的两块区域)，递归
     *
     * 方法二：利用自身数组的规律求解，详见：{@link _04_findNumberIn2DArray#findNumberIn2DArray2(int[][], int)}
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int n = matrix.length;
        int m = matrix[0].length;
        return dfs(matrix, target, 0, 0, n - 1, m - 1);
    }

    private boolean dfs(int[][] matrix, int target, int sx, int sy, int ex, int ey) {
        if (!checkXY(matrix, sx, sy,ex,ey)) return false;
        int start = matrix[sx][sy];
        int end = matrix[ex][ey];
        if (target < start || target > end) return false;
        int mx = (ex + sx) / 2;
        int my = (ey + sy) / 2;
        int mid = matrix[mx][my];
        if (target < mid) {
            return dfs(matrix, target, sx, sy, ex, my-1) || dfs(matrix, target, sx, my, mx-1, ey) ;
        }
        if (target > mid) {
            return dfs(matrix, target, mx + 1, sy, ex, ey) || dfs(matrix, target, sx, my + 1, mx, ey);
        }
        return true;
    }

    //检查输入参数是否有效
    private boolean checkXY(int[][] matrix, int sx, int sy, int ex, int ey) {
        if (sx < 0 || sx > matrix.length - 1) return false;
        if (sy < 0 || sy > matrix[0].length - 1) return false;
        if (ex < 0 || ex > matrix.length - 1) return false;
        if (ey < 0 || ey > matrix[0].length - 1) return false;
        if (sx > ex || sy > ey) return false;
        return true;
    }

    /**
     * 方法二：找到数组的右上角，此位置正下方都比他大，正左方都比他小，左下角区域可大可小，以此为起始锚点
     * 1.target>锚点，锚点位置向下移动一行
     * 2.target<锚点，锚点位置向左移动一列
     * 3.target=锚点，返回结果true
     * 4.锚点移除列表边线，返回结果false
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        //选取右上角
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else if (matrix[row][col] < target) {
                row++;
            }
        }
        return false;
    }

}
