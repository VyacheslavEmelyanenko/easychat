package ru.sbt.javaschool.easychat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbt.javaschool.easychat.entity.Chat;
import ru.sbt.javaschool.easychat.exception.NoChatException;
import ru.sbt.javaschool.easychat.repository.ChatRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ChatService {

    @Autowired
    ChatRepository chatRepository;

    /**
     * Закрытие всех открытых чатов и открытие нового чата
     */
    public void newChat() {
        Chat newChat = new Chat();
        List<Chat> chats = chatRepository.findByOpenedTrue();
        if (chats.size() > 0) {
            setChatsClosed(chats);
        }
        newChat.setStartDate(LocalDateTime.now());
        newChat.setOpened(true);
        chatRepository.save(newChat);
    }

    public Chat getCurrentChat() {
        List<Chat> chats = chatRepository.findByOpenedTrue();

        if (chats.isEmpty()) throw new NoChatException("There is no open chat");

        if (chats.size() == 1) return chats.get(0);
        else {
            chats.sort(Comparator.comparing(Chat::getStartDate).reversed());
            Chat currentChat = chats.remove(0);
            setChatsClosed(chats);
            return currentChat;
        }
    }

    public Chat getChatById(long id) {
        Optional<Chat> chat = chatRepository.findById(id);
        if (chat.isPresent()) return chat.get();
        else throw new NoChatException("There is no such chat with id = " + id);
    }

    private void setChatsClosed(List<Chat> chats) {
        for (Chat chat : chats) {
            chat.setOpened(false);
            chat.setEndDate(LocalDateTime.now());
        }
    }
}
