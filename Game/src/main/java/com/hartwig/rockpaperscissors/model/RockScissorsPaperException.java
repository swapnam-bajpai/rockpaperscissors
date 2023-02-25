package com.hartwig.rockpaperscissors.model;

public class RockScissorsPaperException extends RuntimeException {
    public RockScissorsPaperException(String message, Throwable cause) {
        super(String.format("Error while playing rock paper scissors : %s", message), cause);
    }
}
