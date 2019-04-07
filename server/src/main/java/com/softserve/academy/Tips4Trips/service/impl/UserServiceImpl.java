package com.softserve.academy.Tips4Trips.service.impl;

import com.softserve.academy.Tips4Trips.dto.UserDTO;
import com.softserve.academy.Tips4Trips.dto.converter.reverse.ReverseUserConverter;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.User;
import com.softserve.academy.Tips4Trips.repository.UserRepository;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends ServiceImpl<User, Long, UserRepository>
        implements UserService {

    private AccountService accountService;
    private ReverseUserConverter reverseUserConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AccountService accountService,
                           ReverseUserConverter reverseUserConverter) {
        super(userRepository);
        this.accountService = accountService;
        this.reverseUserConverter = reverseUserConverter;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public List<User> findByAccount(Account account) {
        return repository.findByAccount(account);
    }

    @Override
    public boolean loginExists(String login) {
        return repository.existsByLogin(login);
    }

    @Override
    public boolean emailExists(String email) {
        return repository.existsByAccountEmail(email);
    }

    @Override
    public User createUser(UserDTO userDTO) {
        User user = reverseUserConverter.convert(userDTO);
        return repository.save(user);
    }

    @Override
    public User update(UserDTO userDTO) {
        User user = findById(userDTO.getId()).get();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        return repository.save(user);
    }
}
