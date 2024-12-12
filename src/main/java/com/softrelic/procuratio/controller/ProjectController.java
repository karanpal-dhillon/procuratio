package com.softrelic.procuratio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softrelic.procuratio.domain.Project;
import com.softrelic.procuratio.service.ProjectService;

@RestController
@RequestMapping("projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> user = projectService.getAllProjects();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
