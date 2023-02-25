package com.hartwig.rockpaperscissors;

import java.util.function.Supplier;

public class Player {
    private final String userName;
    private final Supplier<Integer> inputSupplier;
    private final GamesStatistics gamesStatistics;

    public Player(String userName, Supplier<Integer> inputSupplier) {
        this.userName = userName;
        this.inputSupplier = inputSupplier;
        gamesStatistics = new GamesStatistics();
    }

    public String getUserName() {
        return userName;
    }

    public int getNextInput() {
        return inputSupplier.get();
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
