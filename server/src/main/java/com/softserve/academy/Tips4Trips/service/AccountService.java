package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.AccountDTO;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;

import java.util.Optional;

public interface AccountService extends Service<Account, Long, AccountRepository> {

    Optional<Account> findByEmail(String email);

    Account createAccount(AccountDTO accountDTO);

}
