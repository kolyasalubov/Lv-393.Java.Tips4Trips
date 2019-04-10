package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.UserDTO;
import com.softserve.academy.Tips4Trips.dto.converter.UserConverter;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.User;
import com.softserve.academy.Tips4Trips.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private AccountService accountService;
    private UserConverter userConverter;
    private UserRepository repository;

    @Autowired
    public UserService(AccountService accountService, UserConverter userConverter,
                       UserRepository repository) {
        this.accountService = accountService;
        this.userConverter = userConverter;
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
