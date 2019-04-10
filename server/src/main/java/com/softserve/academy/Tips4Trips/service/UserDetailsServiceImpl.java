package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.User;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.UserRepository;
import com.softserve.academy.Tips4Trips.security.UserDetailsImpl;
import com.softserve.academy.Tips4Trips.service.UserService;
import com.softserve.academy.Tips4Trips.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        User user = userService.findByLogin(login);
        if (user == null) {
             throw new UsernameNotFoundException("User with login '"
                     + login + "' not found.");
        }
        Account account = accountService.findByUser(user);
        if (user == null) {
            throw new UsernameNotFoundException(
                    "User with login '" + login + "' not found.",
                    new AccountNotFoundException(
                            "Account for user '" + login + "' not found."
                    )
            );
        }
        return UserDetailsImpl.create(user, account.getRole());
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new UsernameNotFoundException(
                    "User with id '" + id + "' not found."
            );
        }

        Account account = accountService.findByUser(user);
        if (user == null) {
            throw new UsernameNotFoundException(
                    "User with id '" + id+ "' not found.",
                    new AccountNotFoundException(
                            "Account for user '" + id + "' not found."
                    )
            );
        }
        return UserDetailsImpl.create(user, account.getRole());
    }
}
