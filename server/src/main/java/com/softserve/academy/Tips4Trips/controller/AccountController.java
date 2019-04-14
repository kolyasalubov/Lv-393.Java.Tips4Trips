package com.softserve.academy.Tips4Trips.controller;



import com.softserve.academy.Tips4Trips.dto.converter.AccountConverter;
import com.softserve.academy.Tips4Trips.dto.details.AccountDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.AccountInfoDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.log4j.Logger;

@RestController
@CrossOrigin
@RequestMapping("/accounts")
public class AccountController {

    private static final Logger logger = Logger.getLogger(AccountController.class);

    private AccountService accountService;
    private AccountConverter accountConverter;

    @Autowired
    public AccountController(AccountService accountService, AccountConverter accountConverter){
        this.accountService = accountService;
        this.accountConverter = accountConverter;
    }

    @GetMapping
    public ResponseEntity<List<AccountInfoDTO>> getAll() {
        return new ResponseEntity<>(accountConverter.convertToInfoDTO(accountService
                .findAll()), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AccountDetailsDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(accountConverter
                .convertToDTO(accountService.findById(id)), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<AccountDetailsDTO> createAccount(@RequestBody AccountDetailsDTO accountDTO){
        Account account = accountService.createAccount(accountConverter.convertToEntity(accountDTO));
        return new ResponseEntity<>(accountConverter.convertToDTO(account), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<AccountDetailsDTO> updateAccount(@RequestBody AccountDetailsDTO accountDTO) {
        Account account = accountService.update(accountConverter.convertToEntity(accountDTO));
        return new ResponseEntity<>(accountConverter.convertToDTO(account), HttpStatus.ACCEPTED);

    }
}
