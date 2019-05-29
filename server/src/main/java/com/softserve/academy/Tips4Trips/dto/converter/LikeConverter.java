package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.LikeDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Like;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LikeConverter implements Converter<Like, LikeDTO> {

    private PostService postService;
    private AccountService accountService;

    @Autowired
    public LikeConverter(PostService postService, AccountService accountService) {
        this.postService = postService;
        this.accountService = accountService;
    }


    @Override
    public LikeDTO convertToDTO(Like like) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setId(like.getId());
        likeDTO.setAccountId(like.getLikedBy().getId());
        likeDTO.setPostId(like.getPost().getId());
        return likeDTO;
    }

    @Override
    public Like convertToEntity(LikeDTO likeDTO) {
        Like like = new Like();
        like.setId(likeDTO.getId());
        Account account = accountService.findById(likeDTO.getAccountId());
        like.setLikedBy(account);
        Post post = postService.findById(likeDTO.getPostId());
        like.setPost(post);
        return like;
    }
}
