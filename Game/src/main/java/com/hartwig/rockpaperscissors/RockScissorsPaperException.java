package com.hartwig.rockpaperscissors;

public class RockScissorsPaperException extends RuntimeException {
    public RockScissorsPaperException(String message, Throwable cause) {
        super(String.format("Error while playing rock paper scissors : %s", message), cause);
    }
}
