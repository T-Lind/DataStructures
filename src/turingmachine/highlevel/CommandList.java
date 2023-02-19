package turingmachine.highlevel;

public class CommandList {
    // Section info

    public final static String BEGIN_INFO = "BEGIN INFO";
    public final static String END_INFO = "END INFO";
    public final static String BEGIN_COMMANDSET = "BEGIN COMMANDS";
    public final static String END_COMMANDSET = "END COMMANDS";
    public final static String RUN = "RUN";

    // Individual commands

    public final static String SIZE = "SIZE ";
    public final static String START_POS = "START POS ";
    public final static String EXECUTE_AUTO = "EXECUTE AUTO";
    public final static String MOVE_RELATIVE = "MOVE ";

    public final static String SET_POSITION = "SET POSITION ";
    public final static String CMD = "CMD ";

    public final static String STORED = "STORED ";
    public final static String SET_STORED = "SET_STORED ";
    public final static String SET = "SET";
    public final static String TRUE = "TRUE";
    public final static String FALSE = "FALSE";
    public final static String GET = "GET";
    public final static String NOT = "NOT";
    public final static String EXECUTE = "EXECUTE";
    public final static String COMMENT = "//";
    public final static String[] BLOCK_COMMENT = {"/*", "*/"};


    /*
     * Deprecated section:
     */

    @Deprecated
    public final static String DONE = "DONE"; // Not used anymore, last command automatically does this

    @Deprecated
    public final static String MOVE_RELATIVE_NSPACE = "MOVE"; // Not used anymore, .strip() now used
}
