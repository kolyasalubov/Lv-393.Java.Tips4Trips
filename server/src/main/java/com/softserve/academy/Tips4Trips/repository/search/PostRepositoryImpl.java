package com.softserve.academy.Tips4Trips.repository.search;

import com.softserve.academy.Tips4Trips.dto.Page;
import com.softserve.academy.Tips4Trips.dto.search.PostSearchParams;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class PostRepositoryImpl implements SearchRepository<Post, PostSearchParams> {

    private EntityManager em;
    private CriteriaBuilder cb;

    @Autowired
    public PostRepositoryImpl(EntityManager em) {
        this.em = em;
        cb = em.getCriteriaBuilder();
    }

    @Override
    public Page<Post> findByParams(PostSearchParams searchParams, long page, int size) {
        CriteriaQuery<Post> cq = cb.createQuery(Post.class);
        Root<Post> post = cq.from(Post.class);
        List<Predicate> wherePredicates = getWherePredicates(searchParams, post);
        cq.where(wherePredicates.toArray(new Predicate[0]));
        long count = getCountByParams(searchParams);
        long total = (count % size == 0) ? count / size : count / size + 1;
        List<Post> result = em.createQuery(cq)
                .setFirstResult((int) page * size)
                .setMaxResults(size).getResultList();
        return new Page<>(result, page, total);
    }

    private long getCountByParams(PostSearchParams searchParams) {
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Post> from = cq.from(Post.class);
        List<Predicate> wherePredicates = getWherePredicates(searchParams, from);
        cq.select(cb.count(from))
                .where(wherePredicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getSingleResult();
    }

    private List<Predicate> getWherePredicates(PostSearchParams searchParams, Root<Post> post) {
        List<Predicate> predicates = new LinkedList<>();
        if (searchParams.getName() != null) {
            predicates.add(cb.like(cb.upper(post.get("name")),
                    "%" + searchParams.getName().toUpperCase() + "%"));
        }
        if (searchParams.getStartDate() != null) {
            predicates.add(cb.greaterThanOrEqualTo(post.get("creationDate"), searchParams.getStartDate()));
        }
        if (searchParams.getEndDate() != null) {
            predicates.add(cb.lessThanOrEqualTo(post.get("creationDate"), searchParams.getEndDate()));
        }
        return predicates;
    }
}
