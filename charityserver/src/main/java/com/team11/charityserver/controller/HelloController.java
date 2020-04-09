package com.team11.charityserver.controller;

import com.team11.charityserver.model.Order;
import com.team11.charityserver.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/hello")
    public String postHello(@RequestBody User user) {
        System.out.println(user.getGender());
        return "false";
    }

    @PutMapping("/hello")
    public String putHello() {
        return "false";
    }
}
