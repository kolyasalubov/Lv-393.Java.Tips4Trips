package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.Post;
import com.softserve.academy.Tips4Trips.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByNameContainingIgnoreCase(String name);

    List<Post> findByAuthor(Account author);

    List<Post> findByRoute(Route route);
}
