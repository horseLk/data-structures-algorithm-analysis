package chapter04.practice.question31;

public class NodeCount<T extends Comparable<? super T>> {
    public static class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;

        public Node(T t) {
            this(t, null, null);
        }

        public Node(T t, Node<T> _left, Node<T> _right) {
            this.element = t;
            this.left = _left;
            this.right = _right;
        }
    }

    /*
        所有节点的个数
     */
    public int nodeCount(Node<T> root) {
        return nodeCount(0, root);
    }

    /*
        树叶节点个数
     */
    public int leafNodeCount(Node<T> root) {
        if (root == null) {
            return 0;
        }
        int num = 0;
        if (root.left == null && root.right == null){
            num++;
            return num;
        }
        if (root.left != null) {
            num += leafNodeCount(root.left);
        }
        if (root.right != null) {
            num += leafNodeCount(root.right);
        }
        return num;
    }

    public int fullNodeCount(Node<T> root) {
        if (root == null) return 0;
        int num = 0;
        if (root.left != null && root.right != null) {
            num++;
        }
        if (root.left != null) {
            num += fullNodeCount(root.left);
        }
        if (root.right != null) {
            num += fullNodeCount(root.right);
        }
        return num;
    }

    private int nodeCount(int num, Node<T> node) {
        if (node != null) {
            num++;
            num += nodeCount(0, node.left);
            num += nodeCount(0, node.right);
        }
        return num;
    }

}
