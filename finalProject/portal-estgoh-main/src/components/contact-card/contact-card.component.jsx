import Container from "react-bootstrap/Container";
import Card from "react-bootstrap/Card";

const ContactCard = () => {
  return (
    <Container>
      <Card>
        <Card.Body>
          <Card.Title>Contacto</Card.Title>
          <Card.Text>Email: leonardomf96@gmail.com</Card.Text>
          <Card.Text>Twitter: @LMenezesFranco</Card.Text>
          <Card.Text>
            Caso encontra algum bug por favor envie um email.
          </Card.Text>
          <Card.Text>
            Caso queira alguma nova feature entre em contato comigo!
          </Card.Text>
          <Card.Text>
            Ajude o Portal Estgoh a crescer! <a href="https://github.com/leonafranco/portal-estgoh">https://github.com/leonafranco/portal-estgoh</a> 
          </Card.Text>
        </Card.Body>
      </Card>
    </Container>
  );
};

export default ContactCard;
