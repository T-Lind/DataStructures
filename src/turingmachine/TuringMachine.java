package turingmachine;

import turingmachine.components.Command;
import turingmachine.highlevel.CommandList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class TuringMachine extends CommandList {

    private ArrayList<HashMap<Integer, ArrayList<Command>>> pages;

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
        awareness = 0;
        page = 0;
        tape = new HashMap<>();
        position = 0;
    }

    public TuringMachine(Integer startPos, int... defaultValues) {
        pages = new ArrayList<>();
        awareness = 0;
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
        if (pages.get(page) == null || pages.get(page).get(awareness) == null){
            pages.get(page).put(awareness, new ArrayList<>());
        }
        pages.get(page).get(awareness).add((m -> m.setAwareness(m.getTape())));
        pages.get(page).get(awareness).add(command);
    }

    public void run() {
        while (page != STOP) {
            for(Command cmd: pages.get(page).get(awareness))
                cmd.invoke(this);
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
        StringBuilder printStr = new StringBuilder();
        Set<Integer> keys = tape.keySet();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(Integer value: keys){
            if(value < min)
                min = value;
            if(value > max)
                max = value;
        }
        for(int i=min;i<=max;i++){
            if(tape.get(i) != null){
                printStr.append(tape.get(i)).append(" ");
            }
            else {
                printStr.append("Ø ");
            }
        }
        System.out.println(printStr);
    }

    public void goToPage(int pageNum){
        page = pageNum;
    }

    public void stop(){
        goToPage(STOP);
    }

    private static Integer removeNull(Integer input){
        if(input == null)
            return 0;
        return input;
    }
}
