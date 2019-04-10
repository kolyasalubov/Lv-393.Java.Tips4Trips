package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.converter.AccountConverter;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.User;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AccountService  {

   private AccountConverter accountConverter;
   private AccountRepository repository;

    @Autowired
    public AccountService( AccountConverter accountConverter, AccountRepository repository) {
        this.accountConverter = accountConverter;
        this.repository = repository;
    }

<<<<<<< HEAD
    public Account findById(Long id) {
        return repository.findById(id).get();
=======

    public Optional<Account> findById(Long id) {
        return repository.findById(id);
>>>>>>> 34dacc720e840960dbb65139bd53b58671477958
    }


    public Account findByEmail(String email) {
        return repository.findByEmail(email).get();
    }


<<<<<<< HEAD
    public Account createAccount(Account account) {
=======
    public Account createAccount(AccountDTO accountDTO) {
        Account account = accountConverter.convertToEntity(accountDTO);
>>>>>>> 34dacc720e840960dbb65139bd53b58671477958
        return repository.save(account);
    }


<<<<<<< HEAD
    public Account update(Account account) {
        return repository.save(account);
=======
    public Account update(AccountDTO accountDTO) {
        Account account = repository.findById(accountDTO.getId()).get();
        account.setFirstName(accountDTO.getFirstName());
        account.setLastName(accountDTO.getLastName());
        account.setPhoneNumber(accountDTO.getPhoneNumber());
        account.setAbout(accountDTO.getAbout());
            return repository.save(account);
        }
>>>>>>> 34dacc720e840960dbb65139bd53b58671477958
    }
