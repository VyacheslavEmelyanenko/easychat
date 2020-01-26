package ru.sbt.javaschool.easychat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sbt.javaschool.easychat.entity.Chat;
import ru.sbt.javaschool.easychat.model.RequestEntry;
import ru.sbt.javaschool.easychat.service.ChatService;

@Controller
public class ChatController {

    @Autowired
    ChatService chatService;

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/create")
    public void create() {
        chatService.newChat();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/send")
    public void send(@RequestBody RequestEntry requestEntry) {


    }
}
