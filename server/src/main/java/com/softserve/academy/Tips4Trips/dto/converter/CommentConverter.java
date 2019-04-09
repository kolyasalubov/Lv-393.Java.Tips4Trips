package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.CommentDTO;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.Comment;
import com.softserve.academy.Tips4Trips.entity.Post;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter implements Converter<Comment, CommentDTO> {
    private AccountService accountService;
    private PostService postService;

    @Autowired
    public CommentConverter(AccountService accountService, PostService postService) {
        this.accountService = accountService;
        this.postService = postService;
    }

    @Override
    public CommentDTO convertToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setAccountId(comment.getCommentedBy().getId());
        commentDTO.setPostId(comment.getPost().getId());
        commentDTO.setCreationDate(comment.getCreationDate());
        commentDTO.setText(comment.getText());
        return commentDTO;
    }

    @Override
    public Comment convertToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        Account account = accountService.findById(commentDTO.getAccountId());
        comment.setCommentedBy(account);
        Post post = postService.findById(commentDTO.getPostId());
        comment.setPost(post);
        comment.setCreationDate(commentDTO.getCreationDate());
        comment.setText(commentDTO.getText());
        return comment;
    }
}
