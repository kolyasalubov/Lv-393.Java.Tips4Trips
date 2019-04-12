package com.softserve.academy.Tips4Trips.controller;


import com.softserve.academy.Tips4Trips.dto.AccountDTO;
import com.softserve.academy.Tips4Trips.dto.converter.AccountConverter;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(accountConverter
                .convertToDTO(accountService.findById(id)), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO){
        Account account = accountService.createAccount(accountConverter.convertToEntity(accountDTO));
        return new ResponseEntity<>(accountConverter.convertToDTO(account), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO accountDTO) {
        Account account = accountService.update(accountConverter.convertToEntity(accountDTO));
        return new ResponseEntity<>(accountConverter.convertToDTO(account), HttpStatus.ACCEPTED);

    }
}
