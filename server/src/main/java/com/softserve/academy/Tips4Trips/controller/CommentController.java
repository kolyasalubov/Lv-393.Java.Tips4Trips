package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.CommentConverter;
import com.softserve.academy.Tips4Trips.dto.details.CommentDetailsDTO;
import com.softserve.academy.Tips4Trips.entity.blog.Comment;
import com.softserve.academy.Tips4Trips.service.CommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {

    private static final Logger logger = Logger.getLogger(CommentController.class);

    CommentConverter commentConverter;
    CommentService commentService;


    @Autowired
    public CommentController(CommentConverter commentConverter, CommentService commentService) {
        this.commentConverter = commentConverter;
        this.commentService = commentService;
    }


    @GetMapping("/all/{id}")
    public ResponseEntity<List<CommentDetailsDTO>> findByPostId(@PathVariable Long id) {
        logger.info("findByPostId method executing: ");
        return new ResponseEntity<>(commentConverter.convertToDTO(commentService.findByPostId(id)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CommentDetailsDTO> createComment(@RequestBody CommentDetailsDTO commentDetailsDTO) {
        logger.info("createComment method executing: ");
        Comment comment = commentService.createComment(commentConverter.convertToEntity(commentDetailsDTO));
        return new ResponseEntity<>(commentConverter.convertToDTO(comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{accountId}/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long accountId, @PathVariable Long commentId) {
        logger.info("deleteComment method executing: ");
        if (commentService.existsByCommentedByIdAndId(accountId, commentId)) {
            commentService.deleteComment(commentId);
        } else {
            throw new NoSuchElementException();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

