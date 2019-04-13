package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.CommentDTO;
import com.softserve.academy.Tips4Trips.dto.details.PostDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.converter.CommentConverter;
import com.softserve.academy.Tips4Trips.dto.converter.PostConverter;
import com.softserve.academy.Tips4Trips.entity.blog.Comment;
import com.softserve.academy.Tips4Trips.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    CommentConverter commentConverter;
    CommentService commentService;
    PostConverter postConverter;

    @Autowired
    public CommentController(CommentConverter commentConverter, CommentService commentService) {
        this.commentConverter = commentConverter;
        this.commentService = commentService;
    }

/*    public long countByPost(@RequestBody PostDTO postDetailsDTO) {
        long count = commentService.countByPost(postConverter.convertToEntity(postDetailsDTO));
        return count;
    }*/
    //???
    @GetMapping("/find")
    public ResponseEntity<CommentDTO> findByPost(@RequestBody PostDetailsDTO postDetailsDTO) {
        Comment comment = commentService.findByPost(postConverter.convertToEntity(postDetailsDTO));
        return new ResponseEntity<>(commentConverter.convertToDTO(comment), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/create")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = commentService.createComment(commentConverter.convertToEntity(commentDTO));
        return new ResponseEntity<>(commentConverter.convertToDTO(comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable Comment comment) {
        commentService.deleteComment(comment);
    }
}

