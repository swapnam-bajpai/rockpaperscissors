package com.hartwig.rockpaperscissors.inputSupplier;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class RandomNumberSupplier implements Supplier<Integer> {
    private final int minInclusive;
    private final int maxInclusive;

    public RandomNumberSupplier(int minInclusive, int maxInclusive) {
        this.minInclusive = minInclusive;
        this.maxInclusive = maxInclusive;
    }

    @Override
    public Integer get() {
        return ThreadLocalRandom.current().nextInt(minInclusive, maxInclusive + 1);
    }
}
