package com.hartwig.rockpaperscissors;

import com.hartwig.rockpaperscissors.model.GameEntity;
import com.hartwig.rockpaperscissors.util.GameUtils;

import static com.hartwig.rockpaperscissors.util.GameUtils.*;

/**
 * <ul>
 *     <li>Class to emulate a Rock Paper Scissors game between two {@link Player} instances provided as input parameters</li>
 *     <li>Players have to provide integer inputs for each round which the class maps onto {@link GameEntity}</li>
 *     <li>Players are prompted again for correct inputs in case invalid values are provided</li>
 *     <li>The logic of the victory criteria between two game entities (e.g. ROCK beats SCISSORS) is in {@link GameUtils}
 *     and can be easily extended if more entities are added</li>
 *     <li>On exit through user input, a summary of the Players' game statistics are printed</li>
 * </ul>
 */
public class Game implements Runnable {
    private final Player player1;
    private final Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void run() {
        printRules(player1.getName(), player2.getName());
        runGameLoop();
    }

    private void runGameLoop() {
        while (true) {
            int player1Choice = player1.getNextInput();
            int player2Choice = player2.getNextInput();
            if (isWrongChoice(player1Choice) || isWrongChoice(player2Choice)) {
                printInputError();
                continue;
            }
            if (isExitChoice(player1Choice) || isExitChoice(player2Choice)) {
                printSummary(player1);
                //not printing summary for computer, can be done if player2 is another human player
                break;
            }
            playGame(player1Choice, player2Choice);
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
                player1.getName(), entity1, player2.getName(), entity2);
        player1.addGame();
        player2.addGame();
    }

    private void declareWinnerUpdateStats(Player winner, Player loser) {
        System.out.printf("Congratulations %s. You won!%n", winner.getName());
        winner.addGameWon();
        loser.addGameLost();
    }
}
