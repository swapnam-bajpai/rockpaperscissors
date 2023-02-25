package com.hartwig.rockpaperscissors;

import java.util.function.Supplier;

/**
 * <ul>
 *     <li>Class to emulate participants in a Rock, Paper, Scissors game</li>
 *     <li>Every Player in instantiated with a name and a supplier of its inputs to the game</li>
 *     <li>This can be any source of integers such as : command line, file input, random number generator etc.</li>
 *     <li>Player maintains a summary of its game statistics and provides a public API to get number of games played, won and lost</li>
 * </ul>
 */
public class Player {
    private final String name;
    private final Supplier<Integer> inputSupplier;
    private final GamesStatistics gamesStatistics;

    public Player(String name, Supplier<Integer> inputSupplier) {
        this.name = name;
        this.inputSupplier = inputSupplier;
        gamesStatistics = new GamesStatistics();
    }

    public String getName() {
        return name;
    }

    public int getGamesPlayed() {
        return gamesStatistics.getGamesPlayed();
    }

    public int getGamesWon() {
        return gamesStatistics.getGamesWon();
    }

    public int getGamesLost() {
        return gamesStatistics.getGamesLost();
    }

    int getNextInput() {
        return inputSupplier.get();
    }

    void addGame() {
        gamesStatistics.incrementGamesPlayed(1);
    }

    void addGameWon() {
        gamesStatistics.incrementGamesWon(1);
    }

    void addGameLost() {
        gamesStatistics.incrementGamesLost(1);
    }

    private static class GamesStatistics {
        private int gamesPlayed;
        private int gamesWon;
        private int gamesLost;

        public int getGamesPlayed() {
            return gamesPlayed;
        }

        public void incrementGamesPlayed(int count) {
            gamesPlayed += count;
        }

        public int getGamesWon() {
            return gamesWon;
        }

        public void incrementGamesWon(int count) {
            gamesWon += count;
        }

        public int getGamesLost() {
            return gamesLost;
        }

        public void incrementGamesLost(int count) {
            gamesLost += count;
        }
    }
}
