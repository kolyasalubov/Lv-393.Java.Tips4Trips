package com.softserve.academy.Tips4Trips.controller.authentication;

import com.softserve.academy.Tips4Trips.dto.authentication.Mail;
import com.softserve.academy.Tips4Trips.dto.authentication.PasswordForgotDto;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.administration.PasswordResetToken;
import com.softserve.academy.Tips4Trips.repository.PasswordResetTokenRepository;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/authentication")
public class PasswordForgotController {

    @Autowired private AccountService accountService;
    @Autowired private PasswordResetTokenRepository tokenRepository;
    @Autowired private EmailService emailService;

    @PostMapping("/forgot-password")
    public String processForgotPasswordForm(@Valid @RequestBody PasswordForgotDto form,
                                            BindingResult result,
                                            HttpServletRequest request) {

        if (result.hasErrors()){
            return "forgot-password";
        }

        Account account = accountService.findByEmail(form.getEmail());
        if (account == null){
            result.rejectValue("templates/email", null, "We could not find an account for that e-mail address.");
            return "We could not find an account for that e-mail address.";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setAccount(account);
        token.setExpiryDate(30); //30 minutes
        tokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("tips4trips.sup@gmail.com");
        mail.setTo(account.getEmail());
        mail.setSubject("Password reset request");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("account", account);
        model.put("signature", "https://tips4trips.com");
        //String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String url = "http://localhost:4200";
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);

        return "redirect:/forgot-password?success";

    }

}