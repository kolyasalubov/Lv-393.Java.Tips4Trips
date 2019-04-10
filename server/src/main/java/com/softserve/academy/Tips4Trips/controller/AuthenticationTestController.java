package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.AccountDTO;
import com.softserve.academy.Tips4Trips.dto.UserAccountDTO;
import com.softserve.academy.Tips4Trips.dto.UserDTO;
import com.softserve.academy.Tips4Trips.dto.converter.AccountConverter;
import com.softserve.academy.Tips4Trips.dto.converter.UserConverter;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.User;
import com.softserve.academy.Tips4Trips.security.AuthenticationConstant;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.UserService;
import com.softserve.academy.Tips4Trips.service.AuthenticationServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authentication")
public class AuthenticationTestController {

    @Autowired
    AuthenticationServiceImpl authenticationService;

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    UserConverter userConverter;

    @Autowired
    AccountConverter accountConverter;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody
                                                          UserDTO userDto) {
        User user = userConverter.convertToEntity(userDto);
        String token = authenticationService.login(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(AuthenticationConstant
                .AUTHENTICATION_TOKEN_HEADER, token);
        //this should only add headers and redirect,
        // but we don't have a page for redirection
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody UserAccountDTO userAccountDTO)
            throws Exception {

        UserDTO userDTO = userAccountDTO.getUserDto();
        AccountDTO accountDTO = userAccountDTO.getAccountDTO();
        Account account = accountConverter.convertToEntity(accountDTO);
        User user = userConverter.convertToEntity(userDTO);

        String token = authenticationService.register(user, account);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(AuthenticationConstant
                .AUTHENTICATION_TOKEN_HEADER, token);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(token);
    }
}
