package ru.sbt.javaschool.easychat.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sbt.javaschool.easychat.entity.Chat;
import ru.sbt.javaschool.easychat.entity.Message;
import ru.sbt.javaschool.easychat.entity.Person;
import ru.sbt.javaschool.easychat.model.RequestEntry;
import ru.sbt.javaschool.easychat.repository.ChatRepository;
import ru.sbt.javaschool.easychat.repository.MessageRepository;
import ru.sbt.javaschool.easychat.repository.PersonRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class MessageServiceTest {

    @TestConfiguration
    public static class MessageServiceTestConfiguration {
        @Bean
        public MessageService messageService() {
            return new MessageService();
        }
        @Bean
        public ChatService chatService() {
            return new ChatService();
        }
        @Bean
        public PersonService personService() {
            return new PersonService();
        }
    }

    @MockBean
    MessageRepository messageRepository;

    @MockBean
    ChatRepository chatRepository;

    @MockBean
    PersonRepository personRepository;

    @Autowired
    MessageService messageService;

    @Mock
    Chat chat;

    @Mock
    Message messageId1, messageId2;

    @Mock
    Person person;

    @Test
    public void testReceiveMsg() {
        String nickname = "Alex";
        String message = "message";
        when(personRepository.findByNickname(nickname)).thenReturn(person);
        when(chatRepository.findByOpenedTrue()).thenReturn(Arrays.asList(chat));
        RequestEntry requestEntry = new RequestEntry();
        requestEntry.setNickname(nickname);
        requestEntry.setMessage(message);
        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);

        messageService.receiveMsg(requestEntry);
        verify(messageRepository, times(1)).save(captor.capture());
        Message messageActual = captor.getValue();
        assertEquals(chat, messageActual.getChat());
        assertEquals(person, messageActual.getPerson());
        assertEquals(message, messageActual.getMessage());
    }

    @Test
    public void testGetMessagesCurrentChat() {
        when(chatRepository.findByOpenedTrue()).thenReturn(Arrays.asList(chat));
        when(messageRepository.findBychat(chat)).thenReturn(Arrays.asList(messageId1, messageId2));

        List<Message> messages = messageService.getMessagesCurrentChat();
        assertTrue(messages.containsAll(Arrays.asList(messageId1, messageId2)));
    }

    @Test
    public void testGetMessagesByIdChat() {
        when(chatRepository.findById(1L)).thenReturn(Optional.of(chat));
        when(messageRepository.findBychat(chat)).thenReturn(Arrays.asList(messageId1, messageId2));

        List<Message> messages = messageService.getMessagesByIdChat(1L);
        assertTrue(messages.containsAll(Arrays.asList(messageId1, messageId2)));
    }
}