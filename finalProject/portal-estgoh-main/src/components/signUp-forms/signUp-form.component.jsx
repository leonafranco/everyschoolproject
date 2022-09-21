import { useState } from "react";
import {
  createAuthUserWithEmailAndPassword,
  createUserDocumentFromAuth,
  signInWithGooglePopup,
} from "../../firebase/firebase";
import FormInput from "../form-input/form-input.component";
import Button from "../button-signUp-page/button-signUp-page.component";
import "./signUp-form.styles.scss";
import { useNavigate } from "react-router-dom";

const defaultFormFields = {
  displayName: "",
  email: "",
  password: "",
  confirmPassword: "",
};

const SignUpForm = () => {
  const [formFields, setFormFields] = useState(defaultFormFields);
  const { displayName, email, password, confirmPassword } = formFields;

  let navigate = useNavigate();


  const signInWithGoogle = async () => {
    const { user } = await signInWithGooglePopup();
    await createUserDocumentFromAuth(user);
    navigate("/home")
  };

  const resetFormFields = () => {
    setFormFields(defaultFormFields);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    if (password !== confirmPassword) {
      alert("password não estão certas");
      return;
    }

    try {
      const { user } = await createAuthUserWithEmailAndPassword(
        email,
        password
      );

      await createUserDocumentFromAuth(user, { displayName });
      resetFormFields();
      navigate("/home");

    } catch (error) {
      if (error.code === "auth/weak-password") {
        alert("Password deve conter + de 6 caracteres");
      }
      if (error.code === "auth/email-already-in-use") {
        alert("Email já está a ser utilizado");
      }
      console.log(error);
    }
  };

  const handleChange = (event) => {
    const { name, value } = event.target;

    setFormFields({ ...formFields, [name]: value });
  };

  return (
    <div>
      <h2>Novo no Portal Estgoh? Registe-se</h2>
      <form className="form-class" onSubmit={handleSubmit}>
        <FormInput
          label="Nome"
          type="text"
          required
          onChange={handleChange}
          name="displayName"
          value={displayName}
        ></FormInput>
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
        <FormInput
          label="Confirmar Password"
          type="password"
          required
          onChange={handleChange}
          name="confirmPassword"
          value={confirmPassword}
        ></FormInput>
        <div className="button-container">
          <Button buttonType="regular" type="submit">
            Registo
          </Button>
          <Button buttonType="google" onClick={signInWithGoogle}>
            Registo com Google
          </Button>
        </div>
      </form>
    </div>
  );
};

export default SignUpForm;
