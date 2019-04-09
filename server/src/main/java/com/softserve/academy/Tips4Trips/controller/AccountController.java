package com.softserve.academy.Tips4Trips.controller;


import com.softserve.academy.Tips4Trips.dto.AccountDTO;
import com.softserve.academy.Tips4Trips.dto.converter.AccountConverter;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.service.AccountService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;
    private AccountConverter accountConverter;

    @Autowired
    public AccountController(AccountService accountService, AccountConverter accountConverter){
        this.accountService = accountService;
        this.accountConverter = accountConverter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getById(@PathVariable long id) {
        Optional<Account> account = accountService.findById(id);
        return account.map(u -> new ResponseEntity<>(accountConverter.
                convert(u), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO){
        try {
            Account account = accountService.createAccount(accountDTO);
            return new ResponseEntity<>(accountConverter.convert(account), HttpStatus.CREATED);
        } catch (HibernateException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<AccountDTO> updateUser(@RequestBody AccountDTO accountDTO) {
        try {
            Account account = accountService.update(accountDTO);
            return new ResponseEntity<>(accountConverter.convert(account), HttpStatus.ACCEPTED);
        } catch (HibernateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
