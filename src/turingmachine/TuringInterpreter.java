package turingmachine;

import turingmachine.components.Command;
import turingmachine.components.Digit;
import turingmachine.components.ValueError;

import java.util.Arrays;
import java.util.Objects;

public class TuringInterpreter extends CommandList {
    private TuringMachine machine;

    public TuringInterpreter(String commandToFollow) throws ValueError {
        boolean infoStage = false;
        boolean commmandStage = false;


        String[] lines = commandToFollow.split("\n");


        System.out.println(Arrays.toString(lines));

        int len = 10;
        int startPos = 0;
        boolean executeAuto = false;
        TuringMachine machine = new TuringMachine(len, startPos);
        for (int i = 0; i < lines.length; i++) {
            if (Objects.equals(lines[i], BEGIN_INFO))
                infoStage = true;
            if (Objects.equals(lines[i], END_INFO)) {
                infoStage = false;
                machine = new TuringMachine(len, startPos);
            }
            if (Objects.equals(lines[i], BEGIN_COMMANDSET))
                commmandStage = true;
            if (Objects.equals(lines[i], END_COMMANDSET))
                commmandStage = false;
            if (Objects.equals(lines[i], RUN)) {
                machine.getObserver().setPosition(startPos);
                machine.getObserver().runCommand();
            }
            if (Objects.equals(lines[i], EXECUTE_AUTO) && infoStage) {
                executeAuto = true;
            }
            if (lines[i].startsWith(SIZE) && infoStage) {
                String number = lines[1].substring(SIZE.length());
                len = Integer.parseInt(number);
            }
            if (lines[i].startsWith(START_POS) && infoStage) {
                String number = lines[i].substring(START_POS.length());
                startPos = Integer.parseInt(number);
            }
            if (lines[i].startsWith(MOVE_RELATIVE) && commmandStage) {
                String number = lines[i].substring(MOVE_RELATIVE.length());
                machine.getObserver().moveRelative(Integer.parseInt(number));
            }
            if (lines[i].startsWith(SET_POSITION) && commmandStage) {
                String number = lines[i].substring(SET_POSITION.length());
                machine.getObserver().moveRelative(Integer.parseInt(number));
            }
            if (lines[i].startsWith(CMD) && commmandStage) {
                String[] subCommands = lines[i].substring(CMD.length()).split(" ");

                // Generated final variables
                TuringMachine finalMachine = machine;
                boolean finalExecuteAuto = executeAuto;
                Command newCmd = (state) -> {
                    for (int j = 0; j < subCommands.length; j++) {
                        if (Objects.equals(subCommands[j], DONE)) {
                            if (finalExecuteAuto)
                                finalMachine.getObserver().runCommand();
                            return;
                        }
                        if (Objects.equals(subCommands[j], MOVE_RELATIVE)) {
                            finalMachine.getObserver().moveRelative(Integer.parseInt(subCommands[j + 1]));
                        }
                        if (Objects.equals(subCommands[j], SET_POSITION)) {
                            finalMachine.getObserver().setPosition(Integer.parseInt(subCommands[j + 1]));
                        }
                        System.out.println(subCommands[j]);
                        if (Objects.equals(subCommands[j], SET)) {
                            if (Objects.equals(subCommands[j + 1], NOT)) {
                                if (Objects.equals(subCommands[j + 2], GET))
                                    finalMachine.getObserver().set(!finalMachine.getBool());
                                if (Objects.equals(subCommands[j + 2], STORED))
                                    finalMachine.getObserver().set(!finalMachine.getStoredBool());

                            } else {
                                if (Objects.equals(subCommands[j + 1], GET))
                                    finalMachine.getObserver().set(finalMachine.getBool());
                                if (Objects.equals(subCommands[j + 1], STORED))
                                    finalMachine.getObserver().set(finalMachine.getStoredBool());
                                if(Objects.equals(subCommands[j + 1], TRUE))
                                    finalMachine.getObserver().set(true);
                                if(Objects.equals(subCommands[j + 1], FALSE))
                                    finalMachine.getObserver().set(false);

                            }
                        }
                        if (Objects.equals(subCommands[j], SET_STORED)) {
                            finalMachine.setStored(finalMachine.getBool());
                        }
                    }
                };
                machine.getObserver().setCommand(newCmd);
            }
        } // END LOOP
        System.out.println("Len: " + len + " Start pos: " + startPos);
        System.out.println(machine.toString());
    }
}