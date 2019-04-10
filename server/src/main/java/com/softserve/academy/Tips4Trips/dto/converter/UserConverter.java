package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.UserDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.administration.User;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserConverter implements Converter<User, UserDTO> {

    AccountRepository accountRepository;
    UserRepository userRepository;

    @Autowired
    public UserConverter(AccountRepository accountRepository,
                         UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
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
    public User convertToEntity(UserDTO userDTO) {
        long id = (userDTO.getId() != null) ? userDTO.getId() : 0;
        User user = userRepository.findById(id)
                .orElseGet(User::new);
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        Optional<Account> account = accountRepository.findByEmail(userDTO.getEmail());
        account.ifPresent(user::setAccount);
        return user;
    }

}
