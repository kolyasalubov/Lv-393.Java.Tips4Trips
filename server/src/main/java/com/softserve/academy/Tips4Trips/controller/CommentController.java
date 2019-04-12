package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.CommentDTO;
import com.softserve.academy.Tips4Trips.dto.converter.CommentConverter;
import com.softserve.academy.Tips4Trips.dto.converter.PostConverter;
import com.softserve.academy.Tips4Trips.entity.blog.Comment;
import com.softserve.academy.Tips4Trips.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /*    public long countByPost(@RequestBody PostDTO postDTO) {
            long count = commentService.countByPost(postConverter.convertToEntity(postDTO));
            return count;
        }*/
    //???
    @GetMapping("/allbypost/{id}")
    public ResponseEntity<List<CommentDTO>> findByPost(@PathVariable Long id) {
        return new ResponseEntity<>(commentConverter.convertToDTO(commentService.findByPostId(id)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = commentService.createComment(commentConverter.convertToEntity(commentDTO));
        return new ResponseEntity<>(commentConverter.convertToDTO(comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}

