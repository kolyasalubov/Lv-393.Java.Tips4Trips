package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Comment;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>,NotifMark  {
    long countByPostId(Long id);

    List<Comment> findByPostId(Long id);

    boolean existsByCommentedByIdAndId(Long accountId, Long commentId);

    Page<Comment> findByPostIn(List<Post> list, Pageable pageable);


}
