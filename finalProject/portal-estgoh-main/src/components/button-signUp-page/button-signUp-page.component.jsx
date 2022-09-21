import "./button-signUp-page.styles.scss";
import Button from "react-bootstrap/Button";

const BUTTON_TYPE_OBJECT = {
  google: "google-sign-in",
  regular: "regular-button",
};

const ButtonSignInPage = ({ children, buttonType, ...otherProps }) => {
  return (
    <Button
      className={`button-container ${BUTTON_TYPE_OBJECT[buttonType]}`}
      {...otherProps}
    >
      {children}
    </Button>
  );
};

export default ButtonSignInPage;
