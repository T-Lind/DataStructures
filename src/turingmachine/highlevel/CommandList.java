package turingmachine.highlevel;

public class CommandList {
    public static int STOP = Integer.MAX_VALUE;

    public static String MACHINE_SYMBOL = "";
    public static String FUTURE_SYMBOL = "?";
    public static String CONNECTOR = "";
    public static String DEFINE = "DEFINE";
    public static String DENOTE = "DENOTE"; // TODO: IMPLEMENT THESE FEATURES
    public static String BEFORE = "BEFORE";
    public static String AFTER = "AFTER";

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

    public static String RUN = MACHINE_SYMBOL + CONNECTOR + "run()";

}
