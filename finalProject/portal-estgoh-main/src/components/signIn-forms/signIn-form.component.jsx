import { useState } from "react";
import {
  signInUserWithEmailAndPassword,
  signInWithGooglePopup,
} from "../../firebase/firebase";
import { useNavigate } from "react-router-dom";
import FormInput from "../form-input/form-input.component";
import Button from "../button-signUp-page/button-signUp-page.component";

import "./signIn-form.styles.scss";

const defaultFormFields = {
  email: "",
  password: "",
};

const SignInForm = (props) => {

  let navigate = useNavigate();

  const [formFields, setFormFields] = useState(defaultFormFields);
  const { email, password } = formFields;


  const resetFormFields = () => {
    setFormFields(defaultFormFields);
  };
  const signInWithGoogle = async () => {
    await signInWithGooglePopup();
    navigate("/home")
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      await signInUserWithEmailAndPassword(email, password);
      resetFormFields();
      navigate("/home")
      

      
    } catch (error) {
      if (error.code === "auth/wrong-password")
        alert("Password ou Email incorretos");
      if (error.code === "auth/wrong-user")
        alert("Password ou Email incorretos");
      if (error.code === "auth/user-not-found")
        alert("Password ou Email incorretos");
        
      console.log(error)
    }
  };

  const handleChange = (event) => {
    const { name, value } = event.target;

    setFormFields({ ...formFields, [name]: value });
  };

  return props.trigger ? (
    <div className="popup">
      <div className="main">
        <h2>Entre no Portal</h2>
        <form onSubmit={handleSubmit}>
          <FormInput
            label="Email"
            type="email"
            required
            onChange={handleChange}
            name="email"
            value={email}
          ></FormInput>
          <FormInput
            label="Password"
            type="password"
            required
            onChange={handleChange}
            name="password"
            value={password}
          ></FormInput>
          <Button buttonType="regular" type="submit">
            Entrar
          </Button>
          <Button buttonType="google" onClick={signInWithGoogle}>
            Entrar com o Google
          </Button>
          <Button buttonType="regular" onClick={() => props.setTrigger(false)}>
            Fechar
          </Button>
        </form>
      </div>
    </div>
  ) : (
    ""
  );
};

export default SignInForm;
