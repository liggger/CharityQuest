package com.team11.charityserver.mapper;

import com.team11.charityserver.model.Charity;

public interface CharityMapper {
    int insertSelective(Charity charity);
}
