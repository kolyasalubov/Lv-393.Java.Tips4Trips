package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.Positive;


@Controller
public class ChatController {

    private ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }
//
//    @PostMapping(value = "/chats")
//    @PreAuthorize("hasRole=('USER')")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addChatToTrip(@Validated @RequestBody AddChatDto addChatDto) {
//
//        Chat chat = chatService.addChat(addChatDto.getChatType());
//        return new IdDto(chat.getId());
//    }

    @GetMapping(value = "/chats/{chatId}/count")
    @ResponseStatus(HttpStatus.OK)
    public Long countOfMessages(@PathVariable(value = "chatId") @Positive @NonNull Long chatId) {

        return chatService.getCountOfMessages(chatId);
    }

}
