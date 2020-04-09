package com.team11.charityserver.controller;

import com.team11.charityserver.model.RespBean;
import com.team11.charityserver.model.User;
import com.team11.charityserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public RespBean addUser(@RequestBody User user) {
        user.setRoleId(3);
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()).trim());
        userService.addUser(user);
        return RespBean.ok("Register successfully！");
    }
}
