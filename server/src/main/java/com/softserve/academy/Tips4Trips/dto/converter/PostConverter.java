package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.controller.PostController;
import com.softserve.academy.Tips4Trips.dto.details.PostDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.PostInfoDTO;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.PostRepository;
import com.softserve.academy.Tips4Trips.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class PostConverter implements Converter<Post, PostDetailsDTO> {

    private AccountRepository accountRepository;
    private RouteRepository routeRepository;
    private PostRepository postRepository;
    private RouteConverter routeConverter;
    private AccountConverter accountConverter;
    private final int MAX_DESCRIPTION_LENGTH = 100;

    @Autowired
    public PostConverter(AccountRepository accountRepository,
                         RouteRepository routeRepository,
                         PostRepository postRepository,
                         RouteConverter routeConverter,
                         AccountConverter accountConverter) {
        this.accountRepository = accountRepository;
        this.routeRepository = routeRepository;
        this.postRepository = postRepository;
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
        Account author = accountRepository.findById(
                postDetailsDTO.getAuthorInfo().getId()).get();
        post.setAuthor(author);
        Route route = routeRepository.findById(
                postDetailsDTO.getRouteInfo().getId()).get();
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
        return postDetailsDTO;
    }

    public PostInfoDTO convertToInfoDTO(Post post) {
        return toInfoDTO(new PostInfoDTO(), post);
    }

    private PostInfoDTO toInfoDTO(PostInfoDTO postInfoDTO, Post post) {
        postInfoDTO.setId(post.getId());
        postInfoDTO.setName(post.getName());
        String content = post.getContent();
        String description = content.length() > MAX_DESCRIPTION_LENGTH
                ? content.substring(0, MAX_DESCRIPTION_LENGTH) : content;
        postInfoDTO.setDescription(content);
        postInfoDTO.setCreationDate(post.getCreationDate());
        postInfoDTO.setSelf(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(PostController.class)
                        .getById(post.getId())
        ).withSelfRel().getHref());
        Account author = post.getAuthor();
        postInfoDTO.setAuthorInfo(accountConverter
                .convertToInfoDTO(author));
        return postInfoDTO;
    }
}
