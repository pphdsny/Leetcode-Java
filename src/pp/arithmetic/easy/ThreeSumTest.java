package pp.arithmetic.easy;

import java.util.*;

/**
 * Created by wangpeng on 2017/1/11.
 */
public class ThreeSumTest {
    public static void main(String[] args) {
        int[] ints = {-15, 13, 6, -11, -4, 5, -13, 5, 3, 2, 6, -1, 4, 12, -10, -13, -7, -4, -5, 6, 9, -14, 1, -6, 13, 7, -8, 10, -4, 11, -8, -3, 1, 5, -7, 4, -13, -13, -5, -3, 4, -14, 11, -14, 5, -13, -12, 13, -10, -10, -4, -15, 13, 13, -14, 11, -3, -15, 6, 1, 3, 5, 13, -11, -5, -9, 1, -2, -14, 11, 10, 5, 4, -1, 6, -6, -7, 9, -15, -2, 7, -12, -10, 5, -14, 13, -6, -9, 6, 7, 7, -6, -2, -3, -9, 0, -5, 7, 5, -4, -5, -7, -13, 14, 7, 8, -15, 7, -5, -15, -10, 9};
        List<List<Integer>> lists = threeSum(ints);
        System.out.println(lists);
        List<List<Integer>> lists2 = threeSum2(ints);
        System.out.println(lists2);
    }

    /**
     * https://leetcode.com/problems/3sum/
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        //时间太长待优化
        long startTime = System.currentTimeMillis();
        Arrays.sort(nums);
        List<List<Integer>> retList = new LinkedList();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            HashMap<Integer, Integer> keys = new HashMap<Integer, Integer>();
            for (int j = i + 1; j < nums.length; j++) {
                if (keys.containsKey(-nums[i] - nums[j])) {
                    List<Integer> tempList = new ArrayList();
                    tempList.add(nums[i]);
                    tempList.add(-nums[i] - nums[j]);
                    tempList.add(nums[j]);
                    if (retList.contains(tempList)) {
                        continue;
                    }
                    retList.add(tempList);
                } else {
                    keys.put(nums[j], j);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("threeSum花费时间："+(endTime-startTime));
        return retList;
    }

    public static List<List<Integer>> threeSum2(int[] num) {
        long startTime = System.currentTimeMillis();
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < num.length - 2; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
                int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while (lo < hi && num[lo] == num[lo + 1]) lo++;
                        while (lo < hi && num[hi] == num[hi - 1]) hi--;
                        lo++;
                        hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("threeSum2花费时间："+(endTime-startTime));
        return res;
    }
}
