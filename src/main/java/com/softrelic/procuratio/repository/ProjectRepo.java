package com.softrelic.procuratio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softrelic.procuratio.domain.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer> {

}
