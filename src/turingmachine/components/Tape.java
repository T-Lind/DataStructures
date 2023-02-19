package turingmachine.components;

public class Tape {
    private final int len;
    private Item[] data;

    public Tape(int len){
        this.len = len;
        data = new Item[len];
        for(int i=0;i<len;i++)
            data[i] = new Item(false);
    }

    public Tape(int... ints) {
        len = ints.length;
        data = new Item[len];
        for(int i=0;i<len;i++)
            data[i] = new Item(ints[i]);
    }
    public Tape(boolean... booleans)  {
        len = booleans.length;
        data = new Item[len];
        for(int i=0;i<len;i++)
            data[i] = new Item(booleans[i]);
    }

    public Digit set(int idx, Digit newState){
        return data[idx].set(newState);
    }

    public Digit get(int idx){
        return data[idx].get();
    }

    public Item getItem(int idx){
        return data[idx];
    }

    public int size(){
        return len;
    }

    public String toString(){
        StringBuilder ret = new StringBuilder();
        for(Item item: data)
            ret.append(item);
        for(int i=ret.length()-4;i>0;i-=4){
            ret.insert(i, " ");
        }
        return ret.toString();
    }
}
