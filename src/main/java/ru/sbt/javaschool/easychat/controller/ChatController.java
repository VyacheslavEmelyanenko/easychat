package ru.sbt.javaschool.easychat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sbt.javaschool.easychat.entity.Message;
import ru.sbt.javaschool.easychat.model.RequestEntry;
import ru.sbt.javaschool.easychat.service.ChatService;
import ru.sbt.javaschool.easychat.service.MessageService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    ChatService chatService;

    @Autowired
    MessageService messageService;

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/create")
    public void create() {
        chatService.newChat();
    }

    /**
     * @param requestEntry входной json сообщения
     */
    @PostMapping(path = "/send", consumes = "application/json")
    public void send(@Valid @RequestBody RequestEntry requestEntry) {
        messageService.receiveMsg(requestEntry);
    }

    @GetMapping(path = "/message", produces = "application/json")
    public @ResponseBody List<Message> message() {
        return messageService.getMessagesCurrentChat();
    }

    @GetMapping(path = "/message/{id}", produces = "application/json")
    public @ResponseBody List<Message> messagesByIdChat(@PathVariable long id) {
        return messageService.getMessagesByIdChat(id);
    }

}
