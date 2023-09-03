package com.evelyn.myapi.controller;

//import com.evelyn.mysocial.service.UserService;
import com.evelyn.myapi.jdbcrepository.JdbcUserRepository;
import com.evelyn.myapi.model.Post;
import com.evelyn.myapi.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
//@RequestMapping("/api")
public class UserController {
    @Autowired
    // UserService userService;
    JdbcUserRepository jdbcUserRepository;

    @RequestMapping(value="/api/users/count", method=RequestMethod.GET)
    public long getCount() {
        return jdbcUserRepository.count();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/api/users/all", method=RequestMethod.GET)
    public List<User> getAll() {
        return jdbcUserRepository.findAll();
    }


    @RequestMapping(value="/api/users/find/{id}", method=RequestMethod.GET)
    public User getUserById(@PathVariable(value = "id") Long id) {
        return jdbcUserRepository.findById(id);
    }

    @Transactional
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/users/login", method=RequestMethod.POST)
    public ResponseEntity<User> loginExistingUser(@RequestBody User user) {
//        System.out.println(user.getFirstName());
//        System.out.println(user.getLastName());
//        System.out.println(user.getEmail());
        if (user.getEmail().length() == 0) {
            return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
        }
        List<User> resultUser = jdbcUserRepository.findUserByEmail(user.getEmail());
        if (!resultUser.isEmpty()) { // user exists
            if (user.getPassword().equals(resultUser.get(0).getPassword())) {
                // password matched;
                return new ResponseEntity<User>(user, HttpStatus.OK);
            } else {
                // wrong password entered
                return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/users/create", method=RequestMethod.POST)
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
//        System.out.println(user.getFirstName());
//        System.out.println(user.getLastName());
        System.out.println("createNewUser called, username: " + user.getEmail());
        if (user.getEmail().length() == 0) {
            return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
        }
        List<User> resultUser = jdbcUserRepository.findUserByEmail(user.getEmail());
        if (!resultUser.isEmpty()) { // user exists
                return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
        } else {
            jdbcUserRepository.saveUser(user);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "/api/users/update/{id}", method=RequestMethod.POST)
    public int updateUser(@PathVariable(value = "id") Long id , @RequestBody User user) {
        System.out.println("User controller update method");
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getPhoneNumber());

        User toBeUpdatedUser = jdbcUserRepository.findById(id);
        int result = 1;
        if (toBeUpdatedUser != null) {
            if (user.getEmail() != null) {
                toBeUpdatedUser.setEmail(user.getEmail());
            }
            if (user.getPassword() != null) {
                toBeUpdatedUser.setPassword(user.getPassword());
            }
            if (user.getPhoneNumber() != null) {
                toBeUpdatedUser.setPhoneNumber(user.getPhoneNumber());
            }
            jdbcUserRepository.updateUser(toBeUpdatedUser);
        } else {
            result = 0;
        }
        return result;
    }




}