package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.dto.search.PostSearchParams;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.repository.search.SearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository
        extends JpaRepository<Post, Long>, SearchRepository<Post, PostSearchParams> {

    Optional<Post> findById(Long id);

    Page<Post> findAllByOrderByIdDesc(Pageable pageable);

    List<Post> findByNameContainingIgnoreCase(String name);

    List<Post> findByAuthor(Account author);

    List<Post> findByRoute(Route route);

    Page<Post> findByAuthorIdOrderByIdDesc(Long id, Pageable pageable);
//    Page<Post> findByAuthorByOrderByIdDesc(Account account, Pageable pageable);
}
