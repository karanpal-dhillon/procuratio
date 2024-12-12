package com.softrelic.procuratio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softrelic.procuratio.domain.Project;
import com.softrelic.procuratio.repository.ProjectRepo;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepo projectRepo;

    public List<Project> getAllProjects() {

        return projectRepo.findAll();
    }

}
