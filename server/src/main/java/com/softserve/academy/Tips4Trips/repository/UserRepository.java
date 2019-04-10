package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.administration.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    List<User> findByAccount(Account account);

    boolean existsByLogin(String login);

    boolean existsByAccountEmail(String email);

}
