package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Like;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private LikeRepository repository;

    @Autowired
    public LikeService(LikeRepository repository) {
        this.repository = repository;
    }

    public long countByPostId(Long id) {
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

    public List<Account> findAccounts(Post post) {
        List<Like> likes = repository.findByPost(post);
        return likes.stream().map(Like::getLikedBy).collect(Collectors.toList());
    }

    public Like createLike(Account account, Post post) {
        Like like = new Like();
        like.setLikedBy(account);
        like.setPost(post);
        return repository.save(like);
    }

    public void deleteLike(Post post, Account account) {
        repository.findByLikedByAndPost(account, post)
                .ifPresent(repository::delete);
    }

}
