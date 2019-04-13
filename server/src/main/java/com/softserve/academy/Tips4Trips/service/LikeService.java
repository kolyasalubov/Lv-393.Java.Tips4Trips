package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Like;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LikeService {

    private LikeRepository repository;

    @Autowired
    public LikeService(LikeRepository repository) {
        this.repository = repository;
    }

    public long countByPostId(Long id ) {
        return repository.countByPostId(id);
    }

    public Like findByAccountAndPost(Account account, Post post) {
        Optional<Like> likes = repository.findByLikedByAndPost(account, post);
        if (likes.isPresent()) {
            return likes.get();
        } else {
            throw new NoSuchElementException("Like is not found!");
        }
    }

    public Like createLike(Like like) {
        return repository.save(like);
    }

    public void deleteLike(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

}
