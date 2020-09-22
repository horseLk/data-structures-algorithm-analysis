package chapter04.practice.question13;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * TreeSet的实现2 ： 每个节点上还要指向当前节点的上一个和下一个节点
 * @param <T>
 */
public class MyTreeSet2<T extends Comparable<? super T>> implements Iterable<T> {
    private static class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;
        Node<T> prev;
        Node<T> next;

        public Node(T t) {
            this(t, null, null, null, null);
        }

        public Node(T t, Node<T> _left, Node<T> _right, Node<T> _prev, Node<T> _next) {
            this.element = t;
            this.left = _left;
            this.right = _right;
            this.prev = _prev;
            this.next = _next;
        }
    }

    private Node<T> root;
    private int modCount;
    private Node<T> header;
    private Node<T> tail;

    public MyTreeSet2() {
        root = null;
        header.next = tail;
        tail.prev = header;
    }

    public void makeEmpty() {
        modCount++;
        root = null;
        header.next = tail;
        tail.prev = header;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public T findMin() {
        if (isEmpty()) throw new NoSuchElementException();
        return header.next.element;
    }

    public T findMax() {
        if (isEmpty()) throw new NoSuchElementException();
        return tail.prev.element;
    }

    public void insert(T x) {
        root = insert(x, root, header, tail);
    }

    public void remove(T x){
        root = remove(x, root, root.prev, root.next);
    }

    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private boolean contains(T x, Node<T> node) {
        if (node == null) return false;
        int comp = x.compareTo(node.element);
        if (comp < 0) {
            return contains(x, node.left);
        }
        if (comp > 0) {
            return contains(x, node.right);
        }
        return true;
    }

    private Node<T> findMin(Node<T> node) {
        if (node == null) return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node<T> findMax(Node<T> node) {
        if (node == null) return null;
        if (node.right == null) {
            return node;
        }
        return findMin(node.right);
    }

    private Node<T> insert(T x, Node<T> node, Node<T> prev, Node<T> next) {
        if (node == null) {
            Node<T> newNode = new Node<>(x);
            prev.next = newNode;
            newNode.prev = prev;
            newNode.next = next;
            next.prev = newNode;
            modCount++;
        }
        int comp = x.compareTo(node.element);
        if (comp < 0) {
            node.left = insert(x, node.left, node.left.prev, node.left.next);
        }
        if (comp > 0) {
            node.right = insert(x, node.right, node.right.prev, node.right.next);
        }
        return node;
    }

    private Node<T> remove(T x, Node<T> node, Node<T> prev, Node<T> next) {
        if (root == null) return null;
        int comp = x.compareTo(node.element);
        if (comp < 0) {
            node.left = remove(x, node.left, node.left.prev, node.left.next);
        } else if (comp > 0) {
            node.right = remove(x, node.left, node.right.prev, node.right.next);
        } else {
            if (node.left != null && node.right != null) {
                node.element = node.next.element;
                node.next = node.next.next;
                node.right = remove(node.element, node.right, node.prev, node.next);
            } else if (node.left == null) {
                node.prev.next = node.right;
                node.right.prev = node.prev;
                node = node.right;
            } else {
                node.next.prev = node.left;
                node.left.next = node.prev;
                node = node.left;
            }
            modCount++;
        }
        return node;
    }



    private class MyIterator implements Iterator<T> {
        private Node<T> current = (Node<T>) header.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            T value = current.element;
            current = current.next;
            okToRemove = true;
            return value;
        }

        @Override
        public void remove() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            MyTreeSet2.this.remove(current.prev.element);
            expectedModCount++;
            okToRemove = false;
        }
    }
}
