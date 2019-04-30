package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.PostConverter;
import com.softserve.academy.Tips4Trips.dto.details.AccountDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.details.PostDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.converter.PostConverter;
import com.softserve.academy.Tips4Trips.dto.info.PostInfoDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.entity.file.Image;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;
import com.softserve.academy.Tips4Trips.exception.FileIOException;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.PostService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
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

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailsDTO> getById(@PathVariable Long id) {
        logger.info("get post by id method executing: ");
        return new ResponseEntity<>(postConverter
                .convertToDTO(postService.findById(id)), HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<PostDetailsDTO> update(@RequestBody PostDetailsDTO postDetailsDTO) {
        logger.info("post update method executing: ");
        Post post = postService.update(postConverter.convertToEntity(postDetailsDTO));
        return new ResponseEntity<>(postConverter
                .convertToDTO(post), HttpStatus.ACCEPTED);
    }

    @PostMapping("/create")
    public ResponseEntity<PostDetailsDTO> createPost(
            @RequestBody PostDetailsDTO postDetailsDTO) {
        logger.info("create post method executing: ");
        Post post = postService.createPost(postConverter
                .convertToEntity(postDetailsDTO));
        return new ResponseEntity<>(postConverter
                .convertToDTO(post), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        logger.info("delete post by id method executing: ");
        postService.deleteById(id);
    }

    @PostMapping("/{id}/images")
    public ResponseEntity<PostDetailsDTO> addImage(@PathVariable Long id,
            @RequestParam("files") MultipartFile[] files)
            throws FileIOException {
        Post updatedPost = postService.createImagesForPost(files, id);
        return new ResponseEntity<>(postConverter
                .convertToDTO(updatedPost), HttpStatus.CREATED);
    }

    @GetMapping("/images/{imageId}")
    public RedirectView redirectToImageGet(@PathVariable Long imageId) {
        return new RedirectView("/images/" + imageId);
    }

    @DeleteMapping("/{id}/images")
    public void deleteImageById(@PathVariable Long id) throws FileIOException,
            DataNotFoundException {
        postService.deletePostImages(id);
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<PostDetailsDTO> updateImageById(
            @PathVariable Long id, @RequestParam("file") MultipartFile[] file)
            throws FileIOException, DataNotFoundException {

        Post updatedPost = postService.updatePostImages(id, file);
        return new ResponseEntity<>(postConverter
                .convertToDTO(updatedPost), HttpStatus.ACCEPTED);
    }
}
