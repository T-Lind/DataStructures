package unrolledlinkedlist;

import common.ListNode;
import common.NodeFramework;

public class UnrolledNode<T> {
    private T[] values;
    
    private ListNode<T> next;
    
    public UnrolledNode(int nItems){
        values = (T[]) new Object[nItems];
    }
    
    
    public ListNode<T> getNext() {
        return next;
    }

    public ListNode<T> setNext(ListNode<T> next) {
        var prev = this.next;
        this.next = next;
        return prev;
    }

    public T get(int idx) {
        return values[idx];
    }

    public T set(int idx, T value) {
        var prev = values[idx];
        values[idx] = value;
        return prev;
    }
}
