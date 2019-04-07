package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.PostConverter;
import com.softserve.academy.Tips4Trips.dto.PostDTO;
import com.softserve.academy.Tips4Trips.entity.Post;
import com.softserve.academy.Tips4Trips.service.PostService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {

    PostService postService;
    PostConverter postConverter;

    @Autowired
    public PostController(PostService postService, PostConverter postConverter) {
        this.postService = postService;
        this.postConverter = postConverter;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAll() {
        return new ResponseEntity<>(postConverter
                .convert(postService.findAll()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        try {
            Post post = postService.createPost(postDTO);
            return new ResponseEntity<>(postConverter
                    .convert(post), HttpStatus.CREATED);
        } catch (HibernateException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
