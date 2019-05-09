package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.details.ChatMessageDTO;
import com.softserve.academy.Tips4Trips.service.MessageService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/messages")
public class MessagesController {


    private static final Logger logger = Logger.getLogger(MessagesController.class);

    private MessageService messageService;
    private ModelMapper modelMapper;

    @Autowired
    public MessagesController(MessageService messageService, ModelMapper modelMapper){
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/messages/{id}")
//    @PreAuthorize("hasRole('USER')")
    public List<ChatMessageDTO> getCurrentMessagesByChatId(@PathVariable @NotNull @Positive Long id) {

        logger.info("MessagesController get messages by chat id");

        return messageService.getMessagesByChatId(id)
                .stream()
                .map(source -> modelMapper.map(source,ChatMessageDTO.class))
                .collect(Collectors.toList());


    }
}
