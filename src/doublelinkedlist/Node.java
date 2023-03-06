package doublelinkedlist;

public class Node<T> {
    private T value;

    private Node<T> next;
    private Node<T> before;

    public Node(T value) {
        this.value = value;
    }


    public Node<T> getNext() {
        return next;
    }

    public Node<T> getBefore() {
        return before;
    }

    public Node<T> setNext(Node<T> next) {
        var prev = this.next;
        this.next = next;
        return prev;
    }

    public Node<T> setBefore(Node<T> before) {
        var prev = this.before;
        this.before = before;
        return prev;
    }

    public T get() {
        return value;
    }

    public T set(T value) {
        var prev = this.value;
        this.value = value;
        return prev;
    }
}
