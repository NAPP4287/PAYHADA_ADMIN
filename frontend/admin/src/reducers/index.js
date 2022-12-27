import { combineReducers } from "redux";
import commonReducer from './commonReducer'

const rootReducer = combineReducers({
    //reducer 작성 후 조합
    commonReducer,
});
export default rootReducer;