import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import projectreducer from "./projectreducers";
import backlogReducer from "./backlogReducer";

export default combineReducers({
  errors: errorReducer,
  project: projectreducer,
  backlog: backlogReducer,
});
