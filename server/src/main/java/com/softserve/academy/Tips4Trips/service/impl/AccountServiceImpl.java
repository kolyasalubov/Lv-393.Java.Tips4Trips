package com.softserve.academy.Tips4Trips.service.impl;

import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends ServiceImpl<Account, Long, AccountRepository>
        implements AccountService {

    AccountRepository repository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository){super(repository);}

}
