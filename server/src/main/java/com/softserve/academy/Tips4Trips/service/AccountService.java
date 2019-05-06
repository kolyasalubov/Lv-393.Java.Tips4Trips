package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.constants.ExceptionMessages;
import com.softserve.academy.Tips4Trips.dto.converter.AccountConverter;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.administration.User;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.Trip;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;
import com.softserve.academy.Tips4Trips.exception.DuplicateValueException;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountService {

    private static final Logger logger = Logger.getLogger(AccountService.class);

    private AccountConverter accountConverter;
    private AccountRepository repository;
    private UserRepository userRepository;

    @Autowired
    public AccountService(AccountConverter accountConverter, AccountRepository repository, UserRepository userRepository) {
        this.accountConverter = accountConverter;
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Long getCount() {
        return  (repository.count()+100L);
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
            throw new DataNotFoundException(ExceptionMessages.ACCOUNT_BY_THIS_ID_IS_NOT_FOUND);
        }
    }

    boolean emailExists(String email) {
        return repository.existsByEmail(email);
    }

    public Account findByEmail(String email) {

        Optional<Account> account = repository.findByEmail(email);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new NoSuchElementException();
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

    public Page<Account> getPaginatedArticles(Pageable pageable) {
        return repository.findAllByOrderByIdDesc(pageable);
    }

    public void deleteById(Long id) {
    }

    @Transactional
    public Account subscribe(Long accountId, Long profileId) {
        Optional<Account> account = repository.findById(accountId);

        if (account.isPresent()) {
            Optional<Account> profile = repository.findById(profileId);
            if (!account.get().getSubscribers().contains(profile.get())) {
                account.get().getSubscribers().add(profile.get());
            } else {
                throw new DuplicateValueException(ExceptionMessages.ACCOUNT_CAN_NOT_BE_SUBSCRIBED_TWICE);
            }
        }
        return account.get();
    }

    @Transactional
    public void unSubscribe(Long accountId, Long profileId) {
        Optional<Account> account = repository.findById(accountId);
        if (account.isPresent()) {
            Optional<Account> profile = repository.findById(profileId);
            account.get().getSubscribers().remove(profile.get());
        } else {
            throw new DataNotFoundException();
        }
    }
}
