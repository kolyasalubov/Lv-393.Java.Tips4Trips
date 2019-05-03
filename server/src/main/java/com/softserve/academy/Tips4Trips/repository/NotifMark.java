package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.blog.Post;

public interface NotifMark {
    Long countByPost(Post post);
}
