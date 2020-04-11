package com.team11.charityserver.controller;

import com.team11.charityserver.model.Charity;
import com.team11.charityserver.model.RespBean;
import com.team11.charityserver.service.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharityController {
    @Autowired
    CharityService charityService;

    @PostMapping("/apply")
    public RespBean addCharity(@RequestBody Charity charity) {
        if (charityService.addCharity(charity) == 1) {
            return RespBean.ok("Apply successfully!");
        }
        return RespBean.ok("Cannot apply!");
    }
}
