package pp.arithmetic.leetcode;

import java.util.*;

/**
 * Created by wangpeng on 2018/9/19.
 * 187.重复的DNA序列
 * <p>
 * 所有 DNA 由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。
 * 在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 * <p>
 * 编写一个函数来查找 DNA 分子中所有出现超过一次的10个字母长的序列（子串）。
 * <p>
 * 示例:
 * <p>
 * 输入: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * <p>
 * 输出: ["AAAAACCCCC", "CCCCCAAAAA"]
 *
 * @see <a href="https://lingkou.com/problems/repeated-dna-sequences/description/">repeated-dna-sequences</a>
 */
public class _187_findRepeatedDnaSequences {
    public static void main(String[] args) {
        String[] strs = {"A", "C", "G", "T"};
        int max = 1000000;
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < max; i++) {
            builder.append(strs[random.nextInt(4)]);
        }

        long start = System.currentTimeMillis();
        List<String> list = findRepeatedDnaSequences(builder.toString());
//        Util.printStringList(list);
        long end = System.currentTimeMillis();
        //830ms
        System.out.println("time:" + (end - start));
        //方法2
        start = System.currentTimeMillis();
        List<String> result = findRepeatedDnaSequences2(builder.toString());
//        Util.printStringList(result);
        end = System.currentTimeMillis();
        //150ms
        System.out.println("time2:" + (end - start));
        //方法3
        start = System.currentTimeMillis();
        List<String> result3 = findRepeatedDnaSequences3(builder.toString());
//        Util.printStringList(result);
        end = System.currentTimeMillis();
        //593ms
        System.out.println("time3:" + (end - start));
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String substring = s.substring(i, i + 10);
            Integer count = map.get(substring);
            if (count == null) {
                count = 0;
            }
            map.put(substring, ++count);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Integer count = entry.getValue();
            if (count > 1) {
                result.add(entry.getKey());
            }
        }
        return result;
    }


    public static List<String> findRepeatedDnaSequences2(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 10) {
            return result;
        }
        char[] chars = s.toCharArray();
        //空间也太大了吧，对于大数据的较实用
        int[] index = new int[1048576]; //4^10
        int[] flag = new int[26];
        flag['A' - 'A'] = 0;
        flag['C' - 'A'] = 1;
        flag['G' - 'A'] = 2;
        flag['T' - 'A'] = 3;

        int item = 0;
        for (int i = 9; i >= 0; i--) {
            item = (item << 2) + flag[chars[i] - 'A'];
        }
        index[item] = 1;
        for (int i = 10; i < chars.length; i++) {
            item = (item >> 2) | (flag[chars[i] - 'A'] << 18);
            index[item] += 1;
        }
        for (int i = 0; i < index.length; i++) {
            if (index[i] > 1) {
                result.add(transformToStr(i));
            }
        }
        return result;
    }

    private static String transformToStr(int num) {
        char[] chars = new char[]{'A', 'C', 'G', 'T'};
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < 10; j++) {
            builder.insert(0, chars[num & 3]);
            num = num >> 2;
        }
        return builder.toString();
    }

    /**
     * leetcode优秀解法
     * 数据大时候，消耗时间并不理想，还不如解法2
     * @param s
     * @return
     */
    public static List<String> findRepeatedDnaSequences3(String s) {

        Set<Integer> words = new HashSet<>();
        Set<Integer> doubleWords = new HashSet<>();
        List<String> res = new ArrayList<>();
        char[] map = new char[26];
        //map['A'-'A'] = 0; 将字符转换为数字映射
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;

        for (int i = 0; i < s.length() - 9; i++) {
            int str = 0;
            for (int j = i; j < i + 10; j++) {  //找到当前十个字符的子串
                str <<= 2;
                str |= map[s.charAt(j) - 'A'];
            }
            if (!words.add(str) && doubleWords.add(str)) { //子串出现次数大于一次时，将子串保存进结果集
                res.add(s.substring(i, i + 10));
            }
        }
        return res;
    }

}
