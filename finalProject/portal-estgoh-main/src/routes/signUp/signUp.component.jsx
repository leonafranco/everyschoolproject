import "./signUp.styles.scss";
import SignUpForm from "../../components/signUp-forms/signUp-form.component";
import SignInForm from "../../components/signIn-forms/signIn-form.component";
import logo from "../../imgs/test.jpeg";
import ButtonSignInPage from "../../components/button-signUp-page/button-signUp-page.component";
import { useState } from "react";
import Container from "react-bootstrap/Container";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import Footer from "../footer/footer.component";

const SignUp = () => {
  const [buttonSignInForm, setButtonSignInForm] = useState(false);

  return (
    <><Container>
      <Row id="row-main-page">
        <Col id="logo-div">
          <img src={logo} alt=" logo" />
        </Col>
        <Col>
          <h1>De ESTGOH para a ESTGOH</h1>
          <SignUpForm />
          <br />
          <h2>Já tem conta?</h2>
          <ButtonSignInPage
            buttonType="regular"
            onClick={() => setButtonSignInForm(true)}
          >
            Entrar
          </ButtonSignInPage>
          <SignInForm
            trigger={buttonSignInForm}
            setTrigger={setButtonSignInForm} />
        </Col>
      </Row>
    </Container><Footer /></>
  );
};

export default SignUp;
