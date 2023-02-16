//package unrolledlinkedlist;
//
//import common.ListNode;
//
//public class UnrolledLinkedList<T> {
//
//    private ListNode<T> firstItem;
//
//    public UnrolledLinkedList() {}
//
//    public void add(T value) {
//        if (size() == 0) {
//            firstItem = new UnrolledNode<T>(value);
//            return;
//        }
//        getEnd().setNext(new UnrolledNode<T>(value));
//    }
//
//    public void add(int idx, T value) {
//        if(idx == 0){
//            var nextItem = firstItem;
//            System.out.println(nextItem.get());
//            firstItem = new UnrolledNode<T>(value);
//            firstItem.setNext(nextItem);
//            return;
//        }
//        try{
//            var node = getNode(idx-1);
//            var next = node.getNext();
//            node.setNext(new UnrolledNode<T>(value));
//            node.getNext().setNext(next);
//        }
//        catch(Exception e){
//            add(value);
//        }
//    }
//
//    public T set(int idx, T value) {
//        var item = getNode(idx);
//        var oldValue = item.get();
//        item.set(value);
//        return oldValue;
//    }
//
//    public T get(int idx) {
//        return getNode(idx).get();
//    }
//
//    public T remove(int idx) {
//        if (idx == 0) {
//            var value = firstItem.get();
//            if (size() == 1) {
//                firstItem = null;
//                return value;
//            }
//            firstItem = getNode(1);
//            return value;
//        }
//        var before = getNode(idx - 1);
//        var node = before.getNext();
//        var next = node.getNext();
//        before.setNext(next);
//        return node.get();
//    }
//
//    public ListNode<T> getEnd() {
//        var end = firstItem;
//        while (end.getNext() != null)
//            end = end.getNext();
//        return end;
//    }
//
//    public ListNode<T> getNode(int idx) {
//        var end = firstItem;
//        for (int i = 0; i < idx; i++)
//            end = end.getNext();
//        return end;
//    }
//
//    public int size() {
//        int size = 0;
//        var end = firstItem;
//        while (end != null) {
//            end = end.getNext();
//            size++;
//        }
//        return size;
//    }
//
//    public String toString() {
//        StringBuilder retStr = new StringBuilder("[");
//        var currentNode = firstItem;
//        while (currentNode != null) {
//            retStr.append(currentNode.get().toString()).append(", ");
//            currentNode = currentNode.getNext();
//        }
//        var len = retStr.length();
//        retStr.delete(len - 2, len - 1);
//        retStr.append("]");
//        return retStr.toString();
//    }
//}
