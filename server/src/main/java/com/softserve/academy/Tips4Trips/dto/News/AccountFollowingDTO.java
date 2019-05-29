package com.softserve.academy.Tips4Trips.dto.News;

import com.softserve.academy.Tips4Trips.dto.details.PostDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.AccountInfoDTO;
import com.softserve.academy.Tips4Trips.entity.blog.Post;

import java.util.List;

public class AccountFollowingDTO extends AccountInfoDTO {
    List<PostDetailsDTO> post;

    public List<PostDetailsDTO> getPost() {
        return post;
    }

    public void setPost(List<PostDetailsDTO> post) {
        this.post = post;
    }
}
