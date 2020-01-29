package ru.sbt.javaschool.easychat.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sbt.javaschool.easychat.entity.Person;
import ru.sbt.javaschool.easychat.repository.PersonRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersonServiceTest.PersonServiceTestConfiguration.class)
class PersonServiceTest {

    @Configuration
    static class PersonServiceTestConfiguration {
        @Bean
        public PersonService personService() {
            return new PersonService();
        }
    }

    @MockBean
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @Mock
    Person person;

    @Test
    void testCreatePerson() {
        String nickname = "Alex";
        ArgumentCaptor<Person> captor = ArgumentCaptor.forClass(Person.class);

        personService.createPerson(nickname);
        verify(personRepository, times(1)).save(captor.capture());
        Person personActual = captor.getValue();
        assertEquals(nickname, personActual.getNickname());
    }

    @Test
    void testFindPersonByNickname_whenPersonExist() {
        String nickname = "Alex";
        when(person.getNickname()).thenReturn(nickname);
        when(personRepository.findByNickname(nickname)).thenReturn(person);

        Person personActual = personService.findPersonByNickname(nickname);
        assertEquals(nickname, personActual.getNickname());
    }

    @Test
    void testFindPersonByNickname_whenPersonNotExist() {
        String nickname = "Alex";
        when(person.getNickname()).thenReturn(nickname);
        when(personRepository.findByNickname(nickname)).thenReturn(null);
        ArgumentCaptor<Person> captor = ArgumentCaptor.forClass(Person.class);
        when(personRepository.save(ArgumentCaptor.forClass(Person.class).capture())).thenReturn(person);

        Person personActual = personService.findPersonByNickname(nickname);
        verify(personRepository, times(1)).save(captor.capture());
        Person personCreated = captor.getValue();
        assertEquals(personCreated.getNickname(), personActual.getNickname());
    }
}