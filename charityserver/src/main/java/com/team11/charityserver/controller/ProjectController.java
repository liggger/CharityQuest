package com.team11.charityserver.controller;

import com.team11.charityserver.model.Project;
import com.team11.charityserver.model.RespBean;
import com.team11.charityserver.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping("/project")
    public RespBean addProject(@RequestBody Project project) {
        if (projectService.addProject(project) == 1) {
            return RespBean.ok("Add project successfully!");
        }
        return RespBean.error("Cannot add project!");
    }

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }
}
