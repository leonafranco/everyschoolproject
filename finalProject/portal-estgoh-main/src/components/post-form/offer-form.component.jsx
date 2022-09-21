import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { useContext, useState } from "react";
import {
  addCollectionAndDocuments,
  uploadPhoto
} from "../../firebase/firebase";
import { UserContext } from "../../context/user.context";
import defaultPicLogo from "./default.jpeg"


const defaultFormFieldsOffer = {
  titleName: "",
  companyName:"",
  location: "",
  workplaceType:"",
  employmentType:"",
  uuid: "",
  currentTime: "",
  text:"",
  companyPic: ""
};

const OfferForm = () => {
  const { currentUser } = useContext(UserContext);

  const [formFieldsOffer, setFormFieldsOffer] = useState(defaultFormFieldsOffer);
  const { titleName, companyName, location, text } = formFieldsOffer;

  const handleChangeOffer = (event) => {
    const { name, value } = event.target;

    setFormFieldsOffer({ ...formFieldsOffer, [name]: value });
  };

  const handlePicChange = (event) => {
    formFieldsOffer.companyPic = event.target.files[0];
  };

  const resetFormFields = () => {
    setFormFieldsOffer(defaultFormFieldsOffer);
  };
  
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      if (formFieldsOffer.companyPic)
        await uploadPhoto(formFieldsOffer.companyPic, companyName);
      formFieldsOffer.uuid = currentUser.uid
      formFieldsOffer.currentTime = Date.now();
      formFieldsOffer.workplaceType = document.getElementById("workplaceTypeFormID").value;
      formFieldsOffer.employmentType = document.getElementById("employmentTypeFormID").value;
      await addCollectionAndDocuments("Offer", formFieldsOffer);
      resetFormFields();
    } catch (error) {}

    function timeout() {
      return new Promise( res => setTimeout(res, 1000) );
  }
    await timeout()
    window.location.reload() 
  };
  
  return (
        <Container>
        <Row>
        <Col xs={2}></Col>
        <Col xs={5}>
            <Form onSubmit={handleSubmit}>
            <Form.Group className="mb-3" controlId="formTitle">
                <Form.Label>Título do trabalho</Form.Label>
                <Form.Control onChange={handleChangeOffer} required value={titleName} name="titleName" type="title" placeholder="Nome do Trabalho" />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formCompany">
                <Form.Label>Empresa</Form.Label>
                <Form.Control onChange={handleChangeOffer} required value={companyName} name="companyName" type="company" placeholder="Nome da Empresa" />
            </Form.Group>
            <Form.Group controlId="formFile" className="mb-3">
                  <Col>
                    <img
                      width="50px"
                      height="50px"
                      src={defaultPicLogo}
                      alt="company name profile" />
                  </Col>
                  <Col>
                    <Form.Control onChange={handlePicChange} required type="file" />
                  </Col>
                </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Localização</Form.Label>
                <Form.Control onChange={handleChangeOffer} required value={location} name="location" type="location" placeholder="Localização" />
            </Form.Group>
            <Form.Group className="mb-3" controlId="workplaceTypeFormID">
            <Form.Label>Tipo de modo de trabalho</Form.Label>
                <Form.Select aria-label="Default select example">
                <option value="Presencial">Presencial</option>
                <option value="Híbrido">Híbrido</option>
                <option value="Remoto">Remoto</option>
                </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3" controlId="employmentTypeFormID">
            <Form.Label>Tipo de trabalho</Form.Label>
                <Form.Select aria-label="Default select example">
                <option value="Full-Time">Full-Time</option>
                <option value="Part-time">Part-time</option>
                <option value="Contractual">Contractual</option>
                <option value="Temporário">Temporário</option>
                <option value="Voluntáriado">Voluntáriado</option>
                <option value="Estágio">Estágio</option>
                </Form.Select>
            </Form.Group>
            <Form.Group
              className="mb-3"
              controlId="exampleForm.ControlTextarea1"
            >
              <Form.Label>Ensira uma descrição do Trabalho</Form.Label>
              <Form.Control
                required
                onChange={handleChangeOffer}
                value={text}
                name="text"
                as="textarea"
                rows={10}
              />
            </Form.Group>
            <Button variant="primary" type="submit">
                Submit
            </Button>
            </Form>
        </Col>
        </Row>
        </Container>
  ) 
};

export default OfferForm;




