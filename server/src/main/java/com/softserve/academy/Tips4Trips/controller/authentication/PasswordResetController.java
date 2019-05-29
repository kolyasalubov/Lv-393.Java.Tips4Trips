package com.softserve.academy.Tips4Trips.controller.authentication;

import com.softserve.academy.Tips4Trips.dto.authentication.PasswordResetDto;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.administration.PasswordResetToken;
import com.softserve.academy.Tips4Trips.repository.PasswordResetTokenRepository;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/authentication")
public class PasswordResetController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/reset-password")
    @Transactional
    public String handlePasswordReset(@Valid  @RequestBody PasswordResetDto form) {


        PasswordResetToken token = tokenRepository.findByToken(form.getToken());
        Account account = token.getAccount();
        String updatedPassword = passwordEncoder.encode(form.getPassword());
        userService.updatePassword(updatedPassword, account.getUser().getId());
        tokenRepository.delete(token);

        return "redirect:/login?resetSuccess";
    }

}