package com.softserve.academy.Tips4Trips.service.impl;

import com.softserve.academy.Tips4Trips.entity.User;
import com.softserve.academy.Tips4Trips.repository.UserRepository;
import com.softserve.academy.Tips4Trips.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<User, Long, UserRepository>
        implements UserService {

    UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

}
