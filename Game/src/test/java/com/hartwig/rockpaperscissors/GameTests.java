package com.hartwig.rockpaperscissors;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;

public class GameTests {
    @Test
    public void testGame() {
        //Player 1 tries to give invalid inputs -1 and 5 which cancels that round and game prompts for correct input
        //For the subsequent 9 rounds the game correctly evaluates the results
        //Player 1 exits the game by providing 4 in the last round
        //Note that incorrect inputs and exit round do not contribute to win/loss/played game stats

        Player player1 = new Player("Player1", new TestSupplier(-1, 5, 1, 2, 3, 1, 2, 3, 1, 2, 3, 4));
        Player player2 = new Player("Player2", new TestSupplier(1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 1));

        Game game = new Game(player1, player2);
        game.run();

        Assertions.assertEquals(9, player1.getGamesPlayed());
        Assertions.assertEquals(9, player1.getGamesPlayed());

        Assertions.assertEquals(3, player1.getGamesWon());
        Assertions.assertEquals(3, player1.getGamesWon());

        Assertions.assertEquals(3, player1.getGamesLost());
        Assertions.assertEquals(3, player1.getGamesLost());
    }

    private static class TestSupplier implements Supplier<Integer> {
        private final List<Integer> returnedValues;
        private int invocationCount = 0;

        TestSupplier(Integer... values) {
            returnedValues = ImmutableList.copyOf(values);
        }

        @Override
        public Integer get() {
            return returnedValues.get(invocationCount++);
        }
    }
}
