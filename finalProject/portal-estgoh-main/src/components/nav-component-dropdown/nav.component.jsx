import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Button from "react-bootstrap/Button";
import Navigation from "react-bootstrap/Nav";
import { NavDropdown } from "react-bootstrap";
import { ReactComponent as BellIcon } from "../../assets/bell.svg";
import { ReactComponent as BellWithNotification } from "../../assets/bellWithNoti.svg";
import { getDocActualUser } from "../../firebase/firebase";
import { signOutUser } from "../../firebase/firebase";
import { useNavigate } from "react-router-dom";
import { getNotifications } from "../../firebase/firebase";

const NavDropdownComponent = (uid) => {
  const [username, setUsername] = useState({});
  const [isLoading, setLoading] = useState(false);
  const [notification, setNotification] = useState();

  
  let navigate = useNavigate();

  const actualUser = async () => {
    await getDocActualUser(uid.uid).then((resp) => {
      setUsername(resp.displayName);
      setLoading(true);
    });
  };

  // const getAllNotification = async () => {
  //   await getNotifications(uid.uid).then((resp) => {
  //     setNotification(resp)
  //     setLoading(true);
  //   });
  // };

  useEffect(() => {
    actualUser();
    //getAllNotification();
  }, []);

  

  const signOutHandler = async () => {
    await signOutUser();
    navigate("/")
    
  };
  const value = 1;

  return isLoading ? (
    <Navigation>
      <Link to="/new">
        <Button id="Button-Nav">Create Post</Button>
      </Link>
      <Link to="/notification">
        <Button  id="Bell-button">
          {value===1 ? <BellIcon className="svg-icon" /> : <BellWithNotification className="svg-icon" />}
        </Button>
      </Link>
      <NavDropdown title={username}>
        <NavDropdown.Item href="/perfil">Veja seu perfil</NavDropdown.Item>
        <NavDropdown.Divider />
        <NavDropdown.Item onClick={signOutHandler}>Sair</NavDropdown.Item>
      </NavDropdown>
    </Navigation>
  ) : (
    ""
  );
};

export default NavDropdownComponent;
