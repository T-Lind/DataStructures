package turingmachine.highlevel;

import turingmachine.components.Command;
import turingmachine.components.Stage;
import turingmachine.components.ValueException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class TuringInterpreter extends CommandList {
    private TuringMachine machine;

    public TuringInterpreter(String commandToFollow) throws ValueException {
        Stage currentStage = Stage.NONE;


        String[] lines = commandToFollow.split("\n");

        int len = 10;
        int startPos = 0;
        boolean executeAuto = false;
        boolean inCommentBlock = false;
        machine = new TuringMachine(len, startPos);
        for (int i = 0; i < lines.length; i++) {
            // Check for block comments as well as in line comments
            if(lines[i].startsWith(BLOCK_COMMENT[0])){
                inCommentBlock = true;
                continue;
            }
            if(lines[i].startsWith(BLOCK_COMMENT[1])){
                inCommentBlock = false;
                continue;
            }
            if(inCommentBlock)
                continue;
            if(lines[i].startsWith(COMMENT))
                continue;
            System.out.println(i + " " + lines[i]);


            // Defined stages
            if (Objects.equals(lines[i], BEGIN_INFO))
                currentStage = Stage.INFO;
            if (Objects.equals(lines[i], END_INFO)) {
                currentStage = Stage.NONE;
                machine = new TuringMachine(len, startPos);
            }
            if (Objects.equals(lines[i], BEGIN_COMMANDSET))
                currentStage = Stage.COMMAND;
            if (Objects.equals(lines[i], END_COMMANDSET))
                currentStage = Stage.NONE;
            if (Objects.equals(lines[i], RUN)) {
                machine.getObserver().setPosition(startPos);
                machine.getObserver().runCommand(machine);
            }

            // INFO data:
            // TODO: Implement a command for multiple tapes
            if (Objects.equals(lines[i], EXECUTE_AUTO) && currentStage == Stage.INFO) {
                executeAuto = true;
            }
            if (lines[i].startsWith(SIZE) && currentStage == Stage.INFO) {
                String number = lines[i].substring(SIZE.length());
                len = Integer.parseInt(number);
            }
            if (lines[i].startsWith(START_POS) && currentStage == Stage.INFO) {
                String number = lines[i].substring(START_POS.length());
                startPos = Integer.parseInt(number);
            }

            // Check immediate turing machine movements
            if (lines[i].startsWith(MOVE_RELATIVE) && currentStage == Stage.COMMAND) {
                String number = lines[i].substring(MOVE_RELATIVE.length());
                machine.getObserver().moveRelative(Integer.parseInt(number));
            }
            if (lines[i].startsWith(SET_POSITION) && currentStage == Stage.COMMAND) {
                String number = lines[i].substring(SET_POSITION.length());
                machine.getObserver().moveRelative(Integer.parseInt(number));
            }

            // Check command data
            if (lines[i].startsWith(CMD) && currentStage == Stage.COMMAND) {
                String[] subCommands = lines[i].substring(CMD.length()).split(" ");

                // Create an arraylist of lambdas to run at execution time (these will be assigned to an Item)
                ArrayList<Command> commands = new ArrayList<>();

                // Loop through every provided subcommand using regex
                for (int j = 0; j < subCommands.length; j++) {
                    System.out.println("    " + j + " " + subCommands[j]);

                    if (Objects.equals(subCommands[j], EXECUTE)) {
                        commands.add((state, m) -> {
                            m.getObserver().runCommand(m);
                        });
                    }


                    if (Objects.equals(subCommands[j], MOVE_RELATIVE.strip())) {
                        int amountToMove = Integer.parseInt(subCommands[j + 1]);
                        commands.add((state, m) -> m.getObserver().moveRelative(amountToMove));
                    }
                    if (Objects.equals(subCommands[j], SET_POSITION)) {
                        int finalJ1 = j;
                        commands.add((state, m) -> m.getObserver().setPosition(Integer.parseInt(subCommands[finalJ1 + 1])));
                    }
                    if (Objects.equals(subCommands[j], SET)) {
                        if (Objects.equals(subCommands[j + 1], NOT)) {
                            if (Objects.equals(subCommands[j + 2], GET)) {
                                commands.add((state, m) -> m.getObserver().set(!m.getBool()));
                            }
                            if (Objects.equals(subCommands[j + 2], STORED))
                                commands.add((state, m) -> m.getObserver().set(!m.getStoredBool()));
                        } else {
                            if (Objects.equals(subCommands[j + 1], GET))
                                commands.add((state, m) -> m.getObserver().set(m.getBool()));
                            if (Objects.equals(subCommands[j + 1], STORED))
                                commands.add((state, m) -> m.getObserver().set(m.getStoredBool()));
                            if (Objects.equals(subCommands[j + 1], TRUE)) {
                                commands.add((state, m) -> {

                                    m.getObserver().set(true);
                                });
                            }
                            if (Objects.equals(subCommands[j + 1], FALSE))
                                commands.add((state, m) -> m.getObserver().set(false));
                        }
                    }
                    if (Objects.equals(subCommands[j], SET_STORED)) {
                        commands.add((state, m) -> m.setStored(m.getBool()));
                    }

                    // On the last command, add an execute automatically if provided and set the commands to the item
                    if (j == subCommands.length - 1) {
                        if (executeAuto) {
                            commands.add((state, m) -> {
                                m.getObserver().runCommand(m);
                            });
                            machine.getObserver().setCommands(commands);
                            break;
                        }
                    }
                }
            }
        } // END LOOP
    }

    public String toString() {
        return machine.toString();
    }
}