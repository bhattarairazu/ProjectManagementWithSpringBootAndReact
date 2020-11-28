package io.acepirit.ppmtool.web;

import io.acepirit.ppmtool.domain.Project;
import io.acepirit.ppmtool.domain.ProjectTask;
import io.acepirit.ppmtool.services.MapValiationError;
import io.acepirit.ppmtool.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin("http://localhost:3000")
public class BackLogController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private MapValiationError mapValiationError;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addProjectTaskTOBacklog(@Valid @RequestBody ProjectTask projectTask, BindingResult bindingResult, @PathVariable String backlog_id, Principal principal){

        ResponseEntity<?> erroMap = mapValiationError.MapValidationError(bindingResult);
        if(erroMap!=null) return erroMap;

        ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id,projectTask,principal.getName());
        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id,Principal principal)
    {
        return projectTaskService.findBacklogById(backlog_id,principal.getName());
    }

    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id,@PathVariable String pt_id,Principal principal){
        ProjectTask projectTask = projectTaskService.findPtByProjectSequene(backlog_id,pt_id,principal.getName());
        return new ResponseEntity<ProjectTask>(projectTask,HttpStatus.OK);
    }

    @PatchMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask,BindingResult result,@PathVariable String backlog_id,@PathVariable String pt_id,Principal principal){
        ResponseEntity<?> errorMap = mapValiationError.MapValidationError(result);
        if(errorMap!=null) return errorMap;
         ProjectTask updatedtask = projectTaskService.updateByProjectSequence(projectTask,backlog_id,pt_id,principal.getName());
                 return new ResponseEntity<ProjectTask>(updatedtask,HttpStatus.OK);
    }
    @DeleteMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id,@PathVariable String pt_id,Principal principal){
        projectTaskService.deleteByProjectSequence(backlog_id,pt_id,principal.getName());
        return new ResponseEntity<String>("Project Task "+pt_id+" was deleted successfully",HttpStatus.OK);
    }

}
