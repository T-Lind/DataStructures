package turingmachine;

import org.jetbrains.annotations.NotNull;
import turingmachine.highlevel.CommandList;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class TuringInterpreter extends CommandList {
    public static void main(String[] args) throws FileNotFoundException {
        StringBuilder summedArgs = new StringBuilder();
        for (String arg : args)
            summedArgs.append(arg).append(" ");

        var program = new File(summedArgs.toString());
        var reader = new Scanner(program);
        var readString = new StringBuilder();
        boolean inCommentBlock = false;
        while (reader.hasNextLine()) {
            var line = reader.nextLine();
            if (line.startsWith(BLOCK_COMMENT[0]))
                inCommentBlock = true;
            if (!line.startsWith(COMMENT) && !inCommentBlock)
                readString.append(line);
            if (line.startsWith(BLOCK_COMMENT[1]))
                inCommentBlock = false;
        }
        reader.close();

        int[] values = null;
        int startPosition = 0;
        TuringMachine machine = null;
        String[] lines = readString.toString().split(";");
        for (String line : lines) {
            if (line.startsWith(INITIALIZE_VALUES)) {
                String[] data = getInsideDelimiters(line).split(" ");
                values = new int[data.length];
                for (int j = 0; j < data.length; j++) {
                    if (data[j].equals(SECTION_CHAR)) {
                        values[j] = SECTION;
                    } else {
                        values[j] = Integer.parseInt(data[j]);
                    }
                }
            } else if (line.startsWith(SET_POSITION)) {
                startPosition = Integer.parseInt(getInsideDelimiters(line));
            } else if (line.startsWith(DEFINE)) {
                String inside = getInsideDelimiters(line);
                String symbol = inside.substring(inside.indexOf("\"") + 1, inside.lastIndexOf("\""));


                if (inside.contains("MACHINE_SYMBOL"))
                    MACHINE_SYMBOL = symbol;
                else if (inside.contains("FUTURE_SYMBOL"))
                    FUTURE_SYMBOL = symbol;
                else if (inside.contains("CONNECTOR"))
                    CONNECTOR = symbol;
                else if (inside.contains("PAGE"))
                    PAGE = symbol + "=";
                else if (inside.contains("AWARENESS"))
                    AWARENESS = symbol + "=";
                else if (inside.contains("DELIMITER_OPEN"))
                    DELIMITER_OPEN = symbol;
                else if (inside.contains("DELIMITER_CLOSE"))
                    DELIMITER_CLOSE = symbol;
                else if (inside.contains("COMMENT"))
                    COMMENT = symbol;
                else if (inside.contains("BLOCK_COMMENT_START"))
                    BLOCK_COMMENT[0] = symbol;
                else if (inside.contains("BLOCK_COMMENT_END"))
                    BLOCK_COMMENT[1] = symbol;
                refreshList();
            } else if (line.startsWith(GENERATE_MACHINE)) {
                if (values == null) {
                    machine = new TuringMachine(startPosition);
                } else {
                    machine = new TuringMachine(startPosition, values);
                }
            } else if (line.startsWith(PRINT)) {
                String inside = getInsideDelimiters(line);
                if (inside.length() == 0)
                    machine.printTape();
                else {
                    System.out.println(inside.substring(1, inside.length() - 1));
                }
            } else if (line.startsWith(RUN)) {
                machine.run();
            } else if (line.startsWith(CMD)) {
                String[] insides = getInsideDelimiters(line).strip().split(" ");
                int page = 0;
                int awareness = 0;
                for (int k = 0; k < 2; k++) {
                    var inside = insides[k];
                    if (inside.startsWith(PAGE)) {
                        page = onlyKeepInt(inside);
                    } else if (inside.startsWith(AWARENESS)) {
                        awareness = onlyKeepInt(inside);
                    }
                }
                for (int k = 2; k < insides.length; k++) {
                    var inside = insides[k];
                    if (inside.equals(" "))
                        continue;
                    if (inside.startsWith(FUTURE_STOP)) {
                        machine.addCommand(page, awareness, TuringMachine::stop, false);
                    } else if (inside.startsWith(FUTURE_PRINT)) {
                        String printStatement = getInsideDelimiters(inside);
                        if (printStatement.length() == 0)
                            machine.addCommand(page, awareness, (m) -> m.printTape());
                        else {
                            machine.addCommand(page, awareness, (m) -> System.out.println(printStatement.substring(1, printStatement.length() - 1)));
                        }
                    } else if (inside.startsWith(FUTURE_GOTONEXTSEC)) {
                        machine.addCommand(page, awareness, (m) -> m.goToNextSection());

                    } else if (inside.startsWith(FUTURE_GOTOPREVSEC)) {
                        machine.addCommand(page, awareness, (m) -> m.goToPrevSection());
                    } else if (inside.startsWith(FUTURE_SETTAPE)) {
                        final int value = onlyKeepInt(getInsideDelimiters(inside));
                        machine.addCommand(page, awareness, (m) -> {
                            m.setTape(value);
                        }, false);
                    } else if (inside.startsWith(FUTURE_MOVE)) {
                        final int value = onlyKeepInt(getInsideDelimiters(inside));
                        machine.addCommand(page, awareness, (m) -> {
                            m.move(value);
                        }, false);
                    } else if (inside.startsWith(FUTURE_GOTOPAGE)) {
                        final int value = onlyKeepInt(getInsideDelimiters(inside));
                        machine.addCommand(page, awareness, (m) -> {
                            m.goToPage(value);
                        }, false);
                    }
                }
                machine.addCommand(page, awareness, (m) -> m.setAwareness(m.getTape()));
            }
        }
    }

    public static int onlyKeepInt(String input) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (INTEGERS.contains(input.substring(i, i + 1))) {
                builder.append(input.charAt(i));
            }
        }
        return Integer.parseInt(builder.toString());
    }

    //bug

    private static String getInsideDelimiters(String s) {
        return s.substring(s.indexOf(DELIMITER_OPEN) + 1, s.lastIndexOf(DELIMITER_CLOSE));
    }
}
