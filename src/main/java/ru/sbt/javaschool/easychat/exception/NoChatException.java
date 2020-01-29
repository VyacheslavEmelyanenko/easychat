package ru.sbt.javaschool.easychat.exception;

public class NoChatException extends RuntimeException {
    public NoChatException() {
    }

    public NoChatException(String message) {
        super(message);
    }

    public NoChatException(String message, Throwable cause) {
        super(message, cause);
    }
}
