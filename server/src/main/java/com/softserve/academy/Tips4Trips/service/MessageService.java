package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.constants.ExceptionMessages;
import com.softserve.academy.Tips4Trips.entity.chat.Message;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;
import com.softserve.academy.Tips4Trips.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }



    @Transactional
    public void delete(Long id){
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()) {
            messageRepository.deleteById(id);
        } else {
            throw new DataNotFoundException(ExceptionMessages.MESSAGE_IS_NOT_FOUND);
        }
    }
    @Transactional
    public Message add(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByChatId(Long chatId) {
        return messageRepository.findAllByChatId(chatId);
    }
}
