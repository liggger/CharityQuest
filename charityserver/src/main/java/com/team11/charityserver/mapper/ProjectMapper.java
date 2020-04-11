package com.team11.charityserver.mapper;


import com.team11.charityserver.model.Project;

import java.util.List;

public interface ProjectMapper {
    int insertSelective(Project project);

    List<Project> getAllProjects();
}
