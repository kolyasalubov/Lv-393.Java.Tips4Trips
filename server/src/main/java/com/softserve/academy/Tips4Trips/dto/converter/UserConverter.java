package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.UserDTO;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.User;
<<<<<<< HEAD
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.UserRepository;
=======
import com.softserve.academy.Tips4Trips.service.AccountService;
>>>>>>> 34dacc720e840960dbb65139bd53b58671477958
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserConverter implements Converter<User, UserDTO> {

<<<<<<< HEAD
    AccountRepository accountRepository;
    UserRepository userRepository;

    @Autowired
    public UserConverter(AccountRepository accountRepository,
                         UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
=======
    AccountService accountService;

    @Autowired
    public UserConverter(AccountService accountService) {
        this.accountService = accountService;
>>>>>>> 34dacc720e840960dbb65139bd53b58671477958
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
<<<<<<< HEAD
        long id = (userDTO.getId() != null) ? userDTO.getId() : 0;
        User user = userRepository.findById(id)
                .orElseGet(User::new);
=======
        User user = new User();
>>>>>>> 34dacc720e840960dbb65139bd53b58671477958
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        Optional<Account> account = accountService.findByEmail(userDTO.getEmail());
        account.ifPresent(user::setAccount);
        return user;
    }

}
