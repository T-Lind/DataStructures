package doublelinkedlist;

public class DoubleLinkedList<T> {

    private Node<T> firstItem;
    private Node<T> lastItem;

    public DoubleLinkedList(){

    }
    public DoubleLinkedList(T defaultItem) {
        add(defaultItem);
    }

    public void add(T value) {
        if (size() == 0) {
            firstItem = new Node<T>(value);
            lastItem = firstItem;
            return;
        }
        var oldEnd = getEnd();
        oldEnd.setNext(new Node<T>(value));
        getEnd().setBefore(oldEnd);
        lastItem = oldEnd.getNext();
    }

    public void add(int idx, T value) {
        if(idx == 0){
            var nextItem = firstItem;
            System.out.println(nextItem.get());
            firstItem = new Node<T>(value);
            firstItem.setNext(nextItem);
            nextItem.setBefore(firstItem);
            return;
        }
//        try{
            var node = getNode(idx-1);
            var next = node.getNext();
            node.setNext(new Node<T>(value));
            node.getNext().setNext(next);
            lastItem = node.getNext();
            System.out.println("last item being set");
            lastItem.setBefore(node);
//        }
//        catch(Exception e){
//            add(value);
//        }
    }

    public T set(int idx, T value) {
        var item = getNode(idx);
        var oldValue = item.get();
        item.set(value);
        return oldValue;
    }

    public T get(int idx) {
        return getNode(idx).get();
    }

    public T remove(int idx) {
        if (idx == 0) {
            var value = firstItem.get();
            if (size() == 1) {
                firstItem = null;
                return value;
            }
            firstItem = getNode(1);
            firstItem.setBefore(null);
            return value;
        }
        if(idx == size()-1){
            lastItem = lastItem.getBefore();
        }

        var before = getNode(idx - 1);
        var node = before.getNext();
        var next = node.getNext();
        before.setNext(next);
        next.setBefore(before);
        return node.get();
    }

    public Node<T> getEnd() {
        return lastItem;
    }

    public Node<T> getNode(int idx) {
        var end = firstItem;
        for (int i = 0; i < idx; i++)
            end = end.getNext();
        return end;
    }

    public int size() {
        int size = 0;
        var end = firstItem;
        while (end != null) {
            end = end.getNext();
            size++;
        }
        return size;
    }

    public int indexOf(T item){
        var end = firstItem;
        int idx = 0;
        while (end != null) {
            if(end.get().equals(item))
                return idx;
            end = end.getNext();
            idx++;
        }
        return -1;
    }

    public String toString() {
        StringBuilder retStr = new StringBuilder("[");
        var currentNode = firstItem;
        while (currentNode != null) {
            retStr.append(currentNode.get().toString()).append(", ");
            currentNode = currentNode.getNext();
        }
        var len = retStr.length();
        retStr.delete(len - 2, len - 1);
        retStr.append("]");
        return retStr.toString();
    }

    public String toStringReverse() {
        StringBuilder retStr = new StringBuilder("[");
        var currentNode = lastItem;
        while (currentNode != null) {
            retStr.append(currentNode.get().toString()).append(", ");
            currentNode = currentNode.getBefore();
            System.out.println(currentNode);
        }
        var len = retStr.length();
        retStr.delete(len - 2, len - 1);
        retStr.append("]");
        return retStr.toString();
    }
}
