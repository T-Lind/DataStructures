package common;

public interface BasicNode<T> {
    BasicNode<T> getNext();

    BasicNode<T> setNext(BasicNode<T> next);
}
