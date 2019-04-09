package com.softserve.academy.Tips4Trips.service.impl;

import com.softserve.academy.Tips4Trips.dto.AccountDTO;
import com.softserve.academy.Tips4Trips.dto.converter.reverse.ReverseAccountConverter;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl extends ServiceImpl<Account, Long, AccountRepository>
        implements AccountService {

    ReverseAccountConverter reverseAccountConverter;

    @Autowired
    public AccountServiceImpl(AccountRepository repository, ReverseAccountConverter reverseAccountConverter) {
        super(repository);
        this.reverseAccountConverter = reverseAccountConverter;

    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Account createAccount(AccountDTO accountDTO) {
        Account account = reverseAccountConverter.convert(accountDTO);
        return repository.save(account);
    }

    @Override
    public Account update(AccountDTO accountDTO) {

        Account account = findById(accountDTO.getId()).get();
        account.setFirstName(accountDTO.getFirstName());
        account.setLastName(accountDTO.getLastName());
        account.setPhoneNumber(accountDTO.getPhoneNumber());
        account.setAbout(accountDTO.getAbout());
            return repository.save(account);
        }
    }
