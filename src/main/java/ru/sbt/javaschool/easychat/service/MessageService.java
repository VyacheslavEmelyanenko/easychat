package ru.sbt.javaschool.easychat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbt.javaschool.easychat.entity.Chat;
import ru.sbt.javaschool.easychat.entity.Message;
import ru.sbt.javaschool.easychat.model.RequestEntry;
import ru.sbt.javaschool.easychat.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ChatService chatService;

    @Autowired
    PersonService personService;

    public void receiveMsg(RequestEntry requestEntry) {
        Message message = new Message();
        message.setChat(chatService.getCurrentChat());

        message.setMessage(requestEntry.getMessage());

    }
}
