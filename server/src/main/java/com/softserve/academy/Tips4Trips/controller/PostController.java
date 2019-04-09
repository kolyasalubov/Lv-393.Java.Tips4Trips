package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.PostConverter;
import com.softserve.academy.Tips4Trips.dto.PostDTO;
import com.softserve.academy.Tips4Trips.entity.Post;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.PostService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {

    PostService postService;
    PostConverter postConverter;
    AccountService accountService;

    @Autowired
    public PostController(PostService postService, PostConverter postConverter,
                          AccountService accountService) {
        this.postService = postService;
        this.postConverter = postConverter;
        this.accountService = accountService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAll() {
        return new ResponseEntity<>(postConverter
                .convertToDTO(postService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getById(@PathVariable Long id) {
        Optional<Post> post = postService.findById(id);
        return post.map(p -> new ResponseEntity<>(postConverter
                .convertToDTO(p), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<PostDTO>> getByAuthor(@PathVariable Long authorId) {
        return new ResponseEntity<>(postConverter
                .convertToDTO(postService.findByAuthor(accountService.findById(authorId).get())), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PostDTO> update(@RequestBody PostDTO postDTO) {
        try {
            Post post = postService.update(postDTO);
            return new ResponseEntity<>(postConverter
                    .convertToDTO(post), HttpStatus.ACCEPTED);
        } catch (HibernateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        try {
            Post post = postService.createPost(postDTO);
            return new ResponseEntity<>(postConverter
                    .convertToDTO(post), HttpStatus.CREATED);
        } catch (HibernateException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        postService.findById(id).ifPresent(postService::delete);
    }

}
