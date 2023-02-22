package turingmachine.highlevel;

public class CommandList {
    public static final int STOP = Integer.MAX_VALUE;

    public static final String DELIMITER_OPEN = "(";
    public static final String DELIMITER_CLOSE = ")";
    public static final String COMMENT = "//";
    public static final String[] BLOCK_COMMENT = {"/*", "*/"};
    public static final String INTEGERS = "-0123456789";

    public static final String INITIALIZE_VALUES = "machine::setValues";
    public static final String SET_POSITION = "machine::setPosition";
    public static final String GENERATE_MACHINE = "machine::generate()";

    public static final String PRINT = "machine::print";
    public static final String FUTURE_PRINT = "future::print";

    public static final String CMD = "machine::setCommand";
    public static final String PAGE = "PAGE=";
    public static final String AWARENESS = "AWARENESS=";


    public static final String FUTURE_STOP = "future::stop()";
    public static final String FUTURE_SETTAPE = "future::setTape";
    public static final String FUTURE_MOVE = "future::move";
    public static final String FUTURE_GOTOPAGE = "future::goToPage";

    public static final String RUN = "machine::run()";
}
