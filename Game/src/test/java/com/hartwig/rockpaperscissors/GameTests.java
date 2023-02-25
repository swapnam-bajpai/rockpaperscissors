package com.hartwig.rockpaperscissors;

import org.mockito.Mockito;

public class GameTests {
    @Test
    public void testGame() {
        Player player1 = Mockito.mock(Player.class);
        Game game = new Game();
    }
}
