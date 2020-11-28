package io.acepirit.ppmtool.services;

import io.acepirit.ppmtool.domain.Project;
import io.acepirit.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckProjectOwnerService {
    @Autowired
    private ProjectRepository projectRepository;

//    public Project project_checkProjectOwner(String projectid,String username){
////        Project project = projectRepository.findByProjectId
//    }
}
