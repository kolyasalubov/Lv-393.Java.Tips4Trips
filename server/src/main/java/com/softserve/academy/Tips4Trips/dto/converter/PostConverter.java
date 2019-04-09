package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.PostDTO;
import com.softserve.academy.Tips4Trips.entity.Post;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostConverter implements Converter<Post, PostDTO> {

    AccountService accountService;
    RouteService routeService;

    @Autowired
    public PostConverter(AccountService accountService, RouteService routeService) {
        this.accountService = accountService;
        this.routeService = routeService;
    }

    @Override
    public Post convertToEntity(PostDTO postDTO) {
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

    @Override
    public PostDTO convertToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setName(post.getName());
        postDTO.setAuthorId(post.getAuthor().getId());
        postDTO.setCreationDate(post.getCreationDate());
        postDTO.setContent(post.getContent());
        postDTO.setPhotoPath(post.getPhotoPath());
        if (post.getRoute() != null) {
            postDTO.setRouteId(post.getRoute().getId());
        }
        return postDTO;
    }
}
