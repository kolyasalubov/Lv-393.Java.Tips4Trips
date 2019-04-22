package com.softserve.academy.Tips4Trips.mapper;

import com.softserve.academy.Tips4Trips.dto.UserDTO;
import com.softserve.academy.Tips4Trips.entity.administration.User;
import com.softserve.academy.Tips4Trips.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private ModelMapper modelMapper;
    private AccountService accountService;

    @Autowired
    public UserMapper(ModelMapper modelMapper, AccountService accountService) {
        this.modelMapper = modelMapper;
        this.accountService = accountService;
    }
    public UserDTO toDTO(User user){
        return modelMapper.map(user,UserDTO.class);
    }
}
