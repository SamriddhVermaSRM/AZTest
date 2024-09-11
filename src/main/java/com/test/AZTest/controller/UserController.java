package com.test.AZTest.controller;


import com.test.AZTest.model.User;
import com.test.AZTest.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(("/user"))
public class UserController {
    @Autowired

    private UserService _userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return _userService.FetchAllUsers();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return _userService.GetUserByID(id);
    }

    @GetMapping("/username/{userName}")
    public ResponseEntity<?> getByUserName(@PathVariable String userName) {
        return _userService.GetUserByUsername(userName);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody User user) {
        return _userService.UpdateUser(user);
    }

    @PostMapping("/save")
    public ResponseEntity<?> registerUser(@Validated @RequestBody User user) {
        return _userService.RegisterUser(user);
    }

    @GetMapping("/ping/{username}")
    public ResponseEntity<?> pingUser(@PathVariable String username, HttpServletRequest request) {
        return _userService.PING(username, request.getRemoteAddr());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        return _userService.DeleteUser(user);
    }
}

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ping")
class PingController {
    @GetMapping("/")
    public ResponseEntity<?> ping() {
        return new ResponseEntity<>("Server do be serving", HttpStatus.OK);
    }
}