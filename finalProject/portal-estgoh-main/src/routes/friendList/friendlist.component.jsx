import { useContext } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import "bootstrap/dist/css/bootstrap.min.css";
import HomePageLeftMenu from "../../components/home-page-left-menu/home-page-left-menu.component";
import SuggestedFriends from "../../components/suggested-friends/suggsted-friends.component";
import Footer from "../footer/footer.component";
import { SuggestedFriendsContext } from "../../context/suggestedFriends.context";
import { UserContext } from "../../context/user.context";
import FriendlistCard from "../../components/friendslist-card/friendlist-card.component";
import Form from "react-bootstrap/Form"
import { useState } from "react";

const Friendlist = () => {
    const { currentUser } = useContext(UserContext);
    const { suggestedFriendsMap } = useContext(SuggestedFriendsContext);
    
    const [search, setSearch] = useState("");
  
    const handleChange = (event) => {
      setSearch(event.target.value)
    }

    return (
    <><Container>
      <Row>
        <Col>
          <HomePageLeftMenu />
        </Col>
        <Col xs={6}>
            <Form.Group className="mb-3" controlId="formTitle">
            <Form.Label>
              <h3>Pesquisa um nome de utilizador</h3>
            </Form.Label>
            <Form.Control onChange={handleChange}  value={search} name="search" type="search" />
            </Form.Group>
         <FriendlistCard suggestedFriendsMap={suggestedFriendsMap} term={search}/>
        </Col>
        <Col>
          <SuggestedFriends />
        </Col>
      </Row>
    </Container><Footer /></>
  );
};

export default Friendlist;
