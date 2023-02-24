package com.hartwig.rockpaperscissors;

public class AppDriver {
    public static void main(String[] args) {
        try {
            new Game().run();
        } catch (Exception e) {
            System.out.println("Error while running Rock Paper Scissors. Exiting program");
            throw e;
        }
    }
}
