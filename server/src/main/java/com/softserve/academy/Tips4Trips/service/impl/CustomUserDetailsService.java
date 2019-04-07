package com.softserve.academy.Tips4Trips.service.impl;

import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.User;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.UserRepository;
import com.softserve.academy.Tips4Trips.security.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with login '"
                                + login + "' not found.")
                );

        Account account = accountRepository.findByUser(user)
                .orElseThrow(() ->
                    new UsernameNotFoundException(
                            "User with login '" + login + "' not found.",
                            new AccountNotFoundException(
                                    "Account for user '" + login + "' not found."
                            )
                    )
                );
        return UserDetailsImpl.create(user, account.getRole());
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException(
                        "User with id '" + id + "' not found."
                )
        );

        Account account = accountRepository.findByUser(user)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User with id '" + id + "not found.",
                                new AccountNotFoundException(
                                        "Account with user id '" + id
                                                + "' not found."
                                )
                        )
                );

        return UserDetailsImpl.create(user, account.getRole());
    }
}
