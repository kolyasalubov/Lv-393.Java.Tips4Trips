package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.UserDTO;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserConverter implements Converter<User, UserDTO> {

    AccountService accountService;

    @Autowired
    public UserConverter(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDTO convertToDTO(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setEmail(user.getAccount().getEmail());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    @Override
    public User convertFromDTO(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        Optional<Account> account = accountService.findByEmail(userDTO.getEmail());
        account.ifPresent(user::setAccount);
        return user;
    }

}
