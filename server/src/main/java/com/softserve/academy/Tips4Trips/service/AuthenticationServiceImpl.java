package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.enums.Role;
import com.softserve.academy.Tips4Trips.entity.administration.User;
import com.softserve.academy.Tips4Trips.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;
    private AccountService accountService;
    private UserService userService;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     JwtTokenProvider tokenProvider,
                                     AccountService accountService,
                                     UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.accountService = accountService;
        this.userService = userService;
    }

    public String login(User user) {
        return authenticate(user.getLogin(), user.getPassword());
    }

    public String register(User user, Account account) throws Exception {
        try {

            Account oldAccount = accountService
                    .findByEmail(account.getEmail());
            if (oldAccount != null) {
                throw new Exception("User e-mail already in use!");
            }

            User oldUser = userService
                    .findByLogin(user.getLogin());
            if (oldUser != null) {
                throw new Exception("User login already in use!");
            }

            account.setRole(Role.USER);
            accountService.createAccount(account);
            user.setAccount(account);
            userService.createUser(user);

            return authenticate(user.getLogin(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String authenticate(String login, String password) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        login, password
                );

        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        return "Bearer " + jwt;
    }
}
