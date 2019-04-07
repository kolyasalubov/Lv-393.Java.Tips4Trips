package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.UserDTO;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.User;
import com.softserve.academy.Tips4Trips.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public interface UserService extends Service<User, Long, UserRepository> {
    Optional<User> findByLogin(String login);

    List<User> findByAccount(Account account);

    boolean loginExists(String login);

    boolean emailExists(String email);

    User createUser(UserDTO userDTO);

    User update(UserDTO userDTO);
}
