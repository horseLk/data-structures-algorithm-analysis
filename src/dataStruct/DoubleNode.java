package dataStruct;

/**
 * 双链接节点
 */
public class DoubleNode<T> {
    public T data;
    public DoubleNode<T> prev;
    public DoubleNode<T> next;

    public DoubleNode(T t) {
        this.data = t;
        this.prev = null;
        this.next = null;
    }

    public DoubleNode(T t, DoubleNode<T> prev, DoubleNode<T> next) {
        this.data = t;
        this.prev = prev;
        this.next = next;
    }

    public void print() {
        if (this == null)
            System.out.println("null");
        DoubleNode<T> p = this;
        StringBuilder builder = new StringBuilder();
        while (p != null) {
            builder.append(p.data + "<->");
            p = p.next;
        }
        builder.delete(builder.length() - 3, builder.length());
        System.out.println(builder.toString());

    }
}
