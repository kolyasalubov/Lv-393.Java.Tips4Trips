package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.PostDTO;
import com.softserve.academy.Tips4Trips.dto.converter.PostConverter;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.Post;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    AccountRepository accountRepository;
    RouteService routeService;
    PostConverter postConverter;
    PostRepository repository;

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

    public List<Post> findByAuthor(Account author) {
        return repository.findByAuthor(author);
    }

    public List<Post> findByRoute(Route route) {
        return repository.findByRoute(route);
    }

    public Post createPost(PostDTO postDTO) {
        Post post = postConverter.convertFromDTO(postDTO);
        return repository.save(post);
    }

    public Post update(PostDTO postDTO) {
        Post post = repository.findById(postDTO.getId()).get();
        post.setContent(postDTO.getContent());
        post.setName(postDTO.getName());
        post.setPhotoPath(postDTO.getPhotoPath());
        if (postDTO.getRouteId() != null) {
            routeService.findById(postDTO.getRouteId()).ifPresent(post::setRoute);
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


