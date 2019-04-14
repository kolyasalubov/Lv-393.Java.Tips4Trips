package com.softserve.academy.Tips4Trips.controller;


import com.softserve.academy.Tips4Trips.dto.AccountDTO;
import com.softserve.academy.Tips4Trips.dto.LikeDTO;

import com.softserve.academy.Tips4Trips.dto.details.AccountDetailsDTO;

import com.softserve.academy.Tips4Trips.dto.details.PostDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.converter.AccountConverter;
import com.softserve.academy.Tips4Trips.dto.converter.LikeConverter;
import com.softserve.academy.Tips4Trips.dto.converter.PostConverter;
import com.softserve.academy.Tips4Trips.dto.info.AccountInfoDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Like;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.LikeService;
import org.apache.log4j.Logger;
import com.softserve.academy.Tips4Trips.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("posts/{postId}/likes")
public class LikeController {

    private static final Logger logger = Logger.getLogger(LikeController.class);

    private LikeConverter likeConverter;
    private LikeService likeService;
    private PostService postService;
    private PostConverter postConverter;
    private AccountConverter accountConverter;
    private AccountService accountService;

    @Autowired
    public LikeController(LikeConverter likeConverter,
                          LikeService likeService,
                          PostConverter postConverter,
                          AccountConverter accountConverter,
                          PostService postService,
                          AccountService accountService) {
        this.likeConverter = likeConverter;
        this.likeService = likeService;
        this.accountConverter = accountConverter;
        this.postConverter = postConverter;
        this.postService = postService;
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountInfoDTO>> getAccounts(@PathVariable Long postId) {
        Post post = postService.findById(postId);
        return new ResponseEntity<>(accountConverter.convertToInfoDTO(likeService
                .findAccounts(post)), HttpStatus.OK);
    }

    @PostMapping("/create/{accountId}")
    public ResponseEntity<LikeDTO> createLike(@PathVariable Long postId,
                                              @PathVariable Long accountId) {
        Account account = accountService.findById(accountId);
        Post post = postService.findById(postId);
        return new ResponseEntity<>(likeConverter
                .convertToDTO(likeService.createLike(account, post)), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{accountId}")
    public void deleteLike(@PathVariable Long postId,
                           @PathVariable Long accountId) {
        Account account = accountService.findById(accountId);
        Post post = postService.findById(postId);
        likeService.deleteLike(post, account);
    }
}
