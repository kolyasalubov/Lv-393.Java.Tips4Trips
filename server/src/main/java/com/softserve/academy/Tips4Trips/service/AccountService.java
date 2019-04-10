package com.softserve.academy.Tips4Trips.service;

<<<<<<< HEAD
=======
import com.softserve.academy.Tips4Trips.dto.AccountDTO;
import com.softserve.academy.Tips4Trips.dto.converter.AccountConverter;
>>>>>>> 3ebe6b3ebe2c8d9d2652d6f3f31fbeb22b4c5820
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.User;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private AccountConverter accountConverter;
    private AccountRepository repository;

<<<<<<< HEAD
    Optional<Account> findByUser(User user);
=======
    @Autowired
    public AccountService(AccountConverter accountConverter, AccountRepository repository) {
        this.accountConverter = accountConverter;
        this.repository = repository;
    }

    public Optional<Account> findById(Long id) {
        return repository.findById(id);
    }


    public Optional<Account> findByEmail(String email) {
        return repository.findByEmail(email);
    }


    public Account createAccount(AccountDTO accountDTO) {
        Account account = accountConverter.convertToEntity(accountDTO);
        return repository.save(account);
    }


    public Account update(AccountDTO accountDTO) {
        Account account = repository.findById(accountDTO.getId()).get();
        account.setFirstName(accountDTO.getFirstName());
        account.setLastName(accountDTO.getLastName());
        account.setPhoneNumber(accountDTO.getPhoneNumber());
        account.setAbout(accountDTO.getAbout());
        return repository.save(account);
    }
>>>>>>> 3ebe6b3ebe2c8d9d2652d6f3f31fbeb22b4c5820
}
