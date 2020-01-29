package ru.sbt.javaschool.easychat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbt.javaschool.easychat.entity.Chat;
import ru.sbt.javaschool.easychat.entity.Message;
import ru.sbt.javaschool.easychat.entity.Person;
import ru.sbt.javaschool.easychat.model.RequestEntry;
import ru.sbt.javaschool.easychat.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
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
        Person person;

        person = personService.findPersonByNickname(requestEntry.getNickname());

        message.setChat(chatService.getCurrentChat());
        message.setPerson(person);
        message.setMessage(requestEntry.getMessage());
        message.setDate(LocalDateTime.now());
        messageRepository.save(message);
    }

    public List<Message> getMessagesCurrentChat() {
        return messageRepository.findBychat(chatService.getCurrentChat());
    }

    public List<Message> getMessagesByIdChat(long id) {
        Chat chat = chatService.getChatById(id);
        return messageRepository.findBychat(chat);
    }
}
