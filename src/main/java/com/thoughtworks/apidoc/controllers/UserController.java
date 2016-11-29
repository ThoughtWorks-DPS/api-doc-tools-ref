package com.thoughtworks.apidoc.controllers;

import com.thoughtworks.apidoc.model.User;
import com.thoughtworks.apidoc.resources.DefaultQueryParams;
import com.thoughtworks.apidoc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/users", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(CREATED)
    @RequestMapping(method = POST)
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), CREATED);
    }


    @RequestMapping(method = GET)
    public ResponseEntity<List<User>> getUsers(DefaultQueryParams queryParams) {
        return new ResponseEntity<>(userService.getAllUsers(queryParams.getPage(), queryParams.getPerPage()), HttpStatus.OK);
    }


}
