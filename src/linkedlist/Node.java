package linkedlist;

import common.ListNode;

public class Node<T> implements ListNode<T> {
    private T value;
    
    private ListNode<T> next;
    
    public Node(T value){
        this.value = value;
    }
    
    
    @Override
    public ListNode<T> getNext() {
        return next;
    }

    @Override
    public ListNode<T> setNext(ListNode<T> next) {
        var prev = this.next;
        this.next = next;
        return prev;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public T set(T value) {
        var prev = this.value;
        this.value = value;
        return prev;
    }
}
