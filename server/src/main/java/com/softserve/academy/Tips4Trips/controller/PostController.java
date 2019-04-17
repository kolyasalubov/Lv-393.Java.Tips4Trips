package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.PostConverter;
import com.softserve.academy.Tips4Trips.dto.details.PostDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.converter.PostConverter;
import com.softserve.academy.Tips4Trips.dto.info.PostInfoDTO;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.PostService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {

    private static final Logger logger = Logger.getLogger(PostController.class);

    private PostService postService;
    private PostConverter postConverter;

    @Autowired
    public PostController(PostService postService,
                          PostConverter postConverter) {
        this.postService = postService;
        this.postConverter = postConverter;
    }

    @GetMapping
    public ResponseEntity<List<PostInfoDTO>> getAll() {
        logger.info("get all post info method executing: ");
        return new ResponseEntity<>(postConverter
                .convertToInfoDTO(postService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailsDTO> getById(@PathVariable Long id) {
        logger.info("get post by id method executing: ");
        return new ResponseEntity<>(postConverter
                .convertToDTO(postService.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<PostInfoDTO>> getByAuthor(@PathVariable Long authorId) {
        logger.info("get by author method executing: ");
        return new ResponseEntity<>(postConverter
                .convertToInfoDTO(postService.getByAuthorId(authorId)), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PostDetailsDTO> update(@RequestBody PostDetailsDTO postDetailsDTO) {
        logger.info("post update method executing: ");
        Post post = postService.update(postConverter.convertToEntity(postDetailsDTO));
        return new ResponseEntity<>(postConverter
                .convertToDTO(post), HttpStatus.ACCEPTED);
    }

    @PostMapping("/create")
    public ResponseEntity<PostDetailsDTO> createPost(@RequestBody PostDetailsDTO postDetailsDTO) {
        logger.info("create post method executing: ");
        Post post = postService.createPost(postConverter.convertToEntity(postDetailsDTO));
        return new ResponseEntity<>(postConverter
                .convertToDTO(post), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        logger.info("delete post by id method executing: ");
        postService.deleteById(id);
    }

}
