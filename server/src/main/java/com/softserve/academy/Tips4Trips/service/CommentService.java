package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.blog.Comment;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    CommentRepository repository;

    @Autowired
    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    public long countByPost(Post post) {
        return repository.countByPost(post);
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
