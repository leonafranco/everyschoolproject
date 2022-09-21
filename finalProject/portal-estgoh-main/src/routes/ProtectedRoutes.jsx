import { UserContext } from "../context/user.context";
import { useContext } from "react";
import { Outlet } from "react-router-dom";
import Blank from "./blank/blank.component";


const ProtectedRoutes = () => {
    const { currentUser } = useContext(UserContext);
    return currentUser ? <Outlet/> : <Blank />;
};


export default ProtectedRoutes