package com.isicod.net.communitiesManagement.controllers;

import com.isicod.net.communitiesManagement.dto.UsersDtoAuthentication;
import com.isicod.net.communitiesManagement.models.Users;
import com.isicod.net.communitiesManagement.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis/users/")
@CrossOrigin("*")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("authenticateUser")
    public Users authenticateUser(@RequestBody UsersDtoAuthentication userDto){
        return usersService.authenticateUser(userDto);
    }
}
