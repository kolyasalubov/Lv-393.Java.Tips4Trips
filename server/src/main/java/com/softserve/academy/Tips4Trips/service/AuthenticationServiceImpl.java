package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.enums.Role;
import com.softserve.academy.Tips4Trips.entity.administration.User;
import com.softserve.academy.Tips4Trips.exception.AuthenticationException;
import com.softserve.academy.Tips4Trips.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationServiceImpl {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;
    private AccountService accountService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     JwtTokenProvider tokenProvider,
                                     AccountService accountService,
                                     UserService userService,
                                     PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.accountService = accountService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(User user) {
        return authenticate(user.getLogin(), user.getPassword());
    }

    public String register(User user, Account account)
            throws AuthenticationException {
        try {

            if (accountService.emailExists(account.getEmail())) {
                throw new AuthenticationException("User e-mail already in "
                        + "use!");
            }

            if (accountService.existsByPhoneNumber(account.getPhoneNumber())) {
                throw new AuthenticationException("User phone number already "
                        + "in use!");
            }

            String login = user.getLogin();
            String password = user.getPassword();
            if (userService.loginExists(login)) {
                throw new AuthenticationException("User login already in use!");
            }

            account.setRole(Role.USER);
            account.setRegistrationDate(new Date());
            Account newAccount = accountService.createAccount(account);
            user.setAccount(newAccount);
            user.setPassword(passwordEncoder.encode(password));
            userService.createUser(user);

            return authenticate(login, password);
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
