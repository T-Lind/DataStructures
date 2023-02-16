package unrolledlinkedlist;

public class UnrolledNode<T> {
    private T[] values;

    private short stored = 0;
    private short nItemsToStore;

    private UnrolledNode<T> next;

    public UnrolledNode(int nItemsToStore) {
        values = (T[]) new Object[nItemsToStore];
        this.nItemsToStore = (short)nItemsToStore;
    }

    public UnrolledNode(int nItemsToStore, T startVal) {
        values = (T[]) new Object[nItemsToStore];
        values[0] = startVal;
        this.nItemsToStore = (short)nItemsToStore;
    }


    public UnrolledNode<T> getNext() {
        return next;
    }

    public UnrolledNode<T> setNext(UnrolledNode<T> next) {
        var prev = this.next;
        this.next = next;
        return prev;
    }

    public T get(int idx) {
        return values[idx];
    }

    public T set(int idx, T value) {
        if(idx <= stored){
            var prev = values[idx];
            values[idx] = value;
            return prev;
        }
        return null;
    }

    public boolean add(T value) {
        if (stored < nItemsToStore) {
            values[stored] = value;
            stored++;
            return true;
        } else {
            return false;
        }
    }

    public T removeLast(){
        stored--;
        if(stored < 0){
            throw new IndexOutOfBoundsException("Tried to remove  anegative index of an Unrolled Node!");
        }
        var end = values[stored];
        values[stored] = null;
        return end;
    }

    public short size(){
        return stored;
    }

    public String toString(){
        String retStr = "";
        for(int i=0;i<stored;i++){
            retStr += values[i].toString()+", ";
        }
        return retStr;
    }
}
