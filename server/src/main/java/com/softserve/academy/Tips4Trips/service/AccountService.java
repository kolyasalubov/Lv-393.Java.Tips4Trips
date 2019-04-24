package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.converter.AccountConverter;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.administration.User;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountService {

    private static final Logger logger = Logger.getLogger(AccountService.class);

    private AccountConverter accountConverter;
    private AccountRepository repository;

    @Autowired
    public AccountService(AccountConverter accountConverter, AccountRepository repository) {
        this.accountConverter = accountConverter;
        this.repository = repository;
    }

    public List<Account> findAll() {
        return repository.findAll();
    }

    public Account findByUser(User user) {
        Optional<Account> account = repository.findByUsersContains(user);
        return account.isPresent() ? account.get() : null;
    }

    public Account findById(Long id) {
        Optional<Account> account = repository.findById(id);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    boolean emailExists(String email) {
        return repository.existsByEmail(email);
    }

    public Account findByEmail(String email) {

        Optional<Account> account = repository.findByEmail(email);
        if(account.isPresent()){
            return account.get();
        }else {
            throw  new NoSuchElementException();
        }

    }


    public Account createAccount(Account account) {
        account.setId(-1L);
        return repository.save(account);
    }


    public Account update(Account account) {
        return repository.save(account);
    }

    boolean existsByPhoneNumber(String number) {
        return repository.existsByPhoneNumber(number);
    }
}
