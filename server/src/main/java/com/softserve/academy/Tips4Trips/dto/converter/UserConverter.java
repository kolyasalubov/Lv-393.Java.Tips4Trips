package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.UserDto;
import com.softserve.academy.Tips4Trips.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, UserDto> {

    @Override
    public UserDto apply(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setAccountId(user.getAccount().getId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

}
