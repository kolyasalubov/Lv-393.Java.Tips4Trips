package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.NotificationDTO;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.repository.CommentRepository;
import com.softserve.academy.Tips4Trips.repository.LikeRepository;
import com.softserve.academy.Tips4Trips.repository.NotifMark;
import com.softserve.academy.Tips4Trips.repository.PostRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NotificationService {

    private PostRepository repository;
    private CommentRepository cRepository;
    private LikeRepository lRepository;

    @Autowired
    public NotificationService(PostRepository repository, CommentRepository cRepository, LikeRepository lRepository) {
        this.repository = repository;
        this.cRepository = cRepository;
        this.lRepository = lRepository;
    }

    private PageImpl<NotificationDTO> getCommentsLikes(Long id, Integer page, NotifMark rep) {
        PageRequest pageable = PageRequest.of(page - 1, 6);
        Page<Post> findByAuthor = repository.findByAuthorIdOrderByIdDesc(id, pageable);
        ArrayList<NotificationDTO> list = new ArrayList<>();
        Page<NotificationDTO> page1;
        for (Post p : findByAuthor) {
            NotificationDTO dto = new NotificationDTO();
            dto.setId(p.getId());
            dto.setCount(rep.countByPost(p));
            dto.setPhotoPath(p.getPhotoPath());
            if (dto.getCount() > 0) {
                list.add(dto);
            }
        }
        return new PageImpl<>(list, pageable, list.size());
    }

    public PageImpl<NotificationDTO> getLikeByCreaterId(Long id, Integer page) {
        return getCommentsLikes(id, page, lRepository);
    }

    public PageImpl<NotificationDTO> getCommentsByCreaterId(Long id, int page) {
        return getCommentsLikes(id, page, cRepository);
    }
}


