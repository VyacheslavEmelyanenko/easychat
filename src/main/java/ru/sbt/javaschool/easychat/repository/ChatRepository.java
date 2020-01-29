package ru.sbt.javaschool.easychat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sbt.javaschool.easychat.entity.Chat;

import java.util.List;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {
    List<Chat> findByOpenedTrue();
}
