package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.controller.CommentController;
import com.softserve.academy.Tips4Trips.controller.LikeController;
import com.softserve.academy.Tips4Trips.controller.PostController;
import com.softserve.academy.Tips4Trips.dto.details.PostDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.PostInfoDTO;
import com.softserve.academy.Tips4Trips.dto.info.RouteInfoDTO;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.RouteRepository;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.LikeService;
import com.softserve.academy.Tips4Trips.service.RouteService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostConverter implements Converter<Post, PostDetailsDTO> {

    private AccountService accountService;
    private RouteService routeService;
    private RouteConverter routeConverter;
    private AccountConverter accountConverter;
    private ImageConverter imageConverter;
    private ModelMapper modelMapper;

    @Autowired
    public PostConverter(AccountService accountService,
                         RouteService routeService,
                         RouteConverter routeConverter,
                         AccountConverter accountConverter,
                         ImageConverter imageConverter,
                         ModelMapper modelMapper) {
        this.accountService = accountService;
        this.routeService = routeService;
        this.routeConverter = routeConverter;
        this.accountConverter = accountConverter;
        this.imageConverter = imageConverter;
        this.modelMapper = modelMapper;
    }
//    @PostConstruct
//    private void post(){
//        modelMapper.createTypeMap(Post.class, PostDetailsDTO.class)
//                .setPostConverter(converter -> {
//                    PostDetailsDTO postDetailsDTO = converter.getDestination();
//                    Post source = converter.getSource();
//                    Route route = source.getRoute();
//                    modelMapper.createTypeMap(Route.class, RouteInfoDTO.class)
//                            .setPostConverter(converter1 -> {
//                                RouteInfoDTO routeInfoDTO = converter1.getDestination();
//
//                                return routeInfoDTO;
//                            });
//                    postDetailsDTO.setRouteInfo(modelMapper.map(route, RouteInfoDTO.class));
//
//                    postDetailsDTO.setLikes(ControllerLinkBuilder
//                            .linkTo(ControllerLinkBuilder
//                                    .methodOn(LikeController.class)
//                                    .getAccounts(source.getId()))
//                            .withRel("likes").getHref());
//                    postDetailsDTO.setComments(ControllerLinkBuilder
//                            .linkTo(ControllerLinkBuilder
//                                    .methodOn(CommentController.class)
//                                    .findByPostId(source.getId()))
//                            .withRel("comments").getHref());
//                    return postDetailsDTO;
//
//                });
//    }

    @Override
    public Post convertToEntity(PostDetailsDTO postDetailsDTO) {
        Post post = new Post();
        post.setId(postDetailsDTO.getId());
        post.setName(postDetailsDTO.getName());
        post.setContent(postDetailsDTO.getContent());
        post.setImages(imageConverter.convertToEntity(postDetailsDTO.getImages()));
        // post.setCreationDate(postDetailsDTO.getCreationDate());
        Route route = routeService.findById(
                postDetailsDTO.getRouteInfo().getId());
        post.setRoute(route);
        return post;
    }
    @Override
    public PostDetailsDTO convertToDTO(Post post) {
        return modelMapper.map(post, PostDetailsDTO.class);
    }

}
