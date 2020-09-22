package chapter04.example.example02;

import java.util.NoSuchElementException;

/**
 * 二叉平衡树的实现
 * @param <T>
 */
public class AVLTree<T extends Comparable<? super T>> {
    private static final int ALLOW_IMBALANCE = 1;
    private static class AVLNode<T> {
        T element;
        AVLNode<T> left;
        AVLNode<T> right;
        int height;

        AVLNode(T t) {
            this(t, null, null);
        }

        AVLNode(T t, AVLNode<T> left, AVLNode<T> right) {
            this.element = t;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    }

    private AVLNode<T> root;

    public AVLTree() {
        this.root = null;
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
    }

    private int height(AVLNode<T> node) {
        if (node == null) return -1;
        return node.height;
    }

    private boolean contains(T x, AVLNode<T> node) {
        if (node == null) return false;
        int comp = x.compareTo(node.element);
        if (comp > 0) {
            return contains(x, node.right);
        }
        if (comp < 0) {
            return contains(x, node.left);
        }
        return true;
    }

    private AVLNode<T> findMin(AVLNode<T> node) {
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            return findMin(node.left);
        }
        return node;
    }

    private AVLNode<T> findMax(AVLNode<T> node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private AVLNode<T> insert(T x, AVLNode<T> node) {
        if (node ==  null) {
            return new AVLNode<>(x);
        }
        int comp = x.compareTo(node.element);
        if (comp < 0)
            node.left = insert(x, node.left);
        if (comp > 0)
            node.right = insert(x, node.right);
        return balance(node);
    }

    private AVLNode<T> insertWithoutRecursion(T x, AVLNode<T> node) {
        int comp;
        while (node != null && (comp = x.compareTo(node.element)) != 0) {
            if (comp < 0) {
                node = node.left;
            }
            if (comp > 0) {
                node = node.right;
            }
        }
        if (node == null) {
            return new AVLNode<>(x);
        }
        return balance(node);
    }

    private AVLNode<T> remove(T x, AVLNode<T> node) {
        if (node == null) return null;
        int comp = x.compareTo(node.element);

        if (comp < 0) {
            node.left = remove(x, node.left);
        }
        if (comp > 0) {
            node.right = remove(x, node.right);
        }
        if (node.left != null && node.right != null) {
            node.element = findMin(node.right).element;
            remove(node.element, node.right);
        } else {
            node = node.left == null ? node.right : node.left;
        }
        return balance(node);
    }

    private AVLNode<T> balance(AVLNode<T> node) {
        if (node == null) return null;
        if (height(node.left) - height(node.right) > ALLOW_IMBALANCE) {
            if (height(node.left.left) >= height(node.left.right)) {
                node = rotateWithLeftChild(node);
            }else {
                node = doubleWithLeftChild(node);
            }
        } else if (height(node.right) - height(node.left) > ALLOW_IMBALANCE) {
            if (height(node.right.right) >= height(node.right.left)) {
                node = rotateWithRightChild(node);
            } else {
                node = doubleWithRightChild(node);
            }
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    private AVLNode<T> rotateWithLeftChild(AVLNode<T> node) {
        AVLNode<T> leftSon = node.left;
        node.left = leftSon.right;
        leftSon.right = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        leftSon.height = Math.max(height(leftSon.left), node.height) + 1;
        return leftSon;
    }

    private AVLNode<T> rotateWithRightChild(AVLNode<T> node) {
        AVLNode<T> rightSon = node.right;
        node.right = rightSon.left;
        rightSon.left = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        rightSon.height = Math.max(node.height, height(rightSon.right)) + 1;
        return rightSon;
    }

    private AVLNode<T> doubleWithLeftChild(AVLNode<T> node) {
        node.left = rotateWithRightChild(node.left);
        return rotateWithRightChild(node);
    }

    private AVLNode<T> fastDoubleWithLeftChild(AVLNode<T> node) {
        AVLNode<T> m = node.left.right;
        node.left.right = m.left;
        m.left = node.left;
        node.left = m.right;
        m.right = node;
        return m;
    }

    private AVLNode<T> doubleWithRightChild(AVLNode<T> node) {
        node.right = rotateWithLeftChild(node.right);
        return rotateWithRightChild(node);
    }

    private AVLNode<T> fastDoubleWithRightChild(AVLNode<T> node) {
        AVLNode<T> m = node.right.left;
        node.right.left = m.right;
        m.right = node.right;
        node.right = m.left;
        m.left = node;
        return m;
    }
}
