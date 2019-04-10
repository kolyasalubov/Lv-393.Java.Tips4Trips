package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.blog.Comment;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    CommentRepository repository;

    public long countByPost(Post post) {
        return repository.countByPost(post);
    }

    public Comment findByPost(Post post) {
        return repository.findByPost(post).get();
    }

    public Comment createComment(Comment comment) {
        return repository.save(comment);
    }

    public void deleteComment(Comment comment) {
        repository.delete(comment);
    }


}
