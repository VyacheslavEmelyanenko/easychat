package ru.sbt.javaschool.easychat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbt.javaschool.easychat.entity.Person;
import ru.sbt.javaschool.easychat.repository.PersonRepository;

import java.util.Optional;

@Transactional
@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public Person createPerson(String nickname) {
        Person person = new Person();
        person.setNickname(nickname);
        return personRepository.save(person);
    }

    public Optional<Person> findPersonByNickname(String nickname) {
        Person person = personRepository.findByNickname(nickname);
        if (person == null) return Optional.empty();
        else return Optional.of(person);
    }
}
