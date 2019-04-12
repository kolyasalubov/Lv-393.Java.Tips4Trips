package com.softserve.academy.Tips4Trips.controller;


import com.softserve.academy.Tips4Trips.dto.LikeDTO;
import com.softserve.academy.Tips4Trips.dto.converter.AccountConverter;
import com.softserve.academy.Tips4Trips.dto.converter.LikeConverter;
import com.softserve.academy.Tips4Trips.dto.converter.PostConverter;
import com.softserve.academy.Tips4Trips.entity.blog.Like;
import com.softserve.academy.Tips4Trips.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/likes")
public class LikeController {
    private LikeConverter likeConverter;
    private LikeService likeService;
    private PostConverter postConverter;
    private AccountConverter accountConverter;


    @Autowired
    public LikeController(LikeConverter likeConverter, LikeService likeService) {
        this.likeConverter = likeConverter;
        this.likeService = likeService;
    }

/*    public long countComment(@RequestBody PostDTO postDTO) {
        long count = likeService.countByPost(postConverter.convertToEntity(postDTO));
        return count;
    }*/


    // ???
    @PostMapping("/create")
    public ResponseEntity<LikeDTO> createLike(@RequestBody LikeDTO likeDTO) {
        System.out.println(likeDTO.toString());
        Like like = likeService.createLike(likeConverter.convertToEntity(likeDTO));
        return new ResponseEntity<>(likeConverter.convertToDTO(like), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLike(@PathVariable Long id) { //like?
        likeService.deleteLike(id);
    }
}
