package ru.sbt.javaschool.easychat.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class RequestEntry {
    @NotBlank(message = "Nickname must not be empty")
    @Length(max = 30, message = "Nickname length should be less then 30")
    private String nickname;

    @NotBlank(message = "Message must not be empty")
    @Length(max = 255, message = "Message length should be less then 30")
    private String message;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
