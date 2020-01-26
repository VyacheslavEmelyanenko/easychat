package ru.sbt.javaschool.easychat.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sbt.javaschool.easychat.entity.Chat;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {
    List<Chat> findByOpenedTrue();
    List<Chat> findByOpenedTrueOrderByStartDateDesc();

    @Modifying
    @Transactional
    @Query("update Chat c set c.endDate = :date, c.opened = false where c.id in :ids")
    int setOpenedFalse(@Param("date") LocalDateTime endTime, @Param("ids") Iterable<Long> ids);
}
