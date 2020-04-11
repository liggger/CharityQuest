package com.team11.charityserver.service;

import com.team11.charityserver.mapper.DonationMapper;
import com.team11.charityserver.model.Donation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {
    @Autowired
    DonationMapper donationMapper;

    public Integer addDonation(Donation donation) {
        return donationMapper.insertSelective(donation);
    }


    public List<Donation> getAllDonationsByUserId(Integer userId) {
        return donationMapper.getAllDonationsByUserId(userId);
    }
}
