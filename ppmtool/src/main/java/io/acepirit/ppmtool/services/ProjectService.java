package io.acepirit.ppmtool.services;

import io.acepirit.ppmtool.domain.Backlog;
import io.acepirit.ppmtool.domain.Project;
import io.acepirit.ppmtool.domain.User;
import io.acepirit.ppmtool.exceptions.ProjectIdException;
import io.acepirit.ppmtool.exceptions.ProjectNotFoundExceptions;
import io.acepirit.ppmtool.repositories.BacklogRepository;
import io.acepirit.ppmtool.repositories.ProjectRepository;
import io.acepirit.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private UserRepository userRepository;
    //saving project
    public Project saveorupdate(Project project,String username){
        if(project.getId()!=null){
            Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());

            if(existingProject!=null && !existingProject.getProjectLeader().equals(username)){
                throw new ProjectNotFoundExceptions("Project not found in your account");
            }else if(existingProject==null){
                throw new ProjectNotFoundExceptions("Project with ID:"+project.getProjectIdentifier()+" cannot be updated because it doesn't exists");
            }


        }

        try{

            User user  = userRepository.findByUsername(username);
            project.setUser(user);
            project.setProjectLeader(user.getUsername());


            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            if(project.getId()==null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }else{
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }
            return projectRepository.save(project);
        }catch (Exception ex){
            throw new ProjectIdException("Project Id '"+project.getProjectIdentifier().toUpperCase()+"' already exists");

        }


    }

    public Project findProjectByIdentifier(String projectId,String username){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project==null){
            throw new ProjectIdException("Project Id '"+projectId.toUpperCase()+"' doesnot exist");
        }
        if(!project.getProjectLeader().equals(username)){
            throw new ProjectNotFoundExceptions("Project Not found in your account");
        }


        return project;

    }

    public Iterable<Project> findAll(String name){
        return projectRepository.findAllByProjectLeader(name);
    }

    public void deleteProjectByIdentifier(String projectid,String username){
//        Project project = projectRepository.findByProjectIdentifier(projectid.toUpperCase());
//        if(project==null){
//            throw new ProjectIdException("Project Id '"+projectid.toUpperCase()+"' doesnot exist");
//        }

        projectRepository.delete(findProjectByIdentifier(projectid,username));
    }

//    public Project updateProject(String projectId){
//        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
//        if(project==null){
//            throw new ProjectIdException("Project Id '"+projectId.toUpperCase()+"' doesnot exist");
//        }
//
//    }
}
