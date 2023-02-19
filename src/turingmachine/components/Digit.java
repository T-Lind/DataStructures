package turingmachine.components;

public class Digit {
    private boolean state;

    public Digit(int state) {
        set(state);
    }

    public Digit(boolean state){
        set(state);
    }

    public int getInt(){
        if(state)
            return 1;
        return 0;
    }

    public Boolean getBoolean(){
        return state;
    }

    public void set(int state) {
        if(state != 0 && state != 1)
            return;
        this.state = state == 1;

    }

    public void set(boolean state){
        this.state = state;
    }

    public String toString(){
        return getInt()+"";
    }
}
