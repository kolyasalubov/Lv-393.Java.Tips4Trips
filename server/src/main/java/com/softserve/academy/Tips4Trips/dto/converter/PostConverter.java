package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.controller.LikeController;
import com.softserve.academy.Tips4Trips.controller.PostController;
import com.softserve.academy.Tips4Trips.dto.details.PostDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.PostInfoDTO;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.RouteRepository;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.LikeService;
import com.softserve.academy.Tips4Trips.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostConverter implements Converter<Post, PostDetailsDTO> {

    private AccountService accountService;
    private RouteService routeService;
    private RouteConverter routeConverter;
    private AccountConverter accountConverter;
    private final int MAX_DESCRIPTION_LENGTH = 100;

    @Autowired
    public PostConverter(AccountService accountService,
                         RouteService routeService,
                         RouteConverter routeConverter,
                         AccountConverter accountConverter) {
        this.accountService = accountService;
        this.routeService = routeService;
        this.routeConverter = routeConverter;
        this.accountConverter = accountConverter;
    }

    @Override
    public Post convertToEntity(PostDetailsDTO postDetailsDTO) {
        Post post = new Post();
        post.setId(postDetailsDTO.getId());
        post.setName(postDetailsDTO.getName());
        post.setContent(postDetailsDTO.getContent());
        post.setPhotoPath(postDetailsDTO.getPhotoPath());
        post.setCreationDate(postDetailsDTO.getCreationDate());
        Account author = accountService.findById(
                postDetailsDTO.getAuthorInfo().getId());
        post.setAuthor(author);
        Route route = routeService.findById(
                postDetailsDTO.getRouteInfo().getId());
        post.setRoute(route);
        return post;
    }

    @Override
    public PostDetailsDTO convertToDTO(Post post) {
        PostDetailsDTO postDetailsDTO = (PostDetailsDTO)
                toInfoDTO(new PostDetailsDTO(), post);
        postDetailsDTO.setContent(post.getContent());
        postDetailsDTO.setPhotoPath(post.getPhotoPath());
        Route route = post.getRoute();
        postDetailsDTO.setRouteInfo(routeConverter
                .convertToInfoDTO(route));
        postDetailsDTO.setLikes(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder
                        .methodOn(LikeController.class)
                        .getAccounts(post.getId()))
                .withRel("likes").getHref());
        postDetailsDTO.setComments("Here will be link to comments");
        return postDetailsDTO;
    }

    public PostInfoDTO convertToInfoDTO(Post post) {
        return toInfoDTO(new PostInfoDTO(), post);
    }

    public List<PostInfoDTO> convertToInfoDTO(final List<Post> posts) {
        List<PostInfoDTO> dtos = new ArrayList<>();
        if (posts != null) {
            dtos = posts.stream().map(this::convertToInfoDTO).collect(Collectors.toList());
        }
        return dtos;
    }

    private PostInfoDTO toInfoDTO(PostInfoDTO postInfoDTO, Post post) {
        postInfoDTO.setId(post.getId());
        postInfoDTO.setCountOfLikes(post.getLikes().size());
        postInfoDTO.setName(post.getName());
        String content = post.getContent();
        String description = content.length() > MAX_DESCRIPTION_LENGTH
                ? content.substring(0, MAX_DESCRIPTION_LENGTH) : content;
        postInfoDTO.setDescription(description + "...");
        postInfoDTO.setCreationDate(post.getCreationDate());
        postInfoDTO.setSelf(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder
                        .methodOn(PostController.class)
                        .getById(post.getId()))
                .withSelfRel().getHref());
        Account author = post.getAuthor();
        postInfoDTO.setAuthorInfo(accountConverter
                .convertToInfoDTO(author));
        return postInfoDTO;
    }
}
