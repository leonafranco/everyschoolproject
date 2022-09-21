import React from "react";
import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/Button";
import { updateFollowers, isFollowingThisPerson } from "../../firebase/firebase";
import { Container } from "react-bootstrap";


const SuggestedFriendsCard = ({
  displayName,
  email,
  suggestedFriendUserId,
  currentUserId,
}) => {

  const handleFollowUser = async () => {
    const result = await isFollowingThisPerson(currentUserId, suggestedFriendUserId)
    await updateFollowers(suggestedFriendUserId, currentUserId, result);
  };


  return  (
      <Container>
          <Card id="Menu-item-card">
            <Card.Body>
              <Card.Title>{displayName}</Card.Title>
              <Card.Text>{email}</Card.Text>
              <Button id="btn-followMe" onClick={handleFollowUser}>
                Follow Me!
              </Button>
            </Card.Body>
          </Card>
      </Container>
  );

};

export default SuggestedFriendsCard;
