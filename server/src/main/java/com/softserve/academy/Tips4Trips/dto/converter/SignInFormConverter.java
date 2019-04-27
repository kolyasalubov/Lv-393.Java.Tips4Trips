package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.authentication.SignInFormDTO;
import com.softserve.academy.Tips4Trips.entity.administration.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignInFormConverter implements Converter<User, SignInFormDTO> {

    private ModelMapper modelMapper;

    @Autowired
    public SignInFormConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public SignInFormDTO convertToDTO(User user) {
        return modelMapper.map(user, SignInFormDTO.class);
    }

    @Override
    public User convertToEntity(SignInFormDTO signInFormDTO) {
        return modelMapper.map(signInFormDTO, User.class);
    }

}
