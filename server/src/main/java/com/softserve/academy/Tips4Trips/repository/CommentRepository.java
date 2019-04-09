package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.Comment;
import com.softserve.academy.Tips4Trips.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    long countByPost(Post post);
    Optional<Comment> findByPost(Post post);

}
