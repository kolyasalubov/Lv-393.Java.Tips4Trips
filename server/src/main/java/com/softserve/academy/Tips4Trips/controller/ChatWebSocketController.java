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
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class ChatWebSocketController {

    private static final Logger logger = Logger.getLogger(ChatController.class);

        private final SimpMessagingTemplate  simpMessagingTemplate;

    private AccountService accountService;
    private ChatService chatService;
    private MessageService messageService;
    private ModelMapper modelMapper;

    @Autowired
    public ChatWebSocketController(SimpMessagingTemplate  simpMessagingTemplate, AccountService accountService, ChatService chatService, MessageService messageService, ModelMapper modelMapper) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.accountService = accountService;
        this.chatService = chatService;
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }


    @MessageMapping("/send/message")
    public void sendMessage(@Payload ChatMessageInfoDTO chatMessageInfoDTO) {

        logger.info("ChatWebsocketController send message");

        Message message = new Message();

        message.setSender(accountService.findById(chatMessageInfoDTO.getAccountId()));
        message.setChat(chatService.getChatById(chatMessageInfoDTO.getChatId()));
        message.setContent(chatMessageInfoDTO.getContent());

        Message sendBackMessage = messageService.add(message);
        simpMessagingTemplate.convertAndSend("/topic/messages", modelMapper.map(sendBackMessage,ChatMessageDTO.class));

    }



}

