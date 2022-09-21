import Container from "react-bootstrap/Container";
import Card from "react-bootstrap/Card";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { useContext } from "react";
import { PostContext } from "../../context/posts.context";
import { useLocation, useParams } from "react-router";
import { useState } from "react";
import { addCollectionAndDocuments } from "../../firebase/firebase";
import { CommentPostCard } from "../comment-post/comment-post.component";
import { UserContext } from "../../context/user.context";
import 'moment/locale/pt'
var moment = require('moment');


const defaultFormFields = {
  comment: "",
  uuid: "",
  postid: "",
  currentTime: "",
  likes: 0,
  usersWhoLikedIt: [],
};
const CommentCard = () => {
  const { postsMap } = useContext(PostContext);
  const {currentUser} = useContext(UserContext)
  const location = useLocation();
  const username = location.state;
  const { docId } = useParams();


  const [formFields, setFormFields] = useState(defaultFormFields);
  const { comment } = formFields;

  const handleChange = (event) => {
    const { name, value } = event.target;

    setFormFields({ ...formFields, [name]: value });
  };

  const resetFormFields = () => {
    setFormFields(defaultFormFields);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {

      const notificationData = {
        actualUser: currentUser.uid,
        postOwner: postsMap[docId].uuid,
        docId: docId,
        text: "Comentou um post seu",
        check: false,
        currentTime: Date.now(),
      }
      await addCollectionAndDocuments("notification", notificationData);

      formFields.uuid = currentUser.uid;
      formFields.postid = docId;
      formFields.currentTime = Date.now();
      formFields.likes = 0;
      await addCollectionAndDocuments("comments", formFields);
      resetFormFields();
    } catch (error) {}

    function timeout() {
      return new Promise( res => setTimeout(res, 1000) );
  }
    await timeout()
    window.location.reload() 
  };


  return postsMap[docId] ? (
    <Container>
      <Card>
        <Card.Body>
          <Card.Title>
            <img
              id="Style-img"
              src={username.profilePic}
              width="50"
              height="50"
              alt="avatar"
            />
            {username.displayName}
            <h6>
              {moment(postsMap[docId].currentTime).locale("pt").format('DD MMMM YYYY HH:mm:ss')}
            </h6>
          </Card.Title>
          <Card.Text>{postsMap[docId].text}</Card.Text>
        </Card.Body>
        <Card.Body>
          <Card.Text>{postsMap[docId].likes} Likes</Card.Text>
        </Card.Body>
      </Card>
      <Card>
        <Card.Body>
          <Form onSubmit={handleSubmit}>
            <Form.Group
              className="mb-3"
              controlId="exampleForm.ControlTextarea1"
            >
              <h3>Discussion</h3>
              <Form.Control
                required
                onChange={handleChange}
                value={comment}
                name="comment"
                as="textarea"
                rows={5}
              />
            </Form.Group>
            <Button type="submit">Submit</Button>
          </Form>
        </Card.Body>
        <Card.Body>
        <CommentPostCard/>
        </Card.Body>
      </Card>
    </Container>
  ) : (
    ""
  );
};

export default CommentCard;
