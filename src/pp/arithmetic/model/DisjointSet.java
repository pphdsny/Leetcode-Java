package pp.arithmetic.model;

/**
 * Created by wangpeng on 2018/9/29.
 * 并查集实现
 */
public class DisjointSet {

    private int count = 0;
    private int[] id;
    private int[] size;

    public DisjointSet(int n) {
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
        count = n;
    }

    public int find(int n) {
        while (n != id[n]) {
            id[n] = id[id[n]];
            n = id[n];
        }
        return n;
    }

    public void union(int p, int q) {
        int _p = find(p);
        int _q = find(q);
        if (_p == _q) {
            return;
        }
        if (size[_p] > size[_q]) {
            id[_q] = _p;
            size[_p] += size[_q];
        } else {
            id[_p] = _q;
            size[_q] += size[_p];
        }
        count--;
    }

    public int count() {
        return count;
    }
}
