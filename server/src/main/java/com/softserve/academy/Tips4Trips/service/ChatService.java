package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.constants.ExceptionMessages;
import com.softserve.academy.Tips4Trips.entity.chat.Chat;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;
import com.softserve.academy.Tips4Trips.repository.ChatRepository;
import com.softserve.academy.Tips4Trips.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ChatService {
    private ChatRepository chatRepository;
    private MessageRepository messageRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository, MessageRepository messageRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
    }

    @Transactional
    public Chat getChatById(Long chatId) {
        return chatRepository.findById(chatId).
                orElseThrow(() -> new DataNotFoundException(ExceptionMessages.CHAT_BY_THIS_ID_IS_NOT_FOUND));
    }

    @Transactional
    public void deleteChatById(Long chatId) {

        if (chatRepository.existsById(chatId)) {
            chatRepository.deleteById(chatId);
        } else {
            throw new DataNotFoundException(ExceptionMessages.CHAT_BY_THIS_ID_IS_NOT_FOUND);
        }
    }


    public void isChatExist(Long id) {
        if (!chatRepository.existsById(id)) {
            throw new DataNotFoundException(ExceptionMessages.CHAT_BY_THIS_ID_IS_NOT_FOUND);
        }
    }

    public Long getCountOfMessages(Long chatId) {

        if (chatRepository.existsById(chatId)) {
            return messageRepository.countByChatId(chatId);
        } else {
            throw new DataNotFoundException(ExceptionMessages.CHAT_BY_THIS_ID_IS_NOT_FOUND);
        }

    }

    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }


}
