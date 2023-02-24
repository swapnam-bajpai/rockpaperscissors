package com.hartwig.rockpaperscissors;

public class Player {
    private final String userName;
    private final GamesStatistics gamesStatistics;

    public Player(String userName) {
        this.userName = userName;
        gamesStatistics = new GamesStatistics();
    }

    public String getUserName() {
        return userName;
    }

    public void printSummary() {
        System.out.println("Games played :" + gamesStatistics.getGamesPlayed());
        System.out.println("Games won :" + gamesStatistics.getGamesWon());
        System.out.println("Games lost :" + gamesStatistics.getGamesLost());
        System.out.println("Games tied :" +
                (gamesStatistics.getGamesPlayed() - gamesStatistics.getGamesWon() - gamesStatistics.getGamesLost()));
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
