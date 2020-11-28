import React, { Component } from "react";
import ProjectTaskItem from "./ProjectTask/ProjectTaskItem";

class Backlog extends Component {
  render() {
    const { project_tasks } = this.props;
    const tasks = project_tasks.map((project_task) => (
      <ProjectTaskItem key={project_task.id} project_task={project_task} />
    ));
    let todoItems = []
    let inProgressItems =[]
    let doneItems = []

    for(let i =0;i<tasks.length;i++){
     //   console.log(tasks[i]);
        if(tasks[i].props.project_task.status === "TO_DO"){
            todoItems.push(tasks[i]);
        }else if(tasks[i].props.project_task.status ==="DONE"){
            doneItems.push(tasks[i]);
        }else{
            inProgressItems.push(tasks[i]);
        }
    }

    return (
      <div>
        <div className="container">
          <div className="row">
            <div className="col-md-4">
              <div className="card text-center mb-2">
                <div className="card-header bg-secondary text-white">
                  <h3>TO DO</h3>
                </div>
              </div>

              {todoItems}
            </div>
            <div className="col-md-4">
              <div className="card text-center mb-2">
                <div className="card-header bg-primary text-white">
                  <h3>In Progress</h3>
                </div>
              </div>
              {
                // <!-- SAMPLE PROJECT TASK STARTS HERE -->
                //<!-- SAMPLE PROJECT TASK ENDS HERE -->
              }
              {inProgressItems}
            </div>
            <div className="col-md-4">
              <div className="card text-center mb-2">
                <div className="card-header bg-success text-white">
                  <h3>Done</h3>
                </div>
              </div>
              {
                // <!-- SAMPLE PROJECT TASK STARTS HERE -->
                //<!-- SAMPLE PROJECT TASK ENDS HERE -->
              }
              {doneItems}
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default Backlog;
