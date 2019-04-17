package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.UserDTO;
import com.softserve.academy.Tips4Trips.dto.converter.UserConverter;
import com.softserve.academy.Tips4Trips.entity.administration.User;
import com.softserve.academy.Tips4Trips.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    private UserService userService;
    private UserConverter userConverter;

    @Autowired
    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAll() {
        logger.info("get all users method executing: ");
        return new ResponseEntity<>(userConverter
                .convertToDTO(userService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        logger.info("get user by id method executing: ");
        return new ResponseEntity<>(userConverter
                .convertToDTO(userService.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<UserDTO> getByLogin(@PathVariable String login) {
        logger.info("get user by login method executing: ");
        return new ResponseEntity<>(userConverter
                .convertToDTO(userService.findByLogin(login)), HttpStatus.OK);
    }

    @GetMapping("/exists/login/{login}")
    public ResponseEntity<Boolean> loginExists(@PathVariable String login) {
        logger.info("login exists method executing: ");
        return new ResponseEntity<>(userService.loginExists(login), HttpStatus.OK);
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> emailExists(@PathVariable String email) {
        logger.info("email exists method executing: ");
        return new ResponseEntity<>(userService.emailExists(email), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        logger.info("create user method executing: ");
        User user = userService.createUser(userConverter.convertToEntity(userDTO));
        return new ResponseEntity<>(userConverter.convertToDTO(user), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        logger.info("update user method executing: ");
        User user = userService.update(userConverter.convertToEntity(userDTO));
        return new ResponseEntity<>(userConverter.convertToDTO(user), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        logger.info("delete user by id method executing: ");
        userService.deleteById(id);
    }

}
