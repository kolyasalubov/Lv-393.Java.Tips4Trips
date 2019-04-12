package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.details.PostDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.converter.PostConverter;
import com.softserve.academy.Tips4Trips.dto.info.PostInfoDTO;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {

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
        return new ResponseEntity<>(postConverter
                .convertToInfoDTO(postService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailsDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(postConverter
                .convertToDTO(postService.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<PostInfoDTO>> getByAuthor(@PathVariable Long authorId) {
        return new ResponseEntity<>(postConverter
                .convertToInfoDTO(postService.getByAuthorId(authorId)), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PostDetailsDTO> update(@RequestBody PostDetailsDTO postDetailsDTO) {
        Post post = postService.update(postConverter.convertToEntity(postDetailsDTO));
        return new ResponseEntity<>(postConverter
                .convertToDTO(post), HttpStatus.ACCEPTED);
    }

    @PostMapping("/create")
    public ResponseEntity<PostDetailsDTO> createPost(@RequestBody PostDetailsDTO postDetailsDTO) {
        Post post = postService.createPost(postConverter.convertToEntity(postDetailsDTO));
        return new ResponseEntity<>(postConverter
                .convertToDTO(post), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        postService.deleteById(id);
    }

}
