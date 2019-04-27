package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long id);

    Page<Post> findAllByOrderByIdDesc(Pageable pageable);

    List<Post> findByNameContainingIgnoreCase(String name);

    List<Post> findByAuthor(Account author);

    List<Post> findByRoute(Route route);
}
