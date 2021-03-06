package com.softserve.academy.Tips4Trips.controller;


import com.softserve.academy.Tips4Trips.dto.converter.AccountConverter;
import com.softserve.academy.Tips4Trips.dto.converter.LikeConverter;
import com.softserve.academy.Tips4Trips.dto.info.AccountInfoDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.LikeService;
import com.softserve.academy.Tips4Trips.service.PostService;
import org.apache.log4j.Logger;
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
    private AccountConverter accountConverter;
    private AccountService accountService;

    @Autowired
    public LikeController(LikeConverter likeConverter,
                          LikeService likeService,
                          AccountConverter accountConverter,
                          PostService postService,
                          AccountService accountService) {
        this.likeConverter = likeConverter;
        this.likeService = likeService;
        this.accountConverter = accountConverter;
        this.postService = postService;
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountInfoDTO>> getAccounts(@PathVariable Long postId) {
        logger.info("getAccounts method executing: ");
        Post post = postService.findById(postId);
        return new ResponseEntity<>(accountConverter.convertToInfoDTO(likeService
                .findAccounts(post)), HttpStatus.OK);
    }

    @GetMapping("/count")
    public long countLikes(@PathVariable Long postId) {
        logger.info("countLikes method executing: ");
        long count = likeService.countByPostId(postId);
        return count;
    }

    @PostMapping("/change/{accountId}")
    public void changeLikeState(@PathVariable Long postId,
                                @PathVariable Long accountId) {
        logger.info("changeLikeState method executing: ");
        Account account = accountService.findById(accountId);
        Post post = postService.findById(postId);
        if (likeService.existsByLikedByAndPost(account, post)) {
            likeService.deleteLike(post, account);
        } else {
            likeService.createLike(account, post);
        }
    }

    @GetMapping("/exists/{accountId}")
    public boolean isAdded(@PathVariable Long postId,
                           @PathVariable Long accountId) {
        logger.info("isAdded method executing: ");
        boolean isAdded;
        Account account = accountService.findById(accountId);
        Post post = postService.findById(postId);
        if (likeService.existsByLikedByAndPost(account, post)) {
            isAdded = true;
        } else {
            isAdded = false;
        }
        return isAdded;
    }
}
