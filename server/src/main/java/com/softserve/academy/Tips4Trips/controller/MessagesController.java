package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.details.ChatMessageDTO;
import com.softserve.academy.Tips4Trips.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/messages")
public class MessagesController {

    private MessageService messageService;
    private ModelMapper modelMapper;

    @Autowired
    public MessagesController(MessageService messageService, ModelMapper modelMapper){
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")

    public List<ChatMessageDTO> getMessagesByChatId(@PathVariable @NotNull @Positive Long id) {

        return messageService.getMessagesByChatId(id)
                .stream()
                .map(source -> modelMapper.map(source,ChatMessageDTO.class))
                .collect(Collectors.toList());


    }
}
