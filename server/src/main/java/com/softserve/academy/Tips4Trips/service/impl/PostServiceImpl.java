package com.softserve.academy.Tips4Trips.service.impl;

import com.softserve.academy.Tips4Trips.entity.Post;
import com.softserve.academy.Tips4Trips.repository.PostRepository;
import com.softserve.academy.Tips4Trips.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


    @Service
    public class PostServiceImpl extends ServiceImpl<Post, Long, PostRepository>
            implements PostService {

        PostRepository repository;

        @Autowired
        public PostServiceImpl(PostRepository repository) {
            super(repository);
        }

    }


