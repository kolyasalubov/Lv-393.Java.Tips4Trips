package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.details.CommentDetailsDTO;
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



    @GetMapping("/all/{id}")
    public ResponseEntity<List<CommentDetailsDTO>> findByPostId(@PathVariable Long id) {
        return new ResponseEntity<>(commentConverter.convertToDTO(commentService.findByPostId(id)), HttpStatus.OK);
    }
/*    public long countByPost(@RequestBody PostDetailsDTO postDetailsDTO) {
        long count = commentService.countByPost(postConverter.convertToEntity(postDetailsDTO));
        return count;
    }*/


        @PostMapping("/create")
        public ResponseEntity<CommentDetailsDTO> createComment (@RequestBody CommentDetailsDTO commentDetailsDTO){
            Comment comment = commentService.createComment(commentConverter.convertToEntity(commentDetailsDTO));
            return new ResponseEntity<>(commentConverter.convertToDTO(comment), HttpStatus.CREATED);
        }

        @DeleteMapping("/delete/{id}")
        public void deleteComment (@PathVariable Long id){
            commentService.deleteComment(id);

    }
}

