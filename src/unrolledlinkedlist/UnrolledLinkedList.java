package unrolledlinkedlist;

public class UnrolledLinkedList<T> {

    private UnrolledNode<T> firstItem;

    private int nItemsPerNode;

    public UnrolledLinkedList() {
        nItemsPerNode = 5;
    }

    public UnrolledLinkedList(int nItemsPerNode) {
        this.nItemsPerNode = nItemsPerNode;
    }

    public void add(T value) {
        if (size() == 0) {
            firstItem = new UnrolledNode<T>(nItemsPerNode);
        }
        var end = getEnd();
        var success = end.add(value);
        if(!success){
            end.setNext(new UnrolledNode<T>(nItemsPerNode));
            end.getNext().add(value);
        }
    }

    public T removeLast(){
        return getEnd().removeLast();
    }

    public T set(int idx, T value) {
        var item = getNode(idx / nItemsPerNode);
        var arrayIndex = idx % nItemsPerNode;
        var oldValue = item.get(arrayIndex);
        item.set(arrayIndex, value);
        return oldValue;
    }

    public T get(int idx) {
        return getNode(idx / nItemsPerNode).get(idx % nItemsPerNode);
    }


    public UnrolledNode<T> getEnd() {
        var end = firstItem;
        while (end.getNext() != null)
            end = end.getNext();
        return end;
    }

    public UnrolledNode<T> getNode(int idx) {
        var end = firstItem;
        for (int i = 0; i < idx; i++)
            end = end.getNext();
        return end;
    }

    public int size() {
        int size = 0;
        var end = firstItem;
        if(end == null)
            return 0;
        if(end.getNext() == null)
            return end.size();
        size++;
        while (end.getNext() != null) {
            end = end.getNext();
            size++;
        }
        return size * nItemsPerNode - (nItemsPerNode - end.size());
    }

    public String toString() {
        StringBuilder retStr = new StringBuilder("[");
        var currentNode = firstItem;
        while (currentNode != null) {
            retStr.append(currentNode);
            currentNode = currentNode.getNext();
        }
        var len = retStr.length();
        retStr.delete(len - 2, len - 1);
        retStr.append("]");
        return retStr.toString();
    }
}
