package com.softserve.academy.Tips4Trips.controller;


import com.softserve.academy.Tips4Trips.dto.LikeDTO;
import com.softserve.academy.Tips4Trips.dto.converter.LikeConverter;
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
    LikeConverter likeConverter;
    LikeService likeService;

    @Autowired
    public LikeController(LikeConverter likeConverter, LikeService likeService) {
        this.likeConverter = likeConverter;
        this.likeService = likeService;
    }

    @GetMapping("/all")

    @PostMapping("/create")
    public ResponseEntity<LikeDTO> createLike(@RequestBody LikeDTO likeDTO) {
        Like like = likeService.createLike(likeConverter.convertToEntity(likeDTO));
        return new ResponseEntity<>(likeConverter.convertToDTO(like), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}") //???
    public void deleteLike(@PathVariable Like like) { //like?
        likeService.deleteLike(like);
    }
}
