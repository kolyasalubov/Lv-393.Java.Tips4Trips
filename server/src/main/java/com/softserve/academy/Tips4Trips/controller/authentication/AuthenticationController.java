package com.softserve.academy.Tips4Trips.controller.authentication;

import com.softserve.academy.Tips4Trips.dto.UserAccountDTO;
import com.softserve.academy.Tips4Trips.dto.UserDTO;
import com.softserve.academy.Tips4Trips.dto.authentication.SignInFormDTO;
import com.softserve.academy.Tips4Trips.dto.authentication.SignUpFormDTO;
import com.softserve.academy.Tips4Trips.dto.converter.AccountConverter;
import com.softserve.academy.Tips4Trips.dto.converter.SignInFormConverter;
import com.softserve.academy.Tips4Trips.dto.converter.SignUpFormConverter;
import com.softserve.academy.Tips4Trips.dto.converter.UserConverter;
import com.softserve.academy.Tips4Trips.dto.details.AccountDetailsDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.administration.User;
import com.softserve.academy.Tips4Trips.security.AuthenticationConstant;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.AuthenticationServiceImpl;
import com.softserve.academy.Tips4Trips.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/authentication")
public class AuthenticationController {

    @Value("${app.cookieExpirationInS}")
    private int cookieExpiration;

    @Autowired
    AuthenticationServiceImpl authenticationService;

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    SignInFormConverter signInFormConverter;

    @Autowired
    SignUpFormConverter signUpFormConverter;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(HttpServletResponse response,
            @Valid @RequestBody SignInFormDTO signInFormDTO) {
        User user = signInFormConverter.convertToEntity(signInFormDTO);
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
            @Valid @RequestBody SignUpFormDTO signUpFormDTO)
                throws Exception {

        User user = signUpFormConverter.convertToUser(signUpFormDTO);
        Account account = signUpFormConverter.convertToEntity(signUpFormDTO);


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
