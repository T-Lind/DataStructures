package turingmachine;

public class Tape {
    private final int len;
    private Item[] data;

    public Tape(int len){
        this.len = len;
        data = new Item[len];
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
}
