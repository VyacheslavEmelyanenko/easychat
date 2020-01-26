package ru.sbt.javaschool.easychat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sbt.javaschool.easychat.entity.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Person findByNickname(String nickname);
}
