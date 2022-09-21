import React, { useState, useEffect, useContext } from "react";
import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/Button";
import { Link } from "react-router-dom";
import { getDocActualUser, addCollectionAndDocuments, checkIfAlreadyNotified} from "../../firebase/firebase";
import { UserContext } from "../../context/user.context";
import {
  updateLikes,
  checkIfUsersAlreadyLikedIt,
} from "../../firebase/firebase";
import Col from "react-bootstrap/Col"
import Row from "react-bootstrap/Row"
import "./menuItem.styles.scss";
import ButtonRemovePost from "../button-remove-post/button-remove-post.component";
import 'moment/locale/pt'
var moment = require('moment');



const MenuItem = ({ displayName, text, currentTime, docId, likes, uuid, type }) => {
  const { currentUser } = useContext(UserContext);

  const [username, setUsername] = useState();
  const [isLoading, setLoading] = useState(false);

  const actualUser = async () => {
    await getDocActualUser(uuid).then((resp) => {
      setUsername(resp);
      setLoading(true);
    });
  };

  useEffect(() => {
    actualUser();
  }, []);

  const handleLikeButton = async () => {
    let isAlreadyLikedIt = await checkIfUsersAlreadyLikedIt(currentUser.uid, type ,docId);
    let isAlreadyNotified = await checkIfAlreadyNotified(currentUser.uid, docId)

    await updateLikes(docId, isAlreadyLikedIt, type, currentUser.uid);

    const notificationData = {
      actualUser: currentUser.uid,
      postOwner: uuid,
      docId: docId,
      text: "Gostou do seu Post",
      check: false,
      currentTime: Date.now(),
    }
 
    if(isAlreadyLikedIt) {
      setLikesValue(likesValue - 1)
    } else{
      setLikesValue(likesValue + 1)
      if(!isAlreadyNotified){
        await addCollectionAndDocuments("notification", notificationData);
      }
    }
  };


  const [likesValue, setLikesValue] = useState(likes);


  var m = moment(currentTime).locale("pt").format('DD MMMM YYYY HH:mm:ss')
  
  return isLoading ? (
    <Card id="Menu-item-card">
      <Card.Body>
        <Card.Title>
          <Row>
            <Col xs={10}>
              <img
                id="Style-img"
                src={username.profilePic}
                width="50"
                height="50"
                alt="avatar"
              />
              {username.displayName}{" "}
              <h6>{m}</h6>
            </Col>
            <Col id="DeleteButton" xs={2}>
              <ButtonRemovePost logedInUid={currentUser.uid} postOwnerUid={uuid} postID={docId} type={"POST"}/>
            </Col>
          </Row>
        </Card.Title>
        <Card.Text>{text}</Card.Text>
        <Button onClick={handleLikeButton} id="Button-card">
          {likesValue} Likes
        </Button>
        <Link state={username} to={docId}>
          {type==="comments" ? "" :  <Button id="Button-card">Comments</Button>}
        </Link>
      </Card.Body>
    </Card>
  ) : (
    ""
  );
};

export default MenuItem;
