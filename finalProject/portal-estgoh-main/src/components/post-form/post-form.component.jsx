import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { useContext, useState } from "react";
import {
  addCollectionAndDocuments,
} from "../../firebase/firebase";
import { UserContext } from "../../context/user.context";
import OfferForm from "./offer-form.component";

const defaultFormFields = {
  text: "",
  uuid: "",
  currentTime: "",
  likes: 0,
  usersWhoLikedIt: [],
};

const PostForm = ({form}) => {
  const { currentUser } = useContext(UserContext);

  const [formFields, setFormFields] = useState(defaultFormFields);
  const { text } = formFields;

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
      formFields.currentTime = Date.now();
      formFields.uuid = currentUser.uid;
      formFields.likes = 0;
      await addCollectionAndDocuments("POST", formFields);
      resetFormFields();
    } catch (error) {}

    function timeout() {
      return new Promise( res => setTimeout(res, 1000) );
  }
    await timeout()
    window.location.reload() 
  };
  
  return form === "1" ? (
    <Container>
      <Row>
        <Col xs={2}></Col>
        <Col xs={5}>
          <Form onSubmit={handleSubmit}>
            <Form.Group
              className="mb-3"
              controlId="exampleForm.ControlTextarea1"
            >
              <Form.Label>Crie uma nova postagem</Form.Label>
              <Form.Control
                required
                onChange={handleChange}
                value={text}
                name="text"
                as="textarea"
                rows={5}
              />
            </Form.Group>
            <Button type="submit">Submit</Button>
          </Form>
        </Col>
      </Row>
    </Container>
  ) : (<OfferForm/>);
};

export default PostForm;
