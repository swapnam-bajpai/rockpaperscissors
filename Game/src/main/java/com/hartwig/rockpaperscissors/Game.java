package com.hartwig.rockpaperscissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.hartwig.rockpaperscissors.GameUtils.*;

public class Game implements Runnable {
    private final BufferedReader inputReader;
    private Player player1;
    private Player player2;


    public Game() {
        this.inputReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() {
        try {
            getUserNameCreatePlayers();
            printRules();
            runGameLoop();
        } catch (IOException e) {
            System.out.println("Error in interacting with command line!");
        }
    }

    private void getUserNameCreatePlayers() throws IOException {
        System.out.println("Please enter user name");
        player1 = new Player(inputReader.readLine(), new CommandLineSupplier(inputReader));
        player2 = new Player(COMPUTER_USER_NAME, new RandomNumberSupplier(LOWER_BOUND, UPPER_BOUND));
    }

    private void printRules() {
        System.out.printf("""
                        Welcome to rock paper scissors %s.\s
                        ROCK BEATS SCISSORS BEAT PAPER BEATS ROCK\s
                        You will be choosing one of the three in each game against %s.\s
                        At the end you will be provided a summary of your performance over all games.\s
                        """,
                player1.getUserName(), player2.getUserName());
    }

    private void runGameLoop() throws IOException {
        while (true) {
            int userChoice = player1.getNextInput();
            if (isExitChoice(userChoice)) {
                printSummary(player1);
                break;
            }
            if (isWrongChoice(userChoice)) {
                printInputError();
                continue;
            }
            int computerChoice = player2.getNextInput();
            playGame(userChoice, computerChoice);
        }
    }

    private void playGame(int choice1, int choice2) {
        GameEntity player1Entity = getGameEntity(choice1);
        GameEntity player2Entity = getGameEntity(choice2);
        System.out.printf("%s chose : %s, %s chose : %s%n",
                player1.getUserName(), player1Entity,
                player2.getUserName(), player2Entity);

        player1.addGame();
        player2.addGame();

        if (player1Entity == player2Entity) {
            System.out.println("It was a tie!");
            return;
        }
        if (doesDefeat(player1Entity, player2Entity)) {
            declareWinnerUpdateStats(player1, player2);
        } else {
            declareWinnerUpdateStats(player2, player1);
        }
    }

    private void declareWinnerUpdateStats(Player winner, Player loser) {
        System.out.printf("Congratulations %s. You won!%n", winner.getUserName());
        winner.addGameWon();
        loser.addGameLost();
    }
}
