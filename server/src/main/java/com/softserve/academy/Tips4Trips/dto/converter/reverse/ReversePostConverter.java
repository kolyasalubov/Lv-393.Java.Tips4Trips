package com.softserve.academy.Tips4Trips.dto.converter.reverse;

import com.softserve.academy.Tips4Trips.dto.PostDTO;
import com.softserve.academy.Tips4Trips.dto.converter.Converter;
import com.softserve.academy.Tips4Trips.entity.Post;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReversePostConverter implements Converter<PostDTO, Post> {

    AccountService accountService;
    RouteService routeService;

    @Autowired
    public ReversePostConverter(AccountService accountService, RouteService routeService) {
        this.accountService = accountService;
        this.routeService = routeService;
    }

    @Override
    public Post apply(PostDTO postDTO) {
        Post post = new Post();
        post.setAuthor(accountService.findById(postDTO.getAuthorId()).get());
        post.setName(postDTO.getName());
        post.setCreationDate(postDTO.getCreationDate());
        post.setPhotoPath(postDTO.getPhotoPath());
        post.setContent(postDTO.getContent());
        if (postDTO.getRouteId() != null) {
            post.setRoute(routeService.findById(postDTO.getId()).get());
        }
        return post;
    }
}
