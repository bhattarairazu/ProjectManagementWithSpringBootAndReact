package io.acepirit.ppmtool.web;

import io.acepirit.ppmtool.domain.Project;
import io.acepirit.ppmtool.services.MapValiationError;
import io.acepirit.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project/")
@CrossOrigin("http://localhost:3000")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValiationError mapValiationError;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result, Principal principal){
        //for validation
        ResponseEntity<?> errEntity = mapValiationError.MapValidationError(result);
        if(errEntity!=null) return errEntity;

        Project projects = projectService.saveorupdate(project,principal.getName());
        return new ResponseEntity<Project>(projects, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId,Principal principal){
        Project project = projectService.findProjectByIdentifier(projectId,principal.getName());
        return new ResponseEntity<Project>(project,HttpStatus.OK);

    }

    @GetMapping("/all")
    public Iterable<Project> getAll(Principal principal){
        return projectService.findAll(principal.getName());
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId,Principal principal){
        projectService.deleteProjectByIdentifier(projectId,principal.getName());
        return new ResponseEntity<String>("Project with id"+projectId+" Deleted Successfully",HttpStatus.OK);
    }
}
