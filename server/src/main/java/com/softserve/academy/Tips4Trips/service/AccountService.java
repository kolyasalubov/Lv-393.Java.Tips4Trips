package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.AccountDTO;
import com.softserve.academy.Tips4Trips.dto.converter.AccountConverter;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private AccountConverter accountConverter;
    private AccountRepository repository;

    @Autowired
    public AccountService(AccountConverter accountConverter, AccountRepository repository) {
        this.accountConverter = accountConverter;
        this.repository = repository;
    }

    public Account findById(Long id) {
        return repository.findById(id).get();
    }


    public Account findByEmail(String email) {
        return repository.findByEmail(email).get();
    }


    public Account createAccount(Account account) {
        return repository.save(account);
    }


    public Account update(Account account) {
        return repository.save(account);
    }
}
