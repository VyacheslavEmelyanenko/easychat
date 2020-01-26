package ru.sbt.javaschool.easychat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sbt.javaschool.easychat.entity.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
}
