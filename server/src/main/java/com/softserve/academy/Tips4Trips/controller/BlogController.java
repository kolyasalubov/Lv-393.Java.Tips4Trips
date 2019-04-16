package com.softserve.academy.Tips4Trips.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softserve.academy.Tips4Trips.dto.LittlePostDTO;
import com.softserve.academy.Tips4Trips.dto.converter.PostConverter;
import com.softserve.academy.Tips4Trips.dto.details.PostDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.PostInfoDTO;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.service.PostService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@CrossOrigin
@RequestMapping("/blog")
public class BlogController {

    private static final Logger logger = Logger.getLogger(PostController.class);

    private PostService postService;
    private PostConverter postConverter;

    @Autowired
    public BlogController(PostService postService,
                          PostConverter postConverter) {
        this.postService = postService;
        this.postConverter = postConverter;
    }

    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public Page<LittlePostDTO> listArticlesPageByPage(@PathVariable("page") int page) {

        PageRequest pageable = PageRequest.of(page - 1, 6);

        Page<Post> articlePage = postService.getPaginatedArticles(pageable);
        Page<LittlePostDTO> postDetailsDTOS =  articlePage.map(program -> new LittlePostDTO(program));

        return postDetailsDTOS;

    }
}
