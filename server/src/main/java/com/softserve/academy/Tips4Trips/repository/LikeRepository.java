package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Like;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long>, NotifMark {

    long countByPostId(Long id);

    Optional<Like> findByLikedByAndPost(Account account, Post post);

    List<Like> findByPost(Post post);

    Boolean existsByLikedByAndPost(Account account, Post post);

}
