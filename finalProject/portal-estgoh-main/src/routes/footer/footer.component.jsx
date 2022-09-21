import Container from "react-bootstrap/Container";

import "bootstrap/dist/css/bootstrap.min.css";
import "./footer.styles.scss";

const Footer = () => {
  return (
    <div id="div-footer">
      <Container id="container-footer">
        <p>
          Portal Estgoh - uma rede social que junta toda a comunidade
          estudantil.
        </p>
        <p>© 2022 Portal Estgoh.</p>
      </Container>
    </div>
  );
};

export default Footer;
