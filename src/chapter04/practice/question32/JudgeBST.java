package chapter04.practice.question32;

/**
 * 4.32 判断一个树是否满足二叉查找树的条件
 */
public class JudgeBST<T extends Comparable<? super T>> {
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

    public boolean isBST(Node<T> root) {
        if (root == null) return true;
        if (root.left != null && root.right != null) {
            if (root.element.compareTo(findMax(root.left)) > 0 &&
                    root.element.compareTo(findMin(root.right)) < 0) {
                return isBST(root.left) && isBST(root.right);
            }
            return false;
        }
        if (root.left != null) {
            if (root.element.compareTo(findMax(root.left)) > 0) {
                return isBST(root.left);
            }
            return false;
        }
        if (root.right != null) {
            if (root.element.compareTo(findMin(root.right)) < 0) {
                return isBST(root.right);
            }
            return false;
        }
        return true; // 两个子节点都为空
    }

    private T findMin(Node<T> node) {
        if (node == null) return null;
        while (node.left != null) {
            node = node.left;
        }
        return node.element;
    }

    private T findMax(Node<T> node) {
        if (node == null) return null;
        while (node.right != null) {
            node = node.right;
        }
        return node.element;
    }
}
