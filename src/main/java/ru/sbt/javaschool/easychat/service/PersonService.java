package ru.sbt.javaschool.easychat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbt.javaschool.easychat.entity.Person;
import ru.sbt.javaschool.easychat.repository.PersonRepository;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public Person findPersonByNickname(String nickname) {
        return personRepository.findByNickname(nickname);
    }
}
