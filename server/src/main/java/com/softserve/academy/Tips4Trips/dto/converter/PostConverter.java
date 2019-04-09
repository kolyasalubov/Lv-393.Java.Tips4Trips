package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.PostDTO;
import com.softserve.academy.Tips4Trips.entity.Post;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.PostRepository;
import com.softserve.academy.Tips4Trips.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostConverter implements Converter<Post, PostDTO> {

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
    }

    @Override
    public Post convertToEntity(PostDTO postDTO) {
        long id = (postDTO.getId() != null) ? postDTO.getId() : 0;
        Post post = postRepository.findById(id)
                .orElseGet(Post::new);
        post.setAuthor(accountRepository.findById(postDTO.getAuthorId()).get());
        post.setName(postDTO.getName());
        post.setCreationDate(postDTO.getCreationDate());
        post.setPhotoPath(postDTO.getPhotoPath());
        post.setContent(postDTO.getContent());
        if (postDTO.getRouteId() != null) {
            post.setRoute(routeRepository.findById(postDTO.getId()).get());
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
