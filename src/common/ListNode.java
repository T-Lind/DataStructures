package common;

public interface ListNode<T> extends NodeFramework<T> {
    ListNode<T> getNext();

    ListNode<T> setNext(ListNode<T> next);
}
