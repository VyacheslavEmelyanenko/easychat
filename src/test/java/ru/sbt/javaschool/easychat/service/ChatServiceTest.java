package ru.sbt.javaschool.easychat.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sbt.javaschool.easychat.entity.Chat;
import ru.sbt.javaschool.easychat.exception.NoChatException;
import ru.sbt.javaschool.easychat.repository.ChatRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ChatServiceTest {

    @TestConfiguration
    public static class ChatServiceTestConfiguration {
        @Bean
        public ChatService chatService() {
            return new ChatService();
        }
    }

    @MockBean
    ChatRepository chatRepository;

    @Autowired
    ChatService chatService;

    @Mock
    Chat chatId1;

    @Spy
    Chat spyChatId1, spyChatId2;

    @Test
    public void testNewChat_whenOpenChatNotExist() {
        when(chatRepository.findByOpenedTrue()).thenReturn(new ArrayList<>());
        chatService.newChat();
        verify(chatRepository, times(1)).save(any(Chat.class));
    }

    @Test
    public void testNewChat_whenChatsExist() {
        when(chatId1.getId()).thenReturn(1L);
        when(chatRepository.findByOpenedTrue()).thenReturn(Arrays.asList(chatId1));

        chatService.newChat();
        verify(chatRepository, times(1)).save(any(Chat.class));
        verify(chatId1, times(1)).setOpened(false);
    }

    @Test
    public void testGetCurrentChat_whenOneOpenChat() {
        when(chatId1.getId()).thenReturn(1L);
        when(chatRepository.findByOpenedTrue())
                .thenReturn(new ArrayList<>(Arrays.asList(chatId1)));

        Chat chatActual = chatService.getCurrentChat();
        assertEquals(chatId1.getId(), chatActual.getId());
    }

    @Test
    public void testGetCurrentChat_whenTwoOpenChat() {
        spyChatId1.setId(1L);
        spyChatId1.setStartDate(LocalDateTime.parse("2020-01-01T12:00:00"));
        spyChatId2.setId(2L);
        spyChatId2.setStartDate(LocalDateTime.parse("2020-01-01T12:00:20"));
        when(chatRepository.findByOpenedTrue())
                .thenReturn(new ArrayList<>(Arrays.asList(spyChatId1, spyChatId2)));

        Chat chatActual = chatService.getCurrentChat();
        assertEquals(spyChatId2.getId(), chatActual.getId());
        verify(spyChatId1, times(1)).setOpened(false);
    }

    @Test
    public void testGetCurrentChat_whenOpenChatNotExist() {
        when(chatRepository.findByOpenedTrue()).thenReturn(new ArrayList<>());
        assertThrows(NoChatException.class, () -> chatService.getCurrentChat());
    }

    @Test
    public void testGetChatById() {
        when(chatId1.getId()).thenReturn(1L);
        when(chatRepository.findById(1L)).thenReturn(Optional.of(chatId1));

        Chat chatActual = chatService.getChatById(1L);
        assertEquals(chatId1.getId(), chatActual.getId());
    }

    @Test
    public void testGetChatById_whenIdNotExist() {
        assertThrows(NoChatException.class, () -> chatService.getChatById(5));
    }
}