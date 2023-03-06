package hashtable;

import java.lang.reflect.Array;
import java.util.LinkedList;

class Hashtable<K> {
    private Func<K> cvt;

    private LinkedList<K>[] array;


    public Hashtable(int len) {
        this(len, (input) -> (int) input);
    }

    public Hashtable(int len, Func<K> cvt) {
        this.cvt = cvt;

        array = (LinkedList<K>[]) Array.newInstance(LinkedList.class, len);

        for (int i = 0; i < array.length; i++) {
            array[i] = new LinkedList<>();
        }

    }

    public void add(K item) {
        int hashCode = cvt.formula(item);
        if(!array[hashCode].contains(item))
            array[hashCode].add(item);
    }


    public String toString(){
        var retStr = new StringBuilder();
        for(int i=0;i<array.length;i++){
            var list = array[i];
            retStr.append(i).append(": ").append(list.toString()).append("\n");
        }
        return retStr.toString();
    }
}
