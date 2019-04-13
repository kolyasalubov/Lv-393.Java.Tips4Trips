package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.UserAccountDTO;
import com.softserve.academy.Tips4Trips.dto.UserDTO;
import com.softserve.academy.Tips4Trips.dto.converter.AccountConverter;
import com.softserve.academy.Tips4Trips.dto.converter.UserConverter;
import com.softserve.academy.Tips4Trips.dto.details.AccountDetailsDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.administration.User;
import com.softserve.academy.Tips4Trips.security.AuthenticationConstant;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.UserService;
import com.softserve.academy.Tips4Trips.service.AuthenticationServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/authentication")
public class AuthenticationTestController {

    @Value("${app.cookieExpirationInS}")
    private int cookieExpiration;

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
    public ResponseEntity<?> authenticateUser(HttpServletResponse response,
            @Valid @RequestBody UserDTO userDto) {
        User user = userConverter.convertToEntity(userDto);
        String token = authenticationService.login(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(AuthenticationConstant
                .AUTHENTICATION_TOKEN_HEADER, token);
        response.addCookie(createCookie(token));
        //this should only add headers and redirect,
        // but we don't have a page for redirection
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(HttpServletResponse response,
            @Valid @RequestBody UserAccountDTO userAccountDTO)
                throws Exception {

        UserDTO userDTO = userAccountDTO.getUser();
        AccountDetailsDTO accountDTO = userAccountDTO.getAccount();
        Account account = accountConverter.convertToEntity(accountDTO);
        User user = userConverter.convertToEntity(userDTO);

        String token = authenticationService.register(user, account);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(AuthenticationConstant
                .AUTHENTICATION_TOKEN_HEADER, token);
        response.addCookie(createCookie(token));
        //this should only add headers and redirect,
        // but we don't have a page for redirection
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(token);
    }

    private Cookie createCookie(String token) {
        final Cookie cookie = new Cookie(AuthenticationConstant
                .AUTHENTICATION_TOKEN_HEADER, token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(cookieExpiration);
        return cookie;
    }
}
