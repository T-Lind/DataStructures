package turingmachine.highlevel;

public class CommandList {
    public static final int STOP = Integer.MAX_VALUE;

    public static final String machineSymbol = "!";
    public static final String futureSymbol = "?";
    public static final String connector = "";

    public static final String DELIMITER_OPEN = "(";
    public static final String DELIMITER_CLOSE = ")";
    public static final String COMMENT = "//";
    public static final String[] BLOCK_COMMENT = {"/*", "*/"};
    public static final String INTEGERS = "-0123456789";

    public static final String INITIALIZE_VALUES = machineSymbol + connector + "setValues";
    public static final String SET_POSITION = machineSymbol + connector + "setPosition";
    public static final String GENERATE_MACHINE = machineSymbol + connector + "generate()";

    public static final String PRINT = machineSymbol + connector + "print";
    public static final String FUTURE_PRINT = futureSymbol + connector + "print";

    public static final String CMD = machineSymbol + connector + "setCommand";
    public static final String PAGE = "PAGE=";
    public static final String AWARENESS = "AWARENESS=";


    public static final String FUTURE_STOP = futureSymbol + connector + "stop()";
    public static final String FUTURE_SETTAPE = futureSymbol + connector + "setTape";
    public static final String FUTURE_MOVE = futureSymbol + connector + "move";
    public static final String FUTURE_GOTOPAGE = futureSymbol + connector + "goToPage";

    public static final String RUN = machineSymbol + connector + "run()";
}
