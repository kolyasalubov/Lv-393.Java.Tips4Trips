package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Comment;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private static final Logger logger = Logger.getLogger(CommentService.class);

    CommentRepository repository;
    private AccountRepository aRepository;
@Autowired
    public CommentService(CommentRepository repository, AccountRepository aRepository) {
        this.repository = repository;
        this.aRepository = aRepository;
    }




    public long countByPostId(Long id) {
        return repository.countByPostId(id);
    }

    public List<Comment> findByPostId(Long id) {
        return repository.findByPostId(id);
    }


    public Comment createComment(Comment comment) {
        Account acc= aRepository.findById(comment.getPost().getAuthor().getId()).get();
        acc.setNewNotification(true);
        aRepository.save(acc);
        return repository.save(comment);
    }

    public void deleteComment(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

    public boolean existsByCommentedByIdAndId(Long accountId, Long commentId) {
        return repository.existsByCommentedByIdAndId(accountId,commentId);
    }

}
