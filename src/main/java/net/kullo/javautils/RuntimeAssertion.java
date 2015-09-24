package net.kullo.javautils;

public class RuntimeAssertion {
    private RuntimeAssertion() {}

    public static void require(boolean expression) {
        if (!expression) {
            throw new AssertionError(expression);
        }
    }
}
