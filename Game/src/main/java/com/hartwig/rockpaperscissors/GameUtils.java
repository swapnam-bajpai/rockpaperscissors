package com.hartwig.rockpaperscissors;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import static com.hartwig.rockpaperscissors.GameEntity.*;

public final class GameUtils {
    public static final int LOWER_BOUND = 1;
    public static final int UPPER_BOUND = 3;
    public static final int EXIT_GAME = 4;
    public static final String COMPUTER_USER_NAME = "TuringMachine";
    private static final Map<Integer, GameEntity> ENTITY_BY_CHOICE =
            ImmutableMap.<Integer, GameEntity>builder()
                    .put(1, ROCK)
                    .put(2, PAPER)
                    .put(3, SCISSORS)
                    .build();
    private static final Map<GameEntity, GameEntity> DOMINATED_BY_DOMINATOR =
            ImmutableMap.<GameEntity, GameEntity>builder()
                    .put(ROCK, SCISSORS)
                    .put(SCISSORS, PAPER)
                    .put(PAPER, ROCK)
                    .build();

    public static GameEntity getGameEntity(int choice) {
        return ENTITY_BY_CHOICE.get(choice);
    }

    public static void printRules(String userName1, String userName2) {
        System.out.printf("""
                        Welcome to rock paper scissors %s %s.\s
                        ROCK BEATS SCISSORS BEAT PAPER BEATS ROCK\s
                        You will be choosing one of the three in each game against each other.\s
                        At the end you will be provided a summary of your performance over all games.\s
                        """,
                userName1, userName2);
    }

    public static void printSummary(Player player) {
        System.out.println("Games played :" + player.getGamesPlayed());
        System.out.println("Games won :" + player.getGamesWon());
        System.out.println("Games lost :" + player.getGamesLost());
        System.out.println("Games tied :" +
                (player.getGamesPlayed() - player.getGamesWon() - player.getGamesLost()));
        System.out.println("Thank you for playing Rock Paper Scissors. Tot ziens!");
    }

    public static boolean doesDefeat(GameEntity first, GameEntity second) {
        return DOMINATED_BY_DOMINATOR.get(first) == second;
    }

    public static boolean isExitChoice(int choice) {
        return choice == EXIT_GAME;
    }

    public static boolean isWrongChoice(int choice) {
        return choice < LOWER_BOUND || choice > EXIT_GAME;
    }

    public static void printInputError() {
        System.out.printf("Please enter from one of the possible set of choices %d-%d", LOWER_BOUND, EXIT_GAME);
    }
}
