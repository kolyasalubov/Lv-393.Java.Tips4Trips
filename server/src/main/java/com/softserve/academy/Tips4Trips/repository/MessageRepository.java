package com.softserve.academy.Tips4Trips.repository;
import com.softserve.academy.Tips4Trips.entity.chat.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByChatId(Long chatId);

    Long countByChatId(Long chatId);
}
