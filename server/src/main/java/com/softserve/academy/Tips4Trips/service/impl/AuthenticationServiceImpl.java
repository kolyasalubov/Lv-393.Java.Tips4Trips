package com.softserve.academy.Tips4Trips.service.impl;

import com.softserve.academy.Tips4Trips.dto.AccountDTO;
import com.softserve.academy.Tips4Trips.dto.UserAccountDTO;
import com.softserve.academy.Tips4Trips.dto.UserDTO;
import com.softserve.academy.Tips4Trips.dto.converter.reverse.ReverseAccountConverter;
import com.softserve.academy.Tips4Trips.dto.converter.reverse.ReverseUserConverter;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.Role;
import com.softserve.academy.Tips4Trips.entity.User;
import com.softserve.academy.Tips4Trips.security.JwtTokenProvider;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.UserService;

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
    private ReverseAccountConverter reverseAccountConverter;
    private ReverseUserConverter reverseUserConverter;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     JwtTokenProvider tokenProvider,
                                     AccountService accountService,
                                     UserService userService,
                                     ReverseAccountConverter reverseAccountConverter,
                                     ReverseUserConverter reverseUserConverter) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.accountService = accountService;
        this.userService = userService;
        this.reverseAccountConverter = reverseAccountConverter;
        this.reverseUserConverter = reverseUserConverter;
    }

    public String login(UserDTO userDto) {
        return authenticate(userDto.getLogin(), userDto.getPassword());
    }

    public String register(UserAccountDTO userAccountDto) throws Exception {
        try {
            AccountDTO accountDto = userAccountDto.getAccountDTO();
            UserDTO userDto = userAccountDto.getUserDto();

            Optional<Account> account = accountService
                    .findByEmail(accountDto.getEmail());
            if (account.isPresent()) {
                throw new Exception("User e-mail already in use!");
            }

            Optional<User> user = userService
                    .findByLogin(userDto.getLogin());
            if (user.isPresent()) {
                throw new Exception("User login already in use!");
            }

            Account newAccount = createAccount(accountDto);
            createUser(userDto, newAccount);
            return authenticate(userDto.getLogin(), userDto.getPassword());
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

    private Account createAccount(AccountDTO accountDto) {
        accountDto.setRole(Role.USER);
        Account account = reverseAccountConverter
                .apply(accountDto);
        accountService.save(account);
        return account;
    }

    private void createUser(UserDTO userDto, Account account) {
        User user = reverseUserConverter.apply(userDto);
        user.setAccount(account);
        userService.save(user);
    }
}
