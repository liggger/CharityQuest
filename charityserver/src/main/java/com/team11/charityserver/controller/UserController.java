package com.team11.charityserver.controller;

import com.team11.charityserver.model.RespBean;
import com.team11.charityserver.model.User;
import com.team11.charityserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        if (userService.addUser(user) == 1) {
            return RespBean.ok("Register successfully！");
        }
        return RespBean.error("Cannot register！");
    }

    @PutMapping("/user")
    public RespBean updateUser(@RequestBody User user) {
//        User user = new User();
//        user.setUserId(1);
//        user.setRoleId(4);
//        user.setUsername("zzhou");
//        user.setGender("male");
//        user.setPassword("$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm");
//        user.setTelephone("7738427156");
//        user.setMail("zhichengzhou_96@163.com");
        if (userService.updateUser(user) == 1) {
            return RespBean.ok("Update successfully！");
        }
        return RespBean.error("Cannot update user! ");
    }
}
