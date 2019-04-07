package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.UserDTO;
import com.softserve.academy.Tips4Trips.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setEmail(user.getAccount().getEmail());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

}
