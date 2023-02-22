package turingmachine;

import org.jetbrains.annotations.NotNull;
import turingmachine.highlevel.CommandList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class TuringInterpreter extends CommandList {
    public static void main(@NotNull String[] args) throws FileNotFoundException {
        String summedArgs = "";
        for (String arg : args)
            summedArgs += arg + " ";

        File program = new File(summedArgs);
        Scanner reader = new Scanner(program);
        StringBuilder readString = new StringBuilder();
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
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].startsWith(INITIALIZE_VALUES)) {
                String[] data = getInsideDelimiters(lines[i]).split(" ");
                values = new int[data.length];
                for (int j = 0; j < data.length; j++)
                    values[j] = Integer.parseInt(data[j]);
            } else if (lines[i].startsWith(SET_POSITION)) {
                startPosition = Integer.parseInt(getInsideDelimiters(lines[i]));
            } else if (lines[i].startsWith(GENERATE_MACHINE)) {
                if (values == null) {
                    machine = new TuringMachine(startPosition);
                } else {
                    machine = new TuringMachine(startPosition, values);
                }
            } else if (lines[i].startsWith(PRINT)) {
                machine.printTape();
            } else if (lines[i].startsWith(RUN)) {
                machine.run();
            } else if (lines[i].startsWith(CMD)) {
                String[] insides = getInsideDelimiters(lines[i]).strip().split(" ");
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
                int finalPage = page;
                int finalAwareness = awareness;
                machine.addCommand(page, awareness, (m) -> m.setAwareness(m.getTape()));
                for (int k = 2; k < insides.length; k++) {
                    var inside = insides[k];
                    if (inside.equals(" "))
                        continue;
                    if (inside.startsWith(FUTURE_STOP)) {
                        machine.addCommand(page, awareness, (m) -> {
                            m.stop();
                        }, false);
                    } else if (inside.startsWith(FUTURE_PRINT)) {
                        machine.addCommand(page, awareness, (m) -> m.printTape(), false);
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

    private static String getInsideDelimiters(String s) {
        return s.substring(s.indexOf(DELIMITER_OPEN) + 1, s.lastIndexOf(DELIMITER_CLOSE));
    }
}
