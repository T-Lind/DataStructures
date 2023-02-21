package turingmachine;

import turingmachine.components.Command;
import turingmachine.highlevel.CommandList;

import java.util.ArrayList;
import java.util.HashMap;

public class TuringMachine extends CommandList {

    private ArrayList<HashMap<Integer, Command>> pages;

    private Integer awareness;
    private Integer page;

    private HashMap<Integer, Integer> tape;

    private Integer position;


    public TuringMachine() {
        pages = new ArrayList<>();
        awareness = NONE;
        page = 0;
        position = 0;
        tape = new HashMap<>();
    }

    public TuringMachine(int... defaultValues) {
        pages = new ArrayList<>();
        awareness = NONE;
        page = 0;
        tape = new HashMap<>();
        position = 0;
    }

    public TuringMachine(Integer startPos, int... defaultValues) {
        pages = new ArrayList<>();
        awareness = NONE;
        page = 0;
        tape = new HashMap<>();
        for (int i = 0; i < defaultValues.length; i++)
            tape.put(i, defaultValues[i]);
        position = startPos;
    }

    public TuringMachine(Integer startPos, Integer numItemsTape) {
        pages = new ArrayList<>();
        awareness = NONE;
        page = 0;
        tape = new HashMap<>();
        for (Integer i = 0; i < numItemsTape; i++)
            tape.put(i, 0);
        position = startPos;
    }


    public void addCommand(Integer page, Integer awareness, Command command) {
        if (page >= pages.size())
            pages.add(new HashMap<>());
        if (pages.get(page) == null)
            pages.get(page).put(awareness, command);
        pages.get(page).put(awareness,command);
    }

    public void run() {
        while (awareness != STOP) {
            pages.get(page).get(awareness).invoke(this);

        }
    }

    public Integer setAwareness(Integer awareness) {
        var old = this.awareness;
        this.awareness = removeNull(awareness);
        return removeNull(old);
    }


    public void move(Integer moveAmount) {
        position = moveAmount + position;
    }

    public void setTape(Integer value) {
        tape.put(position, value);
    }

    public Integer getTape(){
        return tape.get(position);
    }

    public void printTape(){
        for(Integer i: tape.keySet()){
            System.out.println(tape.get(i)+" ");
        }
    }

    public HashMap<Integer, Command> getPage(){
        return pages.get(page);
    }

    public void goToPage(int pageNum){
        page = pageNum;
    }

    private static Integer removeNull(Integer input){
        if(input == null)
            return NONE;
        return input;
    }
}
