package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.PostDTO;
import com.softserve.academy.Tips4Trips.entity.Post;
<<<<<<< HEAD
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.PostRepository;
import com.softserve.academy.Tips4Trips.repository.RouteRepository;
=======
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.RouteService;
>>>>>>> 34dacc720e840960dbb65139bd53b58671477958
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostConverter implements Converter<Post, PostDTO> {

<<<<<<< HEAD
    AccountRepository accountRepository;
    RouteRepository routeRepository;
    PostRepository postRepository;

    @Autowired
    public PostConverter(AccountRepository accountRepository,
                         RouteRepository routeRepository,
                         PostRepository postRepository) {
        this.accountRepository = accountRepository;
        this.routeRepository = routeRepository;
        this.postRepository = postRepository;
=======
    AccountService accountService;
    RouteService routeService;

    @Autowired
    public PostConverter(AccountService accountService, RouteService routeService) {
        this.accountService = accountService;
        this.routeService = routeService;
>>>>>>> 34dacc720e840960dbb65139bd53b58671477958
    }

    @Override
    public Post convertToEntity(PostDTO postDTO) {
<<<<<<< HEAD
        long id = (postDTO.getId() != null) ? postDTO.getId() : 0;
        Post post = postRepository.findById(id)
                .orElseGet(Post::new);
        post.setAuthor(accountRepository.findById(postDTO.getAuthorId()).get());
=======
        Post post = new Post();
        post.setAuthor(accountService.findById(postDTO.getAuthorId()).get());
>>>>>>> 34dacc720e840960dbb65139bd53b58671477958
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
