package com.softserve.academy.Tips4Trips.service.impl;

import com.softserve.academy.Tips4Trips.dto.PostDTO;
import com.softserve.academy.Tips4Trips.dto.converter.reverse.ReversePostConverter;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.Post;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.repository.PostRepository;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.PostService;
import com.softserve.academy.Tips4Trips.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl extends ServiceImpl<Post, Long, PostRepository>
        implements PostService {

    AccountService accountService;
    RouteService routeService;
    ReversePostConverter reversePostConverter;

    @Autowired
    public PostServiceImpl(PostRepository repository, AccountService accountService,
                           RouteService routeService, ReversePostConverter reversePostConverter) {
        super(repository);
        this.accountService = accountService;
        this.routeService = routeService;
        this.reversePostConverter = reversePostConverter;
    }

    @Override
    public List<Post> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Post> findByAuthor(Account author) {
        return repository.findByAuthor(author);
    }

    @Override
    public List<Post> findByRoute(Route route) {
        return repository.findByRoute(route);
    }

    @Override
    public Post createPost(PostDTO postDTO) {
        Post post = reversePostConverter.convert(postDTO);
        return repository.save(post);
    }

    @Override
    public Post update(PostDTO postDTO) {
        Post post = findById(postDTO.getId()).get();
        post.setContent(postDTO.getContent());
        post.setName(postDTO.getName());
        post.setPhotoPath(postDTO.getPhotoPath());
        if (postDTO.getRouteId() != null) {
            routeService.findById(postDTO.getRouteId()).ifPresent(post::setRoute);
        }
        return repository.save(post);
    }
}


