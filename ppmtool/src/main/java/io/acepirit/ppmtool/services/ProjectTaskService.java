package io.acepirit.ppmtool.services;

import io.acepirit.ppmtool.domain.Backlog;
import io.acepirit.ppmtool.domain.Project;
import io.acepirit.ppmtool.domain.ProjectTask;
import io.acepirit.ppmtool.exceptions.ProjectNotFoundExceptions;
import io.acepirit.ppmtool.repositories.BacklogRepository;
import io.acepirit.ppmtool.repositories.ProjectRepository;
import io.acepirit.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask,String username){
        //Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier,username).getBacklog();

        projectTask.setBacklog(backlog);
        Integer BacklogSequence = backlog.getPTSequence();
        System.out.println("back"+backlog.getPTSequence());
        BacklogSequence++;
        backlog.setPTSequence(BacklogSequence);
        projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);

        projectTask.setProjectIdentifier(projectIdentifier);

        if(projectTask.getPriority()==null|| projectTask.getPriority()==0){
            projectTask.setPriority(3);
        }

        if(projectTask.getStatus()==null || projectTask.getStatus()==""){
            projectTask.setStatus("TO_DO");
        }
        return projectTaskRepository.save(projectTask);


    }

    public Iterable<ProjectTask> findBacklogById(String backlog_id,String username) {
//        Project project = projectRepository.findByProjectIdentifier(backlog_id);
//        if(project==null){
//            throw new ProjectNotFoundExceptions("Project with Id '"+backlog_id+"' does not exist");
//        }

        projectService.findProjectByIdentifier(backlog_id,username);


        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
    }

    public ProjectTask findPtByProjectSequene(String backlog_id,String pt_id,String username){

//        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
//
//        if(backlog==null){
//            throw new ProjectNotFoundExceptions("Project with Id '"+backlog_id+"' does not exist");
//        }

        projectService.findProjectByIdentifier(backlog_id,username);

        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);

        if(projectTask==null){
            throw new ProjectNotFoundExceptions("Project Task "+pt_id+" not found");
        }
        if(!projectTask.getProjectIdentifier().equals(backlog_id)){
            throw new ProjectNotFoundExceptions("Project Task '"+pt_id+"' does not exist in project, "+backlog_id);
        }

        return projectTaskRepository.findByProjectSequence(pt_id);
    }

    public ProjectTask updateByProjectSequence(ProjectTask projectTask, String backlog_id, String pt_id,String username) {
//        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
//        if(backlog==null){
//            throw new ProjectNotFoundExceptions("Project with Id '"+backlog_id+"' does not exist");
//        }

        projectService.findProjectByIdentifier(backlog_id,username);



        ProjectTask projectTask1 = projectTaskRepository.findByProjectSequence(pt_id);
        if(projectTask1 == null){
            throw new ProjectNotFoundExceptions("Project Task: "+pt_id+" not found");
        }
        if(!projectTask1.getBacklog().getProjectIdentifier().toUpperCase().equals(backlog_id.toUpperCase())){
            throw new ProjectNotFoundExceptions("Project Task does not exist in project: "+backlog_id);
        }

        projectTask1 = projectTask;
        return projectTaskRepository.save(projectTask1);

    }
    public void deleteByProjectSequence(String backlog_id,String pt_id,String username){
        ProjectTask projectTask = findPtByProjectSequene(backlog_id,pt_id,username);

//        Backlog backlog = projectTask.getBacklog();
//        List<ProjectTask> pts = backlog.getProjectTasks();
//        pts.remove(projectTask);
//        backlogRepository.save(backlog);

        projectTaskRepository.delete(projectTask);
    }

}
