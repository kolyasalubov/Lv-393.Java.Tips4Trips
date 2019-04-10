package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.converter.PostConverter;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private AccountRepository accountRepository;
    private RouteService routeService;
    private PostConverter postConverter;
    private PostRepository repository;

    @Autowired
    public PostService(AccountRepository accountRepository, RouteService routeService,
                       PostConverter postConverter, PostRepository repository) {
        this.accountRepository = accountRepository;
        this.routeService = routeService;
        this.postConverter = postConverter;
        this.repository = repository;
    }

    public List<Post> getByAuthorId(Long authorId) {
        Account account = accountRepository.findById(authorId).get();
        return repository.findByAuthor(account);
    }

    public List<Post> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Post createPost(Post post) {
        post.setId(0L);
        return repository.save(post);
    }

    public Post update(Post post) {
        if (post.getId() == null) {
            throw new IllegalArgumentException();
        }
        return repository.save(post);
    }

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Post findById(Long id) {
        return repository.findById(id).get();
    }

    public void deleteById(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}


