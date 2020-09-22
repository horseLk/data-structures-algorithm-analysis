package chapter04.practice.question33;

/**
 * 4.33 删除所有树叶
 */
public class DeleteLeaves<T> {
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

    public Node<T> delete(Node<T> node){
        if (node == null) return null;
        if (isLeaf(node)) {
            node = null;
        } else {
            if (node.right != null) {
                node.right = delete(node.right);
            }
            if (node.left != null) {
                node.left = delete(node.left);
            }
        }
        return node;
    }

    private boolean isLeaf(Node<T> node) {
        if (node == null) throw new NullPointerException();
        if (node.left != null || node.right != null) {
            return false;
        }
        return true;
    }
}
