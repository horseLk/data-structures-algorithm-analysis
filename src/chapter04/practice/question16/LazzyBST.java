package chapter04.practice.question16;

import java.util.NoSuchElementException;

/**
 * 4.16 懒惰删除的BST
 */
public class LazzyBST<T extends Comparable<? super T>> {
    private static class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;
        boolean isDel;

        public Node(T t) {
            this(t, null, null);
        }

        public Node(T t, Node<T> _left, Node<T> _right) {
            this.element = t;
            this.left = _left;
            this.right = _right;
            this.isDel = false;
        }
    }

    private Node<T> root;
    private int totalNode; // 总结点数
    private int delNode; // 被删除的节点数

    public LazzyBST() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null || totalNode == delNode;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public T findMin() {
        if (isEmpty()) throw new NoSuchElementException();
        return findMin(root).element;
    }

    public T findMax() {
        if (isEmpty()) throw new NoSuchElementException();
        return findMax(root).element;
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    public void remove(T x) {
        root = remove(x, root);
        if (delNode >= totalNode - delNode) {
            root = remove(root);
            totalNode = totalNode - delNode;
            delNode = 0;
        }
    }

    private boolean contains(T x, Node<T> node) {
        if (root == null) return false;
        int comp = x.compareTo(node.element);
        if (comp < 0) {
            return contains(x, node.left);
        }
        if (comp > 0) {
            return contains(x, node.right);
        }
        if (node.isDel == false) {
            return true;
        }
        return false;
    }

    private Node<T> findMin(Node<T> node) {
        if (node == null) return null;
        Node<T> minNode = null;
        while (node.left != null) {
            if (node.isDel == false) minNode = node;
            node = node.left;
        }
        if (minNode == null) minNode = findMin(node.right);
        return minNode;
    }

    private Node<T> findMax(Node<T> node) {
        if (node == null) return null;
        Node<T> maxNode = null;
        while (node.right != null) {
            if (node.isDel == false) maxNode = node;
            node = node.right;
        }
        if (maxNode == null) maxNode = findMax(node.left);
        return maxNode;
    }

    private Node<T> insert(T x, Node<T> node) {
        if (node == null) {
            totalNode++;
            return new Node<>(x);
        }
        int comp = x.compareTo(node.element);
        if (comp < 0) {
            node.left = insert(x, node.left);
        }
        if (comp > 0) {
            node.right = insert(x, node.right);
        }
        if (comp == 0) {
            if (node.isDel == true) {
                delNode--;
            }
            node.isDel = false;
        }
        return node;
    }

    private Node<T> remove(T x, Node<T> node) {
        if (node == null) {
            return node;
        }
        int comp = x.compareTo(node.element);
        if (comp < 0) {
            node.left = insert(x, node.left);
        }
        if (comp > 0) {
            node.right = insert(x, node.right);
        }
        if (comp == 0) {
            if (node.isDel == false) {
                delNode++;
            }
            node.isDel = true;
        }
        return node;
    }

    private Node<T> remove(Node<T> node) {
        if (node == null) return null;
        if (node.isDel == true) {
            node = abstractDel(node);
        }
        node.left = remove(node.left);
        node.right = remove(node.right);
        return node;
    }

    private Node<T> abstractDel(Node<T> node) {
        if (node.left != null && node.right != null) {
            Node<T> minNode = findMin(node.right);
            node.element = minNode.element;
            abstractDel(minNode);
        } else
            node = node.left != null ? node.left : node.right;
        return node;
    }
}
