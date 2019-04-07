package com.softserve.academy.Tips4Trips.service.impl;

import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.User;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl extends ServiceImpl<Account, Long, AccountRepository>
        implements AccountService {

    @Autowired
    public AccountServiceImpl(AccountRepository repository){super(repository);}

    @Override
    public Optional<Account> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<Account> findByUser(User user) {
        return repository.findByUsersContains(user);
    }
}
