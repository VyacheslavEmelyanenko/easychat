package ru.sbt.javaschool.easychat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbt.javaschool.easychat.entity.Chat;
import ru.sbt.javaschool.easychat.exception.NoChatException;
import ru.sbt.javaschool.easychat.repository.ChatRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class ChatService {

    @Autowired
    ChatRepository chatRepository;

    public void newChat() {
        Chat newChat = new Chat();
        List<Chat> chats = getListOpenChats();
        if (chats.size() > 0) {
            setChatClosed(chats);
        }
        newChat.setStartDate(LocalDateTime.now());
        newChat.setOpened(true);
        chatRepository.save(newChat);
    }

    public Chat getCurrentChat() {
        List<Chat> chats = chatRepository.findByOpenedTrueOrderByStartDateDesc();

        if (chats.isEmpty()) throw new NoChatException("There is no open chat");

        if (chats.size() == 1) return chats.get(0);
        else {
            Chat currentChat = chats.remove(0);
            setChatClosed(chats);
            return currentChat;
        }
    }

    public Chat getChatById(long id) {
        Optional<Chat> chat = chatRepository.findById(id);
        if (chat.isPresent()) return chat.get();
        else throw new NoChatException("There is no such chat with id = " + id);
    }

    private List<Chat> getListOpenChats() {
        return chatRepository.findByOpenedTrue();
    }

    private int setChatClosed(List<Chat> chats) {
        return chatRepository.setOpenedFalse(LocalDateTime.now(), chats.stream()
                                                                                .mapToLong(Chat::getId)
                                                                                .boxed()
                                                                                .collect(Collectors.toList()));
    }
}
