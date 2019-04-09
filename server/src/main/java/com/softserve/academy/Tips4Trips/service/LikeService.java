package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.LikeDTO;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.Like;
import com.softserve.academy.Tips4Trips.entity.Post;
import com.softserve.academy.Tips4Trips.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {
    private LikeRepository repository;

    public long countByPost(Post post) {
        return repository.countByPost(post);
    }

    public Like findByAccountAndPost(Account account, Post post) {
        return repository.findByAccountAndPost(account, post).get();
    }

    Like createLike(Like like) {

        return repository.save(like);
    }

    public void deleteLike(Like like) {
        repository.delete(like);
    }

}
