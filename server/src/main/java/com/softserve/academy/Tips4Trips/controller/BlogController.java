package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.info.PostInfoDTO;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.service.PostService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/blog")
public class BlogController {

    private static final Logger logger = Logger.getLogger(PostController.class);

    private final PostService postService;
    private ModelMapper modelMapper;

    @Autowired
    public BlogController(PostService postService,
                          ModelMapper modelMapper) {
        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public Page<PostInfoDTO> listArticlesPageByPage(@PathVariable("page") int page) {
        logger.info("get list of post method executing: " + page);
        if (page < 1) {
            return null;
        }
        Page<Post> articlePage = postService.getPaginatedArticles(PageRequest.of(page - 1, 6));
        return articlePage.map(post -> modelMapper.map(post, PostInfoDTO.class));
    }
}
