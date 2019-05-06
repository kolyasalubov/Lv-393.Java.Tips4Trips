package com.softserve.academy.Tips4Trips.controller;


import com.softserve.academy.Tips4Trips.dto.details.ChatMessageDTO;
import com.softserve.academy.Tips4Trips.dto.info.ChatMessageInfoDTO;
import com.softserve.academy.Tips4Trips.entity.chat.Message;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.ChatService;
import com.softserve.academy.Tips4Trips.service.MessageService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import java.util.Map;

import static java.lang.String.format;

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

//    @MessageMapping("/send/message")
//    public void sendMessage(@Payload ChatMessageInfoDTO chatMessageInfoDTO) {
//
//        logger.info("ChatWebsocketController send message");
//
//        chatMessageInfoDTO.setAccountId(1L);
//        chatMessageInfoDTO.setChatId(1L);
//        chatMessageInfoDTO.setContent("helloo");
//
//        Message message = new Message();
//
//        message.setSender(accountService.findById(chatMessageInfoDTO.getAccountId()));
//        message.setChat(chatService.getChatById(chatMessageInfoDTO.getChatId()));
//        message.setContent(chatMessageInfoDTO.getContent());
////modelMapper.map(messageService.add(message), ChatMessageDTO.class)
//        sendingOperations.convertAndSend("/topic/messages/","asdfasdfasf");
//
//    }

    @MessageMapping("/send/message")
    public void sendMessage(@Nullable final String messageStr) {

        logger.info("ChatWebsocketController send message");

        //chatMessageInfoDTO.setAccountId(1L);
        //chatMessageInfoDTO.setChatId(1L);
        //chatMessageInfoDTO.setContent("helloo");

        Message message = new Message();

        message.setSender(accountService.findById(1L));
        message.setChat(chatService.getChatById(1L));
        message.setContent(messageStr);



//        modelMapper.addMappings(new PropertyMap<Message, ChatMessageDTO>() {
//            protected void configure() {
//                map().setChatId(source.getChat().getId());
//                map().setFirstName(source.getSender().getFirstName());
//                map().setLastName(source.getSender().getLastName());
//            }
//        });


       // simpMessagingTemplate.convertAndSend("/topic/messages",modelMapper.map(messageService.add(message), ChatMessageDTO.class));

        Message sendBackMessage = messageService.add(message);
        logger.info(sendBackMessage.getSender().getFirstName());
        simpMessagingTemplate.convertAndSend("/topic/messages", modelMapper.map(sendBackMessage,ChatMessageDTO.class));

    }



}

