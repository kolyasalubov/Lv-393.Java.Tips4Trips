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

<<<<<<< HEAD
    public User createUser(User user) {
        user.setId(0L);
        return repository.save(user);
    }

    public User update(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException();
        }
=======

    public User createUser(UserDTO userDTO) {
        User user = userConverter.convertToEntity(userDTO);
        return repository.save(user);
    }

    public User update(UserDTO userDTO) {
        User user = repository.findById(userDTO.getId()).get();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
>>>>>>> 34dacc720e840960dbb65139bd53b58671477958
        return repository.save(user);
    }
}
