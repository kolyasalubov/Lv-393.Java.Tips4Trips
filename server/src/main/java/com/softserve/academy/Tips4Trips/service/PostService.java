package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.PostDTO;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.Post;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.repository.PostRepository;

import java.util.List;

public interface PostService extends Service<Post, Long, PostRepository> {

    List<Post> searchByName(String name);

    List<Post> findByAuthor(Account author);

    List<Post> findByRoute(Route route);

    Post createPost(PostDTO postDTO);
}
