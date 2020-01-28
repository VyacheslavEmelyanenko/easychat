package ru.sbt.javaschool.easychat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

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
