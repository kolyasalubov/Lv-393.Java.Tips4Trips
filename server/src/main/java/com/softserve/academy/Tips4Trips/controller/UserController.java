package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.UserDTO;
import com.softserve.academy.Tips4Trips.dto.converter.UserConverter;
import com.softserve.academy.Tips4Trips.entity.User;
import com.softserve.academy.Tips4Trips.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<UserDTO>> getAll() {
        return new ResponseEntity<>(userConverter
                .convertToDTO(userService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(userConverter
                .convertToDTO(userService.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<UserDTO> getByLogin(@PathVariable String login) {
        return new ResponseEntity<>(userConverter
                .convertToDTO(userService.findByLogin(login)), HttpStatus.OK);
    }

    @GetMapping("/exists/login/{login}")
    public ResponseEntity<Boolean> loginExists(@PathVariable String login) {
        return new ResponseEntity<>(userService.loginExists(login), HttpStatus.OK);
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> emailExists(@PathVariable String email) {
        return new ResponseEntity<>(userService.emailExists(email), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(userConverter.convertToEntity(userDTO));
        return new ResponseEntity<>(userConverter.convertToDTO(user), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        User user = userService.update(userConverter.convertToEntity(userDTO));
        return new ResponseEntity<>(userConverter.convertToDTO(user), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

}
