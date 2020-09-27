package chapter08.example.example01;

public class DisjSets {
    private int[] s;

    public DisjSets(int elementCount) {
        s = new int[elementCount];
        for (int i = 0; i < elementCount; i++) {
            s[i] = -1;
        }
    }

    public int find(int x) {
        if (s[x] < 0) {
            return x;
        }
        return find(s[x]);
    }

    /**
     * 改进的find方法，使用了路径压缩
     * @param x
     * @return
     */
    public int findPro(int x) {
        if (s[x] < 0) {
            return x;
        } else {
            s[x] = find(s[x]);
            return s[x];
        }
    }

    public void union(int root1, int root2) {
        s[root2] = root1;
    }

    /**
     * 改进的按高度求并的方法，数组中的元素是对应树的高度的相反数
     * @param root1
     * @param root2
     */
    public void unionPro(int root1, int root2) {
        // s[root2]中的树高度更高，因此将s[root1]合并到s[root2]
        if (s[root2] < s[root1]) {
            s[root1] = root2;
        } else {
            // 两者高度相等，则合并后高度会增高，即数组中的元素值 - 1
            if (s[root1] == s[root2]) {
                s[root1]--;
            }
            // s[root1]中的树高度更高，因此将s[root2]合并到s[root1]
            s[root2] = root1;
        }
    }
}
