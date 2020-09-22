package chapter04.practice.question11;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 4.11 TreeSet的实现类，并使用迭代器，在每个节点上添加一个指向其父节点的链
 * @param <T>
 */
public class MyTreeSet<T extends Comparable<? super T>> implements Iterable<T> {
    private static class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;
        Node<T> parent;

        public Node(T data) {
            this(data, null, null, null);
        }

        public Node(T data, Node<T> _left, Node<T> _right, Node<T> _parent) {
            element = data;
            left = _left;
            right = _right;
            parent = _parent;
        }
    }

    private Node<T> root; // 根节点
    private int modCount; // 操作次数

    public MyTreeSet() {
        root = null;
    }

    public MyTreeSet(Node<T> node) {
        root = node;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean contains(T t) {
        return contains(t, root);
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
        root = insert(x, root, null);
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    private boolean contains(T x, Node<T> node) {
        if (node == null) return false;
        int comp = x.compareTo(node.element);
        if (comp > 0) return contains(x, root.right);
        if (comp < 0) return contains(x, root.left);
        return true;
    }

    private Node<T> findMin(Node<T> node) {
        if (node == null) return null;
        while (node.left != null) {
            return findMin(node.left);
        }
        return node;
    }

    private Node<T> findMax(Node<T> node) {
        if (node == null) return null;
        if (node.right != null) return findMax(node.right);
        return node;
    }

    private Node<T> insert(T x, Node<T> node, Node<T> parent) {
        if (node == null) {
            modCount++;
            return new Node<>(x, null, null, parent);
        }
        int comp = x.compareTo(node.element);
        if (comp < 0) {
            node.left = insert(x, node.left, node);
        }
        if (comp > 0) {
            node.right = insert(x, node.right, node);
        }
        return node;
    }

    private Node<T> remove(T x, Node<T> node) {
        if (node == null) return null;
        int comp = x.compareTo(node.element);
        if (comp > 0) {
            node.right = remove(x, node.right);
        } else if (comp < 0) {
            node.left = remove(x, node.left);
        } else if (node.left != null && node.right != null) {
            node.element = findMin(node.right).element;
            node.right = remove(node.element, node.right);
        } else { // 被删除的节点只有一个或者零个子节点
            modCount++;
            Node<T> oneChild;
            oneChild = node.left == null ? node.right : node.left;
            oneChild.parent = node.parent;
            node = oneChild;
        }
        return node;
    }

    public Iterator iterator() {
        return new MyTreeSetIterator();
    }

    private class MyTreeSetIterator implements Iterator<T> {
        private Node<T> current = findMin(root);
        private Node<T> previous;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        boolean atEnd = false;


        @Override
        public boolean hasNext() {
            return !atEnd;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T value = current.element;
            previous = current;
            if (current.right != null) {
                current = findMin(current.right);
            } else {
                Node<T> child = current;
                current = current.parent;
                while (current != null && current.left != null) {
                    child = current;
                    current = current.parent;
                }
                if (current == null) {
                    atEnd = true;
                }
            }
            okToRemove = true;
            return value;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            expectedModCount++;
            MyTreeSet.this.remove(previous.element);
            okToRemove = false;
        }
    }
}
