package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.CountryDTO;
import com.softserve.academy.Tips4Trips.dto.PostDTO;
import com.softserve.academy.Tips4Trips.dto.UserDTO;
import com.softserve.academy.Tips4Trips.dto.converter.CountryConverter;
import com.softserve.academy.Tips4Trips.entity.Country;
import com.softserve.academy.Tips4Trips.entity.Post;
import com.softserve.academy.Tips4Trips.entity.User;
import com.softserve.academy.Tips4Trips.service.CountryService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/countries")
public class CountryController {

    CountryConverter countryConverter;
    CountryService countryService;

    @Autowired
    public CountryController(CountryConverter countryConverter, CountryService countryService) {
        this.countryConverter = countryConverter;
        this.countryService = countryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CountryDTO>> getAll() {
        return new ResponseEntity<>(countryConverter
                .convert(countryService.findAll()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO) {
        try {
            Country country = countryService.createCountry(countryDTO);
            return new ResponseEntity<>(countryConverter.convert(country), HttpStatus.CREATED);
        } catch (HibernateException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<PostDTO> getById(@PathVariable Long id) {
//        Optional<Post> post = postService.findById(id);
//        return post.map(p -> new ResponseEntity<>(postConverter
//                .convert(p), HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
//        try {
//            User user = userService.update(userDTO);
//            return new ResponseEntity<>(userConverter.convert(user), HttpStatus.ACCEPTED);
//        } catch (HibernateException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public void deleteById(@PathVariable Long id) {
//        postService.findById(id).ifPresent(postService::delete);
//    }

}
