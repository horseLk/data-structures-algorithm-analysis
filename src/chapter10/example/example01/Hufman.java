package chapter10.example.example01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 哈夫曼编码问题
 */
public class Hufman {

    static class TreeNode {
        public char val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode (char _v) {
            this.val = _v;
            this.left = null;
            this.right = null;
        }
    }

    private Map<Character, String> tohufman(TreeNode node) {
        Map<Character, String> res = new HashMap<>();
        if (node == null) return res;
        tohufman(node, res, "");
        return res;
    }

    private void tohufman(TreeNode node, Map<Character, String> res, String str) {
        if (isLeaf(node)) {
            res.put(node.val, str);
            return;
        }
        tohufman(node.left, res, str + "0");
        tohufman(node.right, res, str + "1");
    }

    private boolean isLeaf(TreeNode node) {
        if (node.left == null && node.right == null) {
            return true;
        }
        return false;
    }

    /**
     * 给定一个字符及其出现频率的映射，生成哈夫曼树
     * @param source
     * @return
     */
    public Map<Character, String> hufman(Map<TreeNode, Integer> source) {
        int len = source.size();
        int count = 0;
        TreeNode node = null;
        while (count < len - 1) {
            List<Map.Entry<TreeNode, Integer>> finds = findTwo(source);
            source.remove(finds.get(0).getKey());
            source.remove(finds.get(1).getKey());
            node = new TreeNode('~');
            node.left = finds.get(0).getKey();
            node.right = finds.get(1).getKey();
            source.put(node, finds.get(0).getValue() + finds.get(1).getValue());
            count++;
        }
        return tohufman(node);
    }

    private List<Map.Entry<TreeNode, Integer>> findTwo(Map<TreeNode, Integer> source) {
        List<Map.Entry<TreeNode, Integer>> res = new ArrayList<>();
        Map.Entry<TreeNode, Integer> min1 = null;
        Map.Entry<TreeNode, Integer> min2 = null;
        int minVal1 = Integer.MAX_VALUE;  // 存储最小的
        int minVal2 = Integer.MAX_VALUE; // 存储次小的
        for (Map.Entry<TreeNode, Integer> entry : source.entrySet()) {
            if (entry.getValue() < minVal1) {
                min2 = min1;
                min1 = entry;
                minVal2 = minVal1;
                minVal1 = entry.getValue();
                continue;
            }
            if (entry.getValue() < minVal2) {
                min2 = entry;
                minVal2 = entry.getValue();
            }
        }
        res.add(min1);
        res.add(min2);
        return res;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode('a');
        TreeNode node2 = new TreeNode('b');
        TreeNode node3 = new TreeNode('v');
        TreeNode node4 = new TreeNode('c');
        TreeNode node5 = new TreeNode('x');
        TreeNode node6 = new TreeNode('q');
        TreeNode node7 = new TreeNode('w');

        Map<TreeNode, Integer> maps = new HashMap<>();
        maps.put(node1, 7);
        maps.put(node2, 6);
        maps.put(node3, 9);
        maps.put(node4, 1);
        maps.put(node5, 18);
        maps.put(node6, 2);
        maps.put(node7, 10);

        final Map<Character, String> hufman = new Hufman().hufman(maps);
    }
}
