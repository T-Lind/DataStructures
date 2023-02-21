package turingmachine.highlevel;

import org.jetbrains.annotations.NotNull;
import turingmachine.components.Command;
import turingmachine.components.SyntaxException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class TuringInterpreter2 extends CommandList {
    private TuringMachine machine;
    private final HashMap<String, Boolean> runtimeData;



    public TuringInterpreter2(@NotNull String commandToFollow) throws SyntaxException {
        checkParens(commandToFollow);
        runtimeData = new HashMap<>();

        String[] lines = removeComments(commandToFollow).split(";");
        int len = 10;
        int startPos = 0;
        // Starting runtime data
        runtimeData.put("executeAuto", false);
        runtimeData.put("debugPrint", false);

        ArrayList<Boolean> initTapeBools = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) {
            if (runtimeData.get("debugPrint"))
                System.out.println(i + " " + lines[i]);

            if (Objects.equals(lines[i], GENERATE_MACHINE)) {
                if(initTapeBools.size() == 0)
                    machine = new TuringMachine(len, startPos);
                else{
                    final boolean[] initValues = new boolean[initTapeBools.size()];
                    for(int k=0;k<initTapeBools.size();k++)
                        initValues[k] = initTapeBools.get(k);

                    machine = new TuringMachine(startPos, initValues);

                }
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
            if (lines[i].startsWith(INIT_TAPE)) {
                for (String strValue : lines[i].substring(INIT_TAPE.length()).split(" ")) {
                    initTapeBools.add(strValue.equals("1"));
                }
            }

            if (Objects.equals(lines[i], EXECUTE_AUTO)) {
                runtimeData.replace("executeAuto", true);
            }

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
                    if (Objects.equals(settingTo[2], STORED.strip())) {
                        machine.setStored(!machine.getStoredBool());
                    }
                }
            }

            if (Objects.equals(lines[i], RUN)) {
                machine.getObserver().setPosition(startPos);
                machine.getObserver().runCommand(machine);
                System.out.println("Output: " + machine.getObserver() + ", Stored: " + machine.getStoredInt());
            }

            // COMMANDS:

            if (lines[i].startsWith(CMD)) {
                String raw = lines[i].substring(CMD.length());
                raw = raw.replaceAll("\n", " ");
                raw = raw.replaceAll("    ", " ");

                String[] sc = raw.split(" ");
                ArrayList<String> subCommands = new ArrayList<>(Arrays.asList(sc));


                ArrayList<Command> commands = new ArrayList<>();

                for (int j = 0; j < subCommands.size(); j++) {
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
                            if (Objects.equals(subCommands.get(j + 1), GET)) {
                                if (isComparison(subCommands.get(j + 2))) {
                                    if (Objects.equals(subCommands.get(j + 3), STORED.strip())) {
                                        String comparison = subCommands.get(j + 2);
                                        commands.add(((state, m) -> m.getObserver().set(performComparison(m.getBool(), m.getStoredBool(), comparison))));
                                    }
                                } else {
                                    commands.add((state, m) -> m.getObserver().set(m.getBool()));
                                }
                            }
                            if (Objects.equals(subCommands.get(j + 1), STORED.strip())) {
                                if (isComparison(subCommands.get(j + 2))) {
                                    if (Objects.equals(subCommands.get(j + 3), GET)) {
                                        String comparison = subCommands.get(j + 2);
                                        commands.add(((state, m) -> m.getObserver().set(performComparison(m.getBool(), m.getStoredBool(), comparison))));
                                    }
                                } else {
                                    commands.add((state, m) -> m.getObserver().set(m.getStoredBool()));
                                }
                            }
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

    private static String removeComments(@NotNull String input) {
        StringBuilder retStr = new StringBuilder();
        boolean inCommentBlock = false;
        String[] lines = input.split("\n");
        for (String line : lines) {
            if (line.startsWith(COMMENT))
                continue;
            if (line.startsWith(BLOCK_COMMENT[0])) {
                inCommentBlock = true;
                continue;
            }
            if (line.startsWith(BLOCK_COMMENT[1])) {
                inCommentBlock = false;
                continue;
            }
            if (!inCommentBlock) {
                retStr.append(line);
            }
        }
        return retStr.toString();
    }

    private static void checkParens(@NotNull String input) throws SyntaxException {
        int parenCounter = 0;
        String[] characters = input.split("");
        for (String c : characters) {
            if (Objects.equals(c, "("))
                parenCounter++;
            if (Objects.equals(c, ")"))
                parenCounter--;
            if (parenCounter < 0) {
                throw new SyntaxException("Parenthesis not matched in input code!");
            }
        }
        if (parenCounter != 0)
            throw new SyntaxException("Parenthesis not matched in input code!");
    }

    private static boolean isComparison(String subCommand) {
        for (String s : COMPARISONS) {
            if (s.equals(subCommand))
                return true;
        }
        return false;
    }

    private static boolean performComparison(boolean a, boolean b, String operation) {
        if (operation.equals(COMPARISONS[0]))
            return a && b;
        else if (operation.equals(COMPARISONS[1]))
            return !(a && b);
        else if (operation.equals(COMPARISONS[2]))
            return a || b;
        else if (operation.equals(COMPARISONS[3])) {
            return a ^ b;
        }
        throw new RuntimeException("Improper operation name specified to performComparison.");
    }
}
