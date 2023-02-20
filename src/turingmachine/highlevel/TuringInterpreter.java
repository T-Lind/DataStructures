package turingmachine.highlevel;

import org.jetbrains.annotations.NotNull;
import turingmachine.components.Command;
import turingmachine.components.Stage;
import java.util.*;

@Deprecated
public class TuringInterpreter extends CommandList {
    private TuringMachine machine;
    private final HashMap<String, Boolean> runtimeData;

    public TuringInterpreter(@NotNull String commandToFollow) {
        Stage currentStage = Stage.NONE;
        runtimeData = new HashMap<>();
        String[] lines = commandToFollow.split("\n");

        int len = 10;
        int startPos = 0;

        // Starting runtime data
        runtimeData.put("executeAuto", false);
        runtimeData.put("inCommentBlock", false);
        runtimeData.put("debugPrint", false);
        machine = new TuringMachine(len, startPos);
        for (int i = 0; i < lines.length; i++) {
            // Check for block comments as well as in line comments
            if (lines[i].startsWith(BLOCK_COMMENT[0])) {
                runtimeData.replace("inCommentBlock", true);
                continue;
            }
            if (lines[i].startsWith(BLOCK_COMMENT[1])) {
                runtimeData.replace("inCommentBlock", false);
                continue;
            }
            if (runtimeData.get("inCommentBlock"))
                continue;
            if (lines[i].startsWith(COMMENT))
                continue;

            if (runtimeData.get("debugPrint"))
                System.out.println(i + " " + lines[i]);


            // Defined stages
            if (Objects.equals(lines[i], BEGIN_INFO))
                currentStage = Stage.INFO;
            if (Objects.equals(lines[i], END_INFO)) {
                currentStage = Stage.COMMAND;
                machine = new TuringMachine(len, startPos);
            }
            if (Objects.equals(lines[i], BEGIN_COMMANDSET)) {
                currentStage = Stage.COMMAND;
            }
            if (Objects.equals(lines[i], END_COMMANDSET))
                currentStage = Stage.NONE;

            if (Objects.equals(lines[i], RUN)) {
                currentStage = Stage.RUN;
                machine.getObserver().setPosition(startPos);
                machine.getObserver().runCommand(machine);
                System.out.println("Output: " + machine.getObserver() + ", Stored: " + machine.getStoredInt());
            }

            // INFO data:

            // TODO: Implement the command for multiple tapes
            if (currentStage == Stage.INFO) {
                if (Objects.equals(lines[i], EXECUTE_AUTO)) {
                    runtimeData.replace("executeAuto", true);
                }
                if (lines[i].startsWith(SIZE)) {
                    String number = lines[i].substring(SIZE.length());
                    len = Integer.parseInt(number);
                }
                if (lines[i].startsWith(START_POS)) {
                    String number = lines[i].substring(START_POS.length());
                    startPos = Integer.parseInt(number);
                }
                if (lines[i].startsWith(DEBUG_PRINT)) {
                    runtimeData.replace("debugPrint", true);
                }
            }


            // TODO: Implement moving between tapes in regular / command movement

            if (currentStage == Stage.COMMAND) {
                // Check immediate turing machine movements
                if (lines[i].startsWith(MOVE_RELATIVE)) {
                    String number = lines[i].substring(MOVE_RELATIVE.length());
                    machine.getObserver().moveRelative(Integer.parseInt(number));
                }
                if (lines[i].startsWith(SET_POSITION)) {
                    String number = lines[i].substring(SET_POSITION.length());
                    machine.getObserver().moveRelative(Integer.parseInt(number));
                }
                if (lines[i].startsWith(SET_STORED)) {
                    String[] settingTo = lines[i].split(" ");
                    if (Objects.equals(settingTo[1], TRUE)) {
                        machine.setStored(true);
                    } else if (Objects.equals(settingTo[1], FALSE)) {
                        machine.setStored(false);
                    } else if (Objects.equals(settingTo[1], GET)) {
                        machine.setStored(machine.getBool());
                    } else if (Objects.equals(settingTo[1], NOT)) {
                        if (Objects.equals(settingTo[2], GET)) {
                            machine.setStored(!machine.getBool());
                        }
                        if (Objects.equals(settingTo[2], STORED)) {
                            machine.setStored(!machine.getStoredBool());
                        }
                    }
                }


                // Check command data
                if (lines[i].startsWith(CMD)) {
                    String[] sc = lines[i].substring(CMD.length()).split(" ");
                    ArrayList<String> subCommands = new ArrayList<>(Arrays.asList(sc));

                    // Create an arraylist of lambdas to run at execution time (these will be assigned to an Item)
                    ArrayList<Command> commands = new ArrayList<>();

                    // Loop through every provided subcommand using regex
                    for (int j = 0; j < subCommands.size(); j++) {

                        // TODO: fix this
//                        for (int indentIndex = i + 1; i < lines.length; i++) {
//                            if (lines[indentIndex].startsWith("    ")) {
//                                System.out.println(indentIndex);
//                                subCommands.addAll(Arrays.asList(lines[indentIndex].split(" ")));
//                            } else {
//                                break;
//                            }
//                        }

                        if (runtimeData.get("debugPrint"))
                            System.out.println("    " + j + " " + subCommands.get(j));

                        if (Objects.equals(subCommands.get(j), EXECUTE)) {
                            commands.add((state, m) -> m.getObserver().runCommand(m));
                        }
                        if (Objects.equals(subCommands.get(j), PRINT) || runtimeData.get("debugPrint")) {
                            commands.add((state, m) -> System.out.println(m.getObserver() + ", Stored: " + m.getStoredInt()));
                        }

                        if (Objects.equals(subCommands.get(j), SET_STORED.strip())) {
                            if (Objects.equals(subCommands.get(j + 1), GET))
                                commands.add(((state, m) -> m.setStored(m.getBool())));
                            if (Objects.equals(subCommands.get(j + 1), NOT)) {
                                if (Objects.equals(subCommands.get(j + 2), GET))
                                    commands.add(((state, m) -> m.setStored(!m.getBool())));
                                if (Objects.equals(subCommands.get(j + 2), STORED.strip()))
                                    commands.add(((state, m) -> m.setStored(!m.getStoredBool())));
                            }
                        }

                        if (Objects.equals(subCommands.get(j), MOVE_RELATIVE.strip())) {
                            int amountToMove = Integer.parseInt(subCommands.get(j + 1));
                            commands.add((state, m) -> m.getObserver().moveRelative(amountToMove));
                        }
                        if (Objects.equals(subCommands.get(j), SET_POSITION)) {
                            int finalJ1 = j;
                            commands.add((state, m) -> m.getObserver().setPosition(Integer.parseInt(subCommands.get(finalJ1 + 1))));
                        }
                        if (Objects.equals(subCommands.get(j), SET)) {
                            if (Objects.equals(subCommands.get(j + 1), NOT)) {
                                if (Objects.equals(subCommands.get(j + 2), GET)) {
                                    commands.add((state, m) -> m.getObserver().set(!m.getBool()));
                                }
                                if (Objects.equals(subCommands.get(j + 2), STORED.strip()))
                                    commands.add((state, m) -> m.getObserver().set(!m.getStoredBool()));
                            } else {
                                if (Objects.equals(subCommands.get(j + 1), GET))
                                    commands.add((state, m) -> m.getObserver().set(m.getBool()));
                                if (Objects.equals(subCommands.get(j + 1), STORED.strip()))
                                    commands.add((state, m) -> m.getObserver().set(m.getStoredBool()));
                                if (Objects.equals(subCommands.get(j + 1), TRUE)) {
                                    commands.add((state, m) -> m.getObserver().set(true));
                                }
                                if (Objects.equals(subCommands.get(j + 1), FALSE))
                                    commands.add((state, m) -> m.getObserver().set(false));
                            }
                        }
                        if (Objects.equals(subCommands.get(j), SET_STORED)) {
                            commands.add((state, m) -> m.setStored(m.getBool()));
                        }

                        // On the last command, add an execute automatically if provided and set the commands to the item
                        if (j == subCommands.size() - 1) {
                            if (runtimeData.get("executeAuto")) {
                                commands.add((state, m) -> m.getObserver().runCommand(m));
                                machine.getObserver().setCommands(commands);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public String toString() {
        return machine.toString();
    }
}