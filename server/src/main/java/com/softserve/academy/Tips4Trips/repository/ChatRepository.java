package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {


//    Chat addChat(String type);
//
//    Chat getChatById(Long chatId);
//
//    void deleteChatById(Long chatId);
//
//    Chat banUserInChat(Long chatId, Long userId);
//
//    Chat updateChat(Chat chat, Long chatId);
//
//    Chat unBanUserFromChat(Long chatId, Long userId);
//
//    Long getCountOfMessages(Long chatId);
}
