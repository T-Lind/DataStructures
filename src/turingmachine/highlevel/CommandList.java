package turingmachine.highlevel;

public class CommandList {
    public static int STOP = Integer.MAX_VALUE;
    public static int SECTION = Integer.MIN_VALUE;
    public static String SECTION_CHAR = "X";

    public static String MACHINE_SYMBOL = "";
    public static String FUTURE_SYMBOL = "?";
    public static String CONNECTOR = "";
    public static String DEFINE = "DEFINE";

    public static String DELIMITER_OPEN = "(";
    public static String DELIMITER_CLOSE = ")";
    public static String COMMENT = "//";
    public static String[] BLOCK_COMMENT = {"/*", "*/"};
    public static String INTEGERS = "-0123456789";

    public static String PAGE = "PAGE=";
    public static String AWARENESS = "AWARENESS=";

    public static String INITIALIZE_VALUES = MACHINE_SYMBOL + CONNECTOR + "setValues";
    public static String SET_POSITION = MACHINE_SYMBOL + CONNECTOR + "setPosition";
    public static String GENERATE_MACHINE = MACHINE_SYMBOL + CONNECTOR + "generate()";

    public static String PRINT = MACHINE_SYMBOL + CONNECTOR + "print";
    public static String FUTURE_PRINT = FUTURE_SYMBOL + CONNECTOR + "print";
    public static String CMD = MACHINE_SYMBOL + CONNECTOR + "setCommand";


    public static String FUTURE_STOP = FUTURE_SYMBOL + CONNECTOR + "stop()";
    public static String FUTURE_SETTAPE = FUTURE_SYMBOL + CONNECTOR + "setTape";
    public static String FUTURE_MOVE = FUTURE_SYMBOL + CONNECTOR + "move";
    public static String FUTURE_GOTOPAGE = FUTURE_SYMBOL + CONNECTOR + "goToPage";
    public static String FUTURE_GOTONEXTSEC = FUTURE_SYMBOL + CONNECTOR + "nextSection()";
    public static String FUTURE_GOTOPREVSEC = FUTURE_SYMBOL + CONNECTOR + "prevSection()";

    public static String RUN = MACHINE_SYMBOL + CONNECTOR + "run()";

    public static void refreshList() {
        refreshList(true);
    }

    public static void refreshList(boolean beforeFunction) {
        if (beforeFunction) {
            INITIALIZE_VALUES = MACHINE_SYMBOL + CONNECTOR + "setValues";
            SET_POSITION = MACHINE_SYMBOL + CONNECTOR + "setPosition";
            GENERATE_MACHINE = MACHINE_SYMBOL + CONNECTOR + "generate()";

            PRINT = MACHINE_SYMBOL + CONNECTOR + "print";
            FUTURE_PRINT = FUTURE_SYMBOL + CONNECTOR + "print";
            CMD = MACHINE_SYMBOL + CONNECTOR + "setCommand";


            FUTURE_STOP = FUTURE_SYMBOL + CONNECTOR + "stop()";
            FUTURE_SETTAPE = FUTURE_SYMBOL + CONNECTOR + "setTape";
            FUTURE_MOVE = FUTURE_SYMBOL + CONNECTOR + "move";
            FUTURE_GOTOPAGE = FUTURE_SYMBOL + CONNECTOR + "goToPage";

            RUN = MACHINE_SYMBOL + CONNECTOR + "run()";

            FUTURE_GOTONEXTSEC = FUTURE_SYMBOL + CONNECTOR + "nextSection()";
            FUTURE_GOTOPREVSEC = FUTURE_SYMBOL + CONNECTOR + "prevSection()";
        } else {
            // TODO: Implement this feature for non-empty function calls
            GENERATE_MACHINE = "generate()" + MACHINE_SYMBOL;
            FUTURE_STOP = "stop()" + FUTURE_SYMBOL;
            RUN = "run()" + MACHINE_SYMBOL;
        }


    }
}
