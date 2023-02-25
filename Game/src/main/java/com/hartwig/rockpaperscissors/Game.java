package com.hartwig.rockpaperscissors;

import com.google.common.collect.ImmutableMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static com.hartwig.rockpaperscissors.GameEntity.*;

public class Game implements Runnable {
    private static final Map<Integer, GameEntity> ENTITY_BY_CHOICE =
            ImmutableMap.<Integer, GameEntity>builder()
                    .put(1, ROCK)
                    .put(2, PAPER)
                    .put(3, SCISSORS)
                    .build();
    private static final int EXIT_GAME = 4;
    private final BufferedReader inputReader;

    private Player player;

    public Game() {
        this.inputReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() {
        try {
            getUserName();
            printGameWelcome();
            runGameLoop();
        } catch (IOException e) {
            System.out.println("Error in interacting with command line!");
        }
    }

    private void getUserName() throws IOException {
        System.out.println("Please enter user name");
        this.player = new Player(inputReader.readLine());
    }

    private void printGameWelcome() {
        System.out.printf("""
                        Welcome to rock paper scissors %s.\s
                        ROCK BEATS SCISSORS BEAT PAPER BEATS ROCK\s
                        You will be choosing one of the three in each game against a computer.\s
                        At the end you will be provided a summary of your performance over all games.\s
                        """,
                player.getUserName());
    }

    private void runGameLoop() throws IOException {
        while (true) {
            int choice = printMenuReadChoice();
            if (choice == EXIT_GAME) {
                player.printSummary();
                break;
            }
            if (isWrongChoice(choice)) {
                printInputError();
                continue;
            }
            playGame(choice);
        }
    }

    private int printMenuReadChoice() throws IOException {
        System.out.println("Choose wisely : 1. ROCK 2. PAPER 3. SCISSORS 4. EXIT GAME");
        return Integer.parseInt(inputReader.readLine());
    }

    private boolean isWrongChoice(int choice) {
        return !ENTITY_BY_CHOICE.containsKey(choice);
    }

    private void printInputError() {
        System.out.println("Please enter from one of the possible set of choices 1-4");
    }

    private void playGame(int choice) {
        GameEntity userEntity = getUserEntity(choice);
        GameEntity computerEntity = getComputerEntity();
        calculateWinnerPrintResult(userEntity, computerEntity);
    }

    private GameEntity getUserEntity(int userChoice) {
        return ENTITY_BY_CHOICE.get(userChoice);
    }

    private GameEntity getComputerEntity() {
        return ENTITY_BY_CHOICE.get(ThreadLocalRandom.current().nextInt(1, 4));
    }

    private void calculateWinnerPrintResult(GameEntity userEntity, GameEntity computerEntity) {
        System.out.printf("You chose : %s Computer chose %s%n", userEntity, computerEntity);
        player.addGame();
        if (userEntity == computerEntity) {
            System.out.println("It was a tie!");
            return;
        }
        if (EntityRelation.doesWin(userEntity, computerEntity)) {
            System.out.printf("Congratulations %s. You won!%n", player.getUserName());
            player.addGameWon();
            return;
        }
        System.out.println("Wat jammer! Unfortunately you lost.");
        player.addGameLost();
    }
}
