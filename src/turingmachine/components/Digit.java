package turingmachine.components;

public class Digit {
    private boolean state;

    public Digit(int state) throws ValueException {
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

    public void set(int state) throws ValueException {
        if(state != 0 && state != 1)
            throw new ValueException("Improper state specified");
        this.state = state == 1;

    }

    public void set(boolean state){
        this.state = state;
    }

    public String toString(){
        return getInt()+"";
    }
}
