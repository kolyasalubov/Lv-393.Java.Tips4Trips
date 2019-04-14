package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.converter.UserConverter;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.administration.User;
import com.softserve.academy.Tips4Trips.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class);

    private AccountService accountService;
    private UserRepository repository;

    @Autowired
    public UserService(AccountService accountService, UserConverter userConverter,
                       UserRepository repository) {
        this.accountService = accountService;
        this.repository = repository;
    }

    public User findByLogin(String login) {
        return repository.findByLogin(login).get();
    }

    public List<User> findByAccount(Account account) {
        return repository.findByAccount(account);
    }

    public boolean loginExists(String login) {
        return repository.existsByLogin(login);
    }

    public boolean emailExists(String email) {
        return repository.existsByAccountEmail(email);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).get();
    }

    public void deleteById(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

    public User createUser(User user) {
        user.setId(0L);
        return repository.save(user);
    }

    public User update(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException();
        }
        return repository.save(user);
    }
}
