package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.controller.CommentController;
import com.softserve.academy.Tips4Trips.dto.details.CommentDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.CommentInfoDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Comment;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentConverter implements Converter<Comment, CommentDetailsDTO> {

    private PostService postService;
    private AccountService accountService;
    private AccountConverter accountConverter;
    private final int MAX_TEXT_LENGTH = 100;

    @Autowired
    public CommentConverter(PostService postService, AccountService accountService) {
        this.postService = postService;
        this.accountService = accountService;
    }

    @Override
    public CommentDetailsDTO convertToDTO(Comment comment) {
        CommentDetailsDTO commentDetailsDTO = new CommentDetailsDTO();
        commentDetailsDTO.setId(comment.getId());
        Account account = comment.getCommentedBy();
        commentDetailsDTO.setAccountInfo(accountConverter.convertToInfoDTO(account));
        commentDetailsDTO.setCreationDate(comment.getCreationDate());
        commentDetailsDTO.setText(comment.getText());
        return commentDetailsDTO;
    }

    @Override
    public Comment convertToEntity(CommentDetailsDTO commentDetailsDTO) {
        Comment comment = new Comment();
        comment.setId(commentDetailsDTO.getId());

        Account account = accountService.findById(commentDetailsDTO.getId());
        comment.setCommentedBy(account);
        Post post = postService.findById(commentDetailsDTO.getId());
        comment.setPost(post);
        comment.setCreationDate(commentDetailsDTO.getCreationDate());
        comment.setText(commentDetailsDTO.getText());
        return comment;
    }

    public CommentInfoDTO convertToInfoDTO(Comment comment) {
        return toInfoDTO(new CommentInfoDTO(), comment);
    }

    public List<CommentInfoDTO> convertToInfoDTO(final List<Comment> comments) {
        List<CommentInfoDTO> dtos = new ArrayList<>();
        if (comments != null) {
            dtos = comments.stream().map(this::convertToInfoDTO).collect(Collectors.toList());
        }
        return dtos;
    }

    private CommentInfoDTO toInfoDTO(CommentInfoDTO commentInfoDTO, Comment comment) {
        commentInfoDTO.setId(comment.getId());
        Account account = comment.getCommentedBy();
        commentInfoDTO.setAccountInfo(accountConverter.convertToInfoDTO(account));
        commentInfoDTO.setCreationDate(comment.getCreationDate());
        String text = comment.getText();
        String shortText = text.length() > MAX_TEXT_LENGTH
                ? text.substring(0, MAX_TEXT_LENGTH) : text;
        commentInfoDTO.setShortText(shortText + "...");
        commentInfoDTO.setSelf(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder
                        .methodOn(CommentController.class)
                                .findByPostId(comment.getPost().getId())
                        )
                .withSelfRel().getHref());
        return commentInfoDTO;
    }
}
