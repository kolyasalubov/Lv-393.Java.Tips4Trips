package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.converter.UserConverter;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.administration.User;
import com.softserve.academy.Tips4Trips.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private AccountService accountService;
    private UserRepository repository;

    @Autowired
    public UserService(AccountService accountService, UserConverter userConverter,
                       UserRepository repository) {
        this.accountService = accountService;
        this.repository = repository;
    }

    public Optional<User> findByLogin(String login) {
        return repository.findByLogin(login);
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

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public User createUser(User user) {
        user.setId(0L);
        return repository.save(user);
    }

    public User update(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException();
        }

    public User createUser(UserDTO userDTO) {
        User user = userConverter.convertToEntity(userDTO);
        return repository.save(user);
    }

    public User update(UserDTO userDTO) {
        User user = repository.findById(userDTO.getId()).get();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        return repository.save(user);
    }
}
