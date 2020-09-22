package chapter04.example.example01;

import java.nio.BufferUnderflowException;

/**
 * 二叉查找树的实现
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<? super T>> {
    private static class BinaryNode<T> {
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

        BinaryNode(T t) {
            this.element = t;
            this.left = null;
            this.right = null;
        }

        BinaryNode(T t, BinaryNode<T> _left, BinaryNode<T> _right) {
            this.element = t;
            this.left = _left;
            this.right = _right;
        }
    }

    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public T findMin() {
        if (isEmpty()) throw new BufferUnderflowException();
        return findMin(root).element;
    }

    public T findMax() {
        if (isEmpty()) throw new BufferUnderflowException();
        return findMax(root).element;
    }

    public void insert(T x){
        root = insert(x, root);
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    public void printTree() {
        if (isEmpty()) System.out.println("Empty Tree");
        else printTree(root);
    }

    private boolean contains(T x, BinaryNode<T> root) {
        if (root == null) return false;
        int compareRes = x.compareTo(root.element);
        if (compareRes == 0) return true;
        if (compareRes > 0) return contains(x, root.right);
        return contains(x, root.left);
    }

    private BinaryNode<T> findMin(BinaryNode<T> node) {
        if (node == null) {
            return null;
        }else if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }

    private BinaryNode<T> findMax(BinaryNode<T> node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private BinaryNode<T> insert(T x, BinaryNode<T> node) {
        if (node == null) return new BinaryNode<>(x, null, null);
        int comp = x.compareTo(node.element);
        if (comp > 0) {
            node.right =  insert(x, node.right);
        }
        if (comp < 0) {
            node.left =  insert(x, node.left);
        }
        return node;

    }

    private BinaryNode<T> remove(T x, BinaryNode<T> node) {
        if (node == null) return node;
        int comp = x.compareTo(node.element);
        if (comp < 0) {
            node.left = remove(x, node.left);
        } else if (comp > 0) {
            node.right = remove(x, node.right);
        } else if (node.left != null && node.right != null) {
            node.element = findMin(node.right).element;
            node.right = remove(node.element, node.right);
        } else {
            node = node.left == null ? node.right : node.left;
        }
        return node;
    }

    private void printTree(BinaryNode<T> node) {
        if (node != null) {
            printTree(node.left);
            System.out.println(node.element);
            printTree(node.right);
        }
    }
}
