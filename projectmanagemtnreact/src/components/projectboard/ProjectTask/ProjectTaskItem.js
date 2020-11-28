import React, { Component } from 'react'
import {Link} from "react-router-dom";
import {deleteProjectTask} from "../../../actions/backlogActions";
import PropTypes from "prop-types";
import {connect} from "react-redux";

 class ProjectTaskItem extends Component {
     onDeleteClick(backlog_id,pt_id){
         this.props.deleteProjectTask(backlog_id,pt_id);
     }
    render() {
        const {project_task} = this.props;
        let prorityClass;
        let priorityString;
        if(project_task.priority===1){
            prorityClass="bg-danger text-light";
            priorityString ="HIGH";
        }else if(project_task.priority===2){

            prorityClass="bg-warning text-light";
            priorityString ="MEDIUM";
        }else{
            prorityClass="bg-info text-light";
            priorityString ="LOW";
        }
        return (
            <div>
                 
                    <div className="card mb-1 bg-light">

                        <div className={`card-header text-primary ${prorityClass}`}>
                            ID: {project_task.projectSequence} -- Priority:{" "} {priorityString}
                        </div>
                        <div className="card-body bg-light">
                            <h5 className="card-title">{project_task.summary}</h5>
                            <p className="card-text text-truncate ">
                                {project_task.acceptanceCriteria}
                            </p>
                            <Link to={`/updateProjectTask/${project_task.projectIdentifier}/${project_task.projectSequence}`} className="btn btn-primary">
                                View / Update
                            </Link>

                            <button className="btn btn-danger ml-4" onClick={this.onDeleteClick.bind(this,project_task.projectIdentifier,project_task.projectSequence)}>
                                Delete
                            </button>
                        </div>
                    </div>

                   
            </div>
        )
    }
}
ProjectTaskItem.propTypes = {
deleteProjectTask: PropTypes.func.isRequired
};

export default connect(null,{deleteProjectTask})(ProjectTaskItem);
