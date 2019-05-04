package com.softserve.academy.Tips4Trips.controller;


import com.softserve.academy.Tips4Trips.dto.details.ChatMessageDTO;
import com.softserve.academy.Tips4Trips.dto.info.ChatMessageInfoDTO;
import com.softserve.academy.Tips4Trips.entity.chat.Message;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.ChatService;
import com.softserve.academy.Tips4Trips.service.MessageService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {

    private static final Logger logger = Logger.getLogger(ChatController.class);

    private final SimpMessageSendingOperations sendingOperations;
    private AccountService accountService;
    private ChatService chatService;
    private MessageService messageService;
    private ModelMapper modelMapper;

    @Autowired
    public ChatWebSocketController(SimpMessageSendingOperations sendingOperations, AccountService accountService, ChatService chatService, MessageService messageService, ModelMapper modelMapper) {
        this.sendingOperations = sendingOperations;
        this.accountService = accountService;
        this.chatService = chatService;
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    @MessageMapping("/message")
    public void sendMessage(@Payload ChatMessageInfoDTO chatMessageInfoDTO) {

        Message message = new Message();

        message.setSender(accountService.findById(chatMessageInfoDTO.getAccountId()));
        message.setChat(chatService.getChatById(chatMessageInfoDTO.getChatId()));
        message.setContent(chatMessageInfoDTO.getContent());

        sendingOperations.convertAndSend(modelMapper.map(messageService.add(message), ChatMessageDTO.class));

    }
}
