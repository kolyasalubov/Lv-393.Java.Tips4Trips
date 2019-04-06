package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.UserDto;
import com.softserve.academy.Tips4Trips.dto.converter.UserConverter;
import com.softserve.academy.Tips4Trips.entity.User;
import com.softserve.academy.Tips4Trips.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserConverter userConverter;

    @Autowired
    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(userConverter
                .convert(userService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable long id) {
        Optional<User> user = userService.findById(id);
        return user.map(u -> new ResponseEntity<>(userConverter.
                convert(u), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable long id) {
        userService.findById(id).ifPresent(userService::delete);
    }

}
