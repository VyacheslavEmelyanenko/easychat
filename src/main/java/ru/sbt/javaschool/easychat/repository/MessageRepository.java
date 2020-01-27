package ru.sbt.javaschool.easychat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sbt.javaschool.easychat.entity.Chat;
import ru.sbt.javaschool.easychat.entity.Message;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findBychat(Chat chat);
}
