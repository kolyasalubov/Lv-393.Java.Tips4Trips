package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PostService {

    private AccountRepository accountRepository;
    private PostRepository repository;

    @Autowired
    public PostService(AccountRepository accountRepository,
                       PostRepository repository) {
        this.accountRepository = accountRepository;
        this.repository = repository;
    }

    public List<Post> getByAuthorId(Long authorId) {
        Optional<Account> account = accountRepository.findById(authorId);
        if (account.isPresent()) {
            return repository.findByAuthor(account.get());
        } else {
            throw new NoSuchElementException("Author not found!");
        }
    }

    public List<Post> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Post createPost(Post post) {
        post.setId(-1L);
        return repository.save(post);
    }

    public Post update(Post post) {
        Optional<Post> existingPost = repository.findById(post.getId());
        if (!existingPost.isPresent()) {
            throw new NoSuchElementException();
        } else {
            existingPost.get().setRoute(post.getRoute());
            existingPost.get().setPhotoPath(post.getPhotoPath());
            existingPost.get().setAuthor(post.getAuthor());
            existingPost.get().setContent(post.getContent());
            existingPost.get().setName(post.getName());
        }
        return repository.save(existingPost.get());
    }

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Post findById(Long id) {
        Optional<Post> post = repository.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    public void deleteById(Long id) {
        // delete comments, likes
        repository.findById(id).ifPresent(repository::delete);
    }
}


