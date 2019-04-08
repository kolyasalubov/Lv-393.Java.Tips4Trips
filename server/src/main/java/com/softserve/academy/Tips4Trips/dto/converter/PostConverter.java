package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.PostDTO;
import com.softserve.academy.Tips4Trips.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostConverter implements Converter<Post, PostDTO> {

    @Override
    public PostDTO apply(Post post) {
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
