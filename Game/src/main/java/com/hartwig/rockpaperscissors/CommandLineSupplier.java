package com.hartwig.rockpaperscissors;

import java.io.BufferedReader;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class CommandLineSupplier implements Supplier<Integer> {
    private final Callable<Integer> commandLineReader;

    public CommandLineSupplier(BufferedReader inputReader) {
        commandLineReader = () -> {
            printInputMessage();
            return Integer.parseInt(inputReader.readLine());
        };
    }

    private void printInputMessage() {
        System.out.println("Choose wisely : 1. ROCK 2. PAPER 3. SCISSORS 4. EXIT GAME");
    }

    @Override
    public Integer get() {
        try {
            return commandLineReader.call();
        } catch (Exception e) {
            throw new RockScissorsPaperException("Could not read choice from command line", e);
        }
    }
}
