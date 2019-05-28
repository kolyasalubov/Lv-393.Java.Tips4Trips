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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private ModelMapper modelMapper;

    @Autowired
    public PostController(PostService postService, PostConverter postConverter, ModelMapper modelMapper) {
        this.postService = postService;
        this.postConverter = postConverter;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        logger.info("get count of posts method executing: ");
        return new ResponseEntity<>(postService.getCount(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailsDTO> getById(@PathVariable Long id) {
        logger.info("get post by id method executing: ");
        if (id < 1) {
            return null;
        }
        return new ResponseEntity<>(postConverter
                .convertToDTO(postService.findById(id)), HttpStatus.OK);
    }


    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<PostDetailsDTO> update(@RequestBody PostDetailsDTO postDetailsDTO) {
        logger.info("post update method executing: ");
        Post post = postService.update(postConverter.convertToEntity(postDetailsDTO));
        return new ResponseEntity<>(postConverter
                .convertToDTO(post), HttpStatus.ACCEPTED);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<PostDetailsDTO> createPost(
            @RequestBody PostDetailsDTO postDetailsDTO) {
        logger.info("create post method executing: ");
        Post post = postService.createPost(postConverter
                .convertToEntity(postDetailsDTO));
        return new ResponseEntity<>(postConverter
                .convertToDTO(post), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteById(@PathVariable Long id) {
        logger.info("delete post by id method executing: ");
        if (id < 1) {
            return;
        }
        postService.deleteById(id);
    }


    @PostMapping("/{id}/images")
    public ResponseEntity<PostDetailsDTO> addImage(@PathVariable Long id,
                                                   @RequestParam("files") MultipartFile[] files)
            throws FileIOException {
        logger.info("create photo by post method executing: ");
        if (id < 1) {
            return null;
        }
        return new ResponseEntity<>(postConverter.convertToDTO(
                postService.createImagesForPost(files, id)),
                HttpStatus.CREATED);
    }

    @GetMapping("/images/{imageId}")
    public RedirectView redirectToImageGet(@PathVariable Long imageId) {
        logger.info("get photo by id method executing: ");
        if (imageId < 1) {
            return null;
        }
        return new RedirectView("/images/" + imageId);
    }

    @DeleteMapping("/{id}/images")
    public void deleteImageById(@PathVariable Long id)
            throws FileIOException, DataNotFoundException {
        logger.info("delete photo by id method executing: ");
        if (id < 1) {
            return;
        }
        postService.deletePostImages(id);
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<PostDetailsDTO> updateImageById(
            @PathVariable Long id, @RequestParam("file") MultipartFile[] file)
            throws FileIOException, DataNotFoundException {
        logger.info("update photo by id method executing: ");
        if (id < 1) {
            return null;
        }
        return new ResponseEntity<>(postConverter.convertToDTO(
                postService.updatePostImages(id, file)), HttpStatus.ACCEPTED);
    }
}
