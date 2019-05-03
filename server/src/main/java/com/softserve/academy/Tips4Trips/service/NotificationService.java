package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.NotificationDTO;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.Trip;
import com.softserve.academy.Tips4Trips.repository.*;
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
    private TripRepository tRepository;


    @Autowired
    public NotificationService(PostRepository repository, CommentRepository cRepository, LikeRepository lRepository, TripRepository tRepository) {
        this.repository = repository;
        this.cRepository = cRepository;
        this.lRepository = lRepository;
        this.tRepository = tRepository;
    }

    private PageImpl<NotificationDTO> getCommentsLikes(Long id, Integer page, NotifMark rep) {
        PageRequest pageable = PageRequest.of(page - 1, 6);
        Page<Post> findByAuthor = repository.findByAuthorIdOrderByIdDesc(id, pageable);
        ArrayList<NotificationDTO> list = new ArrayList<>();
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

    public Page<Trip> getTripByCreaterId(Long id, int page) {
        PageRequest pageable = PageRequest.of(page - 1, 6);
        return tRepository.findByCreatorIdOrderByIdDesc(id, pageable);
    }
}


