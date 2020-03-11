package com.team11.charityserver.mapper;

import com.team11.charityserver.model.User;

public interface UserMapper {
    User loadUserByUsername(String username);
}
