package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.authentication.SignUpFormDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.administration.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignUpFormConverter implements Converter<Account, SignUpFormDTO> {

    private ModelMapper modelMapper;

    @Autowired
    public SignUpFormConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public SignUpFormDTO convertToDTO(Account account) {
        return modelMapper.map(account, SignUpFormDTO.class);
    }


    @Override
    public Account convertToEntity(SignUpFormDTO signUpFormDTO) {
        return modelMapper.map(signUpFormDTO, Account.class);
    }

    public User convertToUser(SignUpFormDTO signUpFormDTO){
        return modelMapper.map(signUpFormDTO, User.class);
    }
}