package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.UserDTO;
import com.softserve.academy.Tips4Trips.dto.converter.UserConverter;
import com.softserve.academy.Tips4Trips.entity.administration.User;
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
        Optional<User> user = userService.findById(id);
        return user.map(u -> new ResponseEntity<>(userConverter
                .convertToDTO(u), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<UserDTO> getByLogin(@PathVariable String login) {
        Optional<User> user = userService.findByLogin(login);
        return user.map(u -> new ResponseEntity<>(userConverter
                .convertToDTO(u), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/exists/login/{login}")
    public boolean loginExists(@PathVariable String login) {
        return userService.loginExists(login);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
<<<<<<< HEAD
        User user = userService.createUser(userConverter.convertToEntity(userDTO));
        return new ResponseEntity<>(userConverter.convertToDTO(user), HttpStatus.CREATED);
=======
        try {
            User user = userService.createUser(userDTO);
            return new ResponseEntity<>(userConverter.convertToDTO(user), HttpStatus.CREATED);
        } catch (HibernateException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
>>>>>>> 34dacc720e840960dbb65139bd53b58671477958
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
<<<<<<< HEAD
        User user = userService.update(userConverter.convertToEntity(userDTO));
        return new ResponseEntity<>(userConverter.convertToDTO(user), HttpStatus.ACCEPTED);
=======
        try {
            User user = userService.update(userDTO);
            return new ResponseEntity<>(userConverter.convertToDTO(user), HttpStatus.ACCEPTED);
        } catch (HibernateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
>>>>>>> 34dacc720e840960dbb65139bd53b58671477958
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.findById(id).ifPresent(userService::delete);
    }

}
