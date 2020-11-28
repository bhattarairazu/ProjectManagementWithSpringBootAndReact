package io.acepirit.ppmtool.repositories;

import io.acepirit.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask,Long> {
    List<ProjectTask> findByProjectIdentifierOrderByPriority(String projectIdenifier);

    ProjectTask findByProjectSequence(String sequence);

}
