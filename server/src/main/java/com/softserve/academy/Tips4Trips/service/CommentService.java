package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.blog.Comment;
import com.softserve.academy.Tips4Trips.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private static final Logger logger = Logger.getLogger(CommentService.class);

    CommentRepository repository;

    @Autowired
    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    public long countByPostId(Long id) {
        return repository.countByPostId(id);
    }

    public List<Comment> findByPostId(Long id) {
        return repository.findByPostId(id);
    }


    public Comment createComment(Comment comment) {
        return repository.save(comment);
    }

    public void deleteComment(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

}
