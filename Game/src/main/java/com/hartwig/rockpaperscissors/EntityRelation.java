package com.hartwig.rockpaperscissors;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.Objects;

import static com.hartwig.rockpaperscissors.GameEntity.*;

public final class EntityRelation {
    private static final Map<GameEntity, GameEntity> DOMINATED_BY_DOMINATOR =
            ImmutableMap.<GameEntity, GameEntity>builder()
                    .put(ROCK, SCISSORS)
                    .put(SCISSORS, PAPER)
                    .put(PAPER, ROCK)
                    .build();

    public static boolean doesWin(GameEntity first, GameEntity second) {
        return Objects.equals(DOMINATED_BY_DOMINATOR.get(first), second);
    }
}
