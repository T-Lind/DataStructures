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


    public TuringMachine(int startPos) {
        pages = new ArrayList<>();
        awareness = 0;
        page = 0;
        position = startPos+1;
        tape = new HashMap<>();
    }

    public TuringMachine(int... defaultValues) {
        pages = new ArrayList<>();
        awareness = 0;
        page = 0;
        tape = new HashMap<>();
        for (int i = 0; i < defaultValues.length; i++)
            tape.put(i, defaultValues[i]);
        position = 1;
    }

    public TuringMachine(Integer startPos, int... defaultValues) {
        pages = new ArrayList<>();
        awareness = defaultValues[1];
        page = 0;
        tape = new HashMap<>();
        for (int i = 0; i < defaultValues.length; i++)
            tape.put(i, defaultValues[i]);
        position = startPos+1;
    }

    public TuringMachine(Integer startPos, Integer numItemsTape) {
        pages = new ArrayList<>();
        awareness = 0;
        page = 0;
        tape = new HashMap<>();
        tape.put(0, SECTION);
        for (Integer i = 1; i < numItemsTape; i++)
            tape.put(i, 0);
        position = startPos;
    }


    public void addCommand(Integer page, Integer awareness, Command command) {
        addCommand(page, awareness, command, false);
    }

    public void addCommand(Integer page, Integer awareness, Command command, boolean autoStart) {
        if (page >= pages.size())
            pages.add(new HashMap<>());
        if (pages.get(page) == null || pages.get(page).get(awareness) == null) {
            pages.get(page).put(awareness, new ArrayList<>());
        }
        if (autoStart)
            pages.get(page).get(awareness).add((m -> m.setAwareness(m.getTape())));
        pages.get(page).get(awareness).add(command);
    }

    public void beginCommand(Integer page, Integer awareness) {
//        if (page >= pages.size())
//            pages.add(new HashMap<>());
//        if (pages.get(page) == null || pages.get(page).get(awareness) == null) {
//            pages.get(page).put(awareness, new ArrayList<>());
//        }
        pages.get(page).get(awareness).add((m -> {
            m.setAwareness(m.getTape());
            m.printTape();
        }));
    }

    public ArrayList<Command> getCommands(int page, int awareness) {
        return pages.get(page).get(awareness);
    }

    public void run() {
        while (page != STOP) {
            var commands = pages.get(page).get(awareness);
            if(commands != null) {
                for (Command command : commands) {
                    command.invoke(this);
                }
            }
            else {
                throw new NullPointerException("No commands specified for page "+page+" and awareness "+awareness);
            }
        }
    }

    public Integer setAwareness(Integer awareness) {
        var old = this.awareness;
        this.awareness = removeNull(awareness);
        return removeNull(old);
    }


    public void move(Integer moveAmount) {
        position = moveAmount + position;
        while(getTape().equals(SECTION))
            position++;
    }
    public void goToNextSection() {
        boolean nextSectionFound = false;
        while(!nextSectionFound){
            nextSectionFound = getTape().equals(SECTION);
            position++;
        }
    }
    public void goToPrevSection() {
        boolean prevSectionFound = false;
        position-=2;
        while(!prevSectionFound){
            prevSectionFound = getTape().equals(SECTION);
            position--;
        }
        position+=2;
    }

    public void setTape(Integer value) {
        tape.put(position, value);
    }

    public Integer getTape() {
        return tape.get(position);
    }

    public void printTape() {
        var printStr = new StringBuilder();
        Set<Integer> keys = tape.keySet();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (Integer value : keys) {
            if (value < min)
                min = value;
            if (value > max)
                max = value;
        }
        for (int i = min; i <= max; i++) {
            var value = tape.get(i);
            if (value != null) {
                if(value != SECTION)
                    printStr.append(tape.get(i)).append(" ");
                else {
                    printStr.append(SECTION_CHAR).append(" ");
                }
            } else {
                printStr.append("Ø ");
            }
        }
        System.out.println(printStr);
    }

    public void goToPage(int pageNum) {
        page = pageNum;
    }

    public void stop() {
        goToPage(STOP);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private static Integer removeNull(Integer input) {
        if (input == null)
            return 0;
        return input;
    }
}
