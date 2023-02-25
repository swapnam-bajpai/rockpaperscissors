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

    public void
    run() {
        try {
            getUserNameCreatePlayers();
        } catch (IOException e) {
            throw new RockScissorsPaperException("Error in getting user name from command line!", e);
        }
        printRules(player1.getUserName(), player2.getUserName());
        runGameLoop();
    }

    private void getUserNameCreatePlayers() throws IOException {
        System.out.println("Please enter user name");
        player1 = new Player(inputReader.readLine(), new CommandLineSupplier(inputReader));
        player2 = new Player(COMPUTER_USER_NAME, new RandomNumberSupplier(LOWER_BOUND, UPPER_BOUND));
    }

    private void runGameLoop() {
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

        printChoicesUpdateStats(player1Entity, player2Entity);

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

    private void printChoicesUpdateStats(GameEntity entity1, GameEntity entity2) {
        System.out.printf("%s chose : %s, %s chose : %s%n",
                player1.getUserName(), entity1, player2.getUserName(), entity2);

        player1.addGame();
        player2.addGame();
    }

    private void declareWinnerUpdateStats(Player winner, Player loser) {
        System.out.printf("Congratulations %s. You won!%n", winner.getUserName());
        winner.addGameWon();
        loser.addGameLost();
    }
}
