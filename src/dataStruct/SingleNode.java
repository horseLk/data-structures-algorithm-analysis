package dataStruct;

/**
 * 单链表节点
 */
public class SingleNode<T> {
    public T data;
    public SingleNode<T> next;

    public SingleNode(T t) {
        this.data = t;
        this.next = null;
    }

    public SingleNode(T t, SingleNode<T> next) {
        this.data = t;
        this.next = next;
    }

    public void print() {
        if (this == null)
            System.out.println("null");
        SingleNode<T> p = this;
        StringBuilder builder = new StringBuilder();
        while (p != null) {
            builder.append(p.data + "->");
            p = p.next;
        }
        builder.delete(builder.length() - 2, builder.length());
        System.out.println(builder.toString());

    }
}
