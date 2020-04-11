package com.team11.charityserver.service;

import com.team11.charityserver.mapper.CharityMapper;
import com.team11.charityserver.model.Charity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharityService {
    @Autowired
    CharityMapper charityMapper;


    public Integer addCharity(Charity charity) {
        return charityMapper.insertSelective(charity);
    }
}
