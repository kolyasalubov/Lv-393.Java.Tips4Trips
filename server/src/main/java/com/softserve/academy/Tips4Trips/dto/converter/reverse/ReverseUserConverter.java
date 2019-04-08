package com.softserve.academy.Tips4Trips.dto.converter.reverse;

import com.softserve.academy.Tips4Trips.dto.UserDTO;
import com.softserve.academy.Tips4Trips.dto.converter.Converter;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.User;
import com.softserve.academy.Tips4Trips.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReverseUserConverter implements Converter<UserDTO, User> {

    AccountService accountService;

    @Autowired
    public ReverseUserConverter(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public User apply(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        Optional<Account> account = accountService.findByEmail(userDTO.getEmail());
        account.ifPresent(user::setAccount);
        return user;
    }
}
