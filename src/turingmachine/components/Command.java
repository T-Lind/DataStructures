package turingmachine.components;

public interface Command {
    void invoke(Digit currentState) throws ValueError;
}