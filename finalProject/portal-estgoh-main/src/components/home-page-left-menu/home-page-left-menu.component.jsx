import Row from "react-bootstrap/Row";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import { Link } from "react-router-dom";
import { ReactComponent as HomeIcon } from "../../assets/home.svg";
import { ReactComponent as FriendsIcon } from "../../assets/friends.svg";
import { ReactComponent as ContactIcon } from "../../assets/contact.svg";
import { ReactComponent as JobsIcon } from "../../assets/jobs.svg";

import "./home-page-left-menu.component.styles.scss";

const HomePageLeftMenu = () => {
  return (
    <Container>
      <Link to="/home">
        <Row>
          <Button id="Button">
            <HomeIcon className="svg-icon" />
            Home
          </Button>
        </Row>
      </Link>
      <Link to ="/friendlist">
      <Row>
        <Button id="Button">
          <FriendsIcon className="svg-icon" />
          Amigos
        </Button>
      </Row>
      </Link>
      <Link to ="/offers">
      <Row>
        <Button id="Button">
          <JobsIcon className="svg-icon" />
          Ofertas
        </Button>
      </Row>
      </Link>
      <Link to="/contact">
        <Row>
          <Button id="Button">
            <ContactIcon className="svg-icon" />
            Contacto
          </Button>
        </Row>
      </Link>
    </Container>
  );
};

export default HomePageLeftMenu;
