package com.hartwig.rockpaperscissors.inputSupplier;

import com.hartwig.rockpaperscissors.model.RockScissorsPaperException;

import java.io.BufferedReader;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

import static com.hartwig.rockpaperscissors.util.GameUtils.INVALID_INPUT;

public class CommandLineSupplier implements Supplier<Integer> {
    private final Callable<Integer> commandLineReader;

    public CommandLineSupplier(BufferedReader inputReader) {
        commandLineReader = () -> {
            printInputMessage();
            return parseInput(inputReader.readLine());
        };
    }

    private void printInputMessage() {
        System.out.println("Choose wisely : 1. ROCK 2. PAPER 3. SCISSORS 4. EXIT GAME");
    }

    private int parseInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.printf("Detected non integer input %s%n", input);
            return INVALID_INPUT;
        }
    }

    @Override
    public Integer get() {
        try {
            return commandLineReader.call();
        } catch (Exception e) {
            throw new RockScissorsPaperException("Could not read choice from command line\n", e);
        }
    }
}
