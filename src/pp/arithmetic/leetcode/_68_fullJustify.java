package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2019-10-22.
 * 68. 文本左右对齐
 *
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 *
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 *
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 *
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 * 说明:
 *
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 * 示例:
 *
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * 示例 2:
 *
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *      因为最后一行应为左对齐，而不是左右两端对齐。
 *      第二行同样为左对齐，这是因为这行只包含一个单词。
 * 示例 3:
 *
 * 输入:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 *          "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/text-justification
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _68_fullJustify {

    public static void main(String[] args) {
        _68_fullJustify fullJustify = new _68_fullJustify();
        Util.printStringList(fullJustify.fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."},16));
        Util.printStringList(fullJustify.fullJustify(new String[]{"What","must","be","acknowledgment","shall","be"},16));
        Util.printStringList(fullJustify.fullJustify(new String[]{"Science","is","what","we","understand","well","enough","to","explain", "to","a","computer.","Art","is","everything","else","we","do"},20));
    }

    /**
     * 解题思路：
     * 大致想法：先确定一行放几个单词，再跟根据条件对单词进行排序
     * 1、一行能放几个单词：
     * 1.1：一个单词放下去之后占的位置是length+1(单词和单词直接至少有一个空格)
     * 1.2：按照1.1的规则循环直到需要的长度>maxWidth
     * 2、跟根据条件对单词进行排序：
     * 2.1：对于只有一个单词的行，直接从左开始摆放
     * 2.2：对于只有2个单词的行，最左和最右摆放
     * 2.3：对于多余2个单词的行，先计算单词直接平均空格有多少个，剩余空格从左到右一个单词后逐个排布（肯定不会超过总单词数）
     * 2.4：如是最后一行，则直接从左开始排序
     * <p>
     * 存储结构：maxWidth长度的数组保存
     *
     * 执行用时 :1 ms, 在所有 java 提交中击败了99.05%的用户
     * 内存消耗 :34.9 MB, 在所有 java 提交中击败了40.26%的用户
     *
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> retList = new ArrayList<>();
        List<String> lineList = new ArrayList<>();
        int leftWidth = maxWidth;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int wordWidth = word.length();
            if (leftWidth - wordWidth - lineList.size()  < 0) {
                //超过了需要换行了
                retList.add(handleSort(lineList, leftWidth, false));
                //换行重置
                leftWidth = maxWidth;
                lineList.clear();
            }
            lineList.add(word);
            leftWidth -= wordWidth;
        }
        if (lineList.size()>0){
            retList.add(handleSort(lineList, leftWidth, true));
        }

        return retList;
    }

    /**
     * 2.1：对于只有一个单词的行，直接从左开始摆放
     * 2.2：对于只有2个单词的行，最左和最右摆放
     * 2.3：对于多余2个单词的行，先计算单词直接平均空格有多少个，剩余空格从左到右一个单词后逐个排布（肯定不会超过总单词数）
     * 2.4：如是最后一行，则直接从左开始排序
     *
     * @param lineList
     * @param leftWidth
     * @param isLastLine
     * @return
     */
    private String handleSort(List<String> lineList, int leftWidth, boolean isLastLine) {
        StringBuilder builder = new StringBuilder();
        if (isLastLine) {
            for (int i = 0; i < lineList.size(); i++) {
                builder.append(lineList.get(i));
                if (i != lineList.size() - 1) {
                    builder.append(" ");
                    leftWidth--;
                } else {
                    for (int j = 0; j < leftWidth; j++) {
                        builder.append(" ");
                    }
                }
            }
        } else {
            //剩余空格数
            int empty = leftWidth;
            //相等空格数
            int equalEmpty;
            //左侧多余空格数
            int leftEmpty;
            if (lineList.size() == 1) {
                equalEmpty = empty;
                leftEmpty = 0;
            } else {
                equalEmpty = empty / (lineList.size() - 1);
                leftEmpty = empty % (lineList.size() - 1);
            }
            for (int i = 0; i < lineList.size(); i++) {
                builder.append(lineList.get(i));
                if (i != lineList.size() - 1 || lineList.size() == 1) {
                    for (int j = 0; j < equalEmpty; j++) {
                        builder.append(" ");
                    }
                    if (leftEmpty-- > 0) {
                        builder.append(" ");
                    }
                }
            }
        }

        return builder.toString();
    }
}
