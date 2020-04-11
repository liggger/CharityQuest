package com.team11.charityserver.controller;

import com.team11.charityserver.model.Donation;
import com.team11.charityserver.model.RespBean;
import com.team11.charityserver.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DonationController {
    @Autowired
    DonationService donationService;

    @PostMapping("/donation")
    public RespBean addDonation(@RequestBody Donation donation) {
        if (donationService.addDonation(donation) == 1) {
            return RespBean.ok("Add donation successfully!");
        }
        return RespBean.error("Cannot add donation!");
    }

    @GetMapping("/donation")
    public List<Donation> getAllDonationsByUserId(@RequestParam Integer userId) {
        return donationService.getAllDonationsByUserId(userId);
    }
}
