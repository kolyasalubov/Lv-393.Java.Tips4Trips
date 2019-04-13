package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Like;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private LikeRepository repository;

    @Autowired
    public LikeService(LikeRepository repository) {
        this.repository = repository;
    }

    public long countByPost(Post post) {
        return repository.countByPost(post);
    }

    public Like findByAccountAndPost(Account account, Post post) {
        return repository.findByLikedByAndPost(account, post).get();
    }

    public Like createLike(Like like) {
        return repository.save(like);
    }

    public void deleteLike(Like like) {
        repository.delete(like);
    }

}
