import { Fragment } from "react";
import { Outlet } from "react-router-dom";
import Navbar from "react-bootstrap/Navbar";
import Container from "react-bootstrap/Container";
import { UserContext } from "../../context/user.context";
import { useContext } from "react";
import NavDropdownComponent from "../../components/nav-component-dropdown/nav.component";
import { Link } from "react-router-dom";

import "bootstrap/dist/css/bootstrap.min.css";

import "./navigation.styles.scss";

const Nav = () => {
  const { currentUser } = useContext(UserContext);

  return currentUser ? (
    <Fragment>
      <Navbar id="Navbar">
        <Container>
          <Link id="Logo" to= "/home"> Portal Estgoh </Link>
          <Navbar.Toggle />
          <Navbar.Collapse className="justify-content-end">
            <NavDropdownComponent uid={currentUser.uid} />
          </Navbar.Collapse>
        </Container>
      </Navbar>
      <Outlet />
    </Fragment>
  ) : (
    ""
  );
};

export default Nav;

// {
//   /* <Link className="nav-link" to="/home">
// Portal Estgoh
// </Link> */
// }
