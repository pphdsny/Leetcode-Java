package pp.arithmetic.model;

/**
 * Created by wangpeng on 2018/9/28.
 */
public class TrieTree {
    public static final int MAX = 26;
    public char val;
    public TrieTree[] tries;
    public boolean isEnd;

    public TrieTree(char val) {
        this.val = val;
        tries = new TrieTree[MAX];
    }
}
