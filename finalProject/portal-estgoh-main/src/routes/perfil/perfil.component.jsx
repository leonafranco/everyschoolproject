import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import HomePageLeftMenu from "../../components/home-page-left-menu/home-page-left-menu.component";
import Card from "react-bootstrap/Card";
import Form from "react-bootstrap/Form";
import { useContext, useState, useEffect } from "react";
import { UserContext } from "../../context/user.context";
import Button from "react-bootstrap/Button";
import Footer from "../footer/footer.component";

import {
  getDocActualUser,
  uploadPhoto,
  updateDocUsers,
} from "../../firebase/firebase";

const Perfil = () => {
  const { currentUser } = useContext(UserContext);

  const [username, setUsername] = useState();
  const [isLoading, setLoading] = useState(false);

  const defaultFormFields = {
    displayName: "",
    email: currentUser.email,
    curso: "",
    profissao: "",
    profilePic: "",
  };

  const actualUser = async () => {
    await getDocActualUser(currentUser.uid).then((resp) => {
      setUsername(resp);
      setLoading(true);
    });
  };

  useEffect(() => {
    actualUser();
  }, []);

  const [formFields, setFormFields] = useState(defaultFormFields);
  const { displayName, email, curso } = formFields;

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (formFields.displayName.length === 0) {
      formFields.displayName = username.displayName;
    }
    formFields.profissao = document.getElementById("profissaoFormID").value;
    try {
      if (typeof(formFields.profilePic) === "object")
        await uploadPhoto(formFields.profilePic, currentUser.uid);
      
      await updateDocUsers(formFields, currentUser.uid);
    } catch (error) {}

    function timeout() {
      return new Promise( res => setTimeout(res, 1000) );
  }
    await timeout()
    window.location.reload() 
  };

  const handlePicChange = (event) => {
    formFields.profilePic = event.target.files[0];
  };

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormFields({ ...formFields, [name]: value });
  };

  return isLoading ? (
    <><Container id="Container">
      <Row>
        <Col xs={3}>
          <HomePageLeftMenu />
        </Col>
        <Col xs={7}>
          <Row>
            <Form onSubmit={handleSubmit}>
              <Card.Body>
                <Card.Title>Utilizador</Card.Title>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                  <Form.Label>Nome</Form.Label>
                  <Form.Control
                    onChange={handleChange}
                    name="displayName"
                    value={displayName}
                    placeholder={username.displayName} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                  <Form.Label>Email</Form.Label>
                  <Form.Control
                    type="email"
                    placeholder={currentUser.email} //need to be fixed
                    onChange={handleChange}
                    name="email"
                    value={email} />
                </Form.Group>
                <Form.Group controlId="formFile" className="mb-3">
                  <Form.Label>Imagem de perfil</Form.Label>
                  <Col>
                    <img
                      width="50px"
                      height="50px"
                      src={username.profilePic}
                      alt="user profile" />
                  </Col>
                  <Col>
                    <Form.Control onChange={handlePicChange} type="file" />
                  </Col>
                </Form.Group>
              </Card.Body>

              <Card.Body>
                <Card.Title>Informação da Estgoh</Card.Title>

                <Form.Group className="mb-3" controlId="formBasicEmail">
                  <Form.Label>Curso</Form.Label>
                  <Form.Control
                    onChange={handleChange}
                    name="curso"
                    value={curso} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="profissaoFormID">
                  <Form.Select aria-label="Profissão">
                    <option>Profissão</option>
                    <option value="Estudante">Estudante</option>
                    <option value="Formado">Formado</option>
                    <option value="Professor">Professor</option>
                  </Form.Select>
                </Form.Group>
                <div className="d-grid gap-2">
                  <Button variant="primary" size="lg" type="submit">
                    Guardar informação pessoal
                  </Button>
                </div>
              </Card.Body>
            </Form>
          </Row>
        </Col>
      </Row>
    </Container><Footer /></>
  ) : (
    ""
  );
};

export default Perfil;
