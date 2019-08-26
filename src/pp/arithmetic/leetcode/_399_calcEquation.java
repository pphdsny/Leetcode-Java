package pp.arithmetic.leetcode;

import java.util.*;

/**
 * Created by wangpeng on 2019-08-26.
 * 399. 除法求值
 * <p>
 * 给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。
 * <p>
 * 示例 :
 * 给定 a / b = 2.0, b / c = 3.0
 * 问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
 * 返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * <p>
 * 输入为: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries(方程式，方程式结果，问题方程式)， 其中 equations.size() == values.size()，即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果值均为正数。以上为方程式的描述。 返回vector<double>类型。
 * <p>
 * 基于上述例子，输入如下：
 * <p>
 * equations(方程式) = [ ["a", "b"], ["b", "c"] ],
 * values(方程式结果) = [2.0, 3.0],
 * queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 * 输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/evaluate-division
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _399_calcEquation {

    public static void main(String[] args) {
        _399_calcEquation calcEquation = new _399_calcEquation();
        List<List<String>> equations = new ArrayList<>();
        equations.add(generateList("x1", "x2"));
        equations.add(generateList("x2", "x3"));
        equations.add(generateList("x3", "x4"));
        equations.add(generateList("x4", "x5"));
        double[] values = new double[]{3.0,4.0,5.0,6.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(generateList("x1", "x5"));
        queries.add(generateList("x5", "x2"));
        queries.add(generateList("x2", "x4"));
        queries.add(generateList("x2", "x2"));
        queries.add(generateList("x2", "x9"));
        queries.add(generateList("x9", "x9"));

        //[["x1","x2"],["x2","x3"],["x3","x4"],["x4","x5"]]
        //[3.0,4.0,5.0,6.0]
        //[["x1","x5"],["x5","x2"],["x2","x4"],["x2","x2"],["x2","x9"],["x9","x9"]]
        //求解
        double[] doubles = calcEquation.calcEquation(equations, values, queries);
        for (int i = 0; i < doubles.length; i++) {
            System.out.println(doubles[i]);
        }
    }

    private static List<String> generateList(String divisor, String dividend) {
        List<String> list = new ArrayList<>();
        list.add(divisor);
        list.add(dividend);
        return list;
    }

    /**
     * 解题思路：
     * 利用HashMap保存每个参数与其他参数直接的关系，当要求解新的方程式的时候，通过之前的关系递归求解出结果
     * 方法不是最优，自己完全构思出来的，如需最优的可以参考他人的
     *
     * 执行用时 :7 ms, 在所有 Java 提交中击败了 6.74%的用户
     * 内存消耗 : 35.9 MB, 在所有 Java 提交中击败了55.00%的用户
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] ret = new double[queries.size()];
        HashMap<String, List<String>> map = new HashMap();
        //将方程式和结果拆解成map，key为参数，value为对应其他参数的值的集合
        for (int i = 0; i < equations.size(); i++) {
            String divisor = equations.get(i).get(0);
            String dividend = equations.get(i).get(1);
            double result = values[i];
            //保存方程式结果
            List<String> divisorR = map.getOrDefault(divisor, new ArrayList<>());
            divisorR.add(result + "_" + dividend);
            map.put(divisor, divisorR);
            List<String> dividendR = map.getOrDefault(dividend, new ArrayList<>());
            dividendR.add(1 / result + "_" + divisor);
            map.put(dividend, dividendR);
        }

        //求解
        for (int i = 0; i < queries.size(); i++) {
            String divisor = queries.get(i).get(0);
            String dividend = queries.get(i).get(1);
            List<String> divisorR = map.get(divisor);
            List<String> dividendR = map.get(dividend);
            //变量是否存在
            if (divisorR == null || dividendR == null) {
                ret[i] = -1.0;
                continue;
            }
            //是否相等
            if (divisor.equals(dividend)) {
                ret[i] = 1.0;
                continue;
            }
            List<String> divisors = new LinkedList<>();
            //递归求解
            Double dfs = dfs(map, divisorR, divisors, dividend);
            ret[i] = dfs == null ? -1.0 : dfs;
        }
        return ret;
    }

    private Double dfs(Map<String, List<String>> map, List<String> divisorR, List<String> divisors, String dividend) {
        for (int i = 0; i < divisorR.size(); i++) {
            double multi = 1;
            String[] split = divisorR.get(i).split("_");
            if (divisors.contains(split[1])) {
                continue;
            }
            multi *= Double.parseDouble(split[0]);
            if (split[1].equals(dividend)) {
                return multi;
            } else {
                divisors.add(split[1]);
                Double dfs = dfs(map, map.get(split[1]), divisors, dividend);
                divisors.remove(split[1]);
                if (dfs != null) {
                    return multi * dfs;
                }
            }
        }
        return null;
    }
}
