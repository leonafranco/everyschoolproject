import { useState } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import PostForm from "../../components/post-form/post-form.component";
import Footer from "../footer/footer.component";
import { ButtonGroup, ToggleButton } from "react-bootstrap";

const CreatePost = () => {

  const [form, setForm] = useState("1");

  const forms = [
    { name: 'Postagem', value: '1' },
    { name: 'Oferta', value: '2' },
  ];

  return (
    <><Container>
      <ButtonGroup>
        {forms.map((radio, idx) => (
          <ToggleButton
            key={idx}
            id={`radio-${idx}`}
            type="radio"
            variant={idx % 2 ? 'outline-success' : 'outline-danger'}
            name="radio"
            value={radio.value}
            checked={form === radio.value}
            onChange={(e) => setForm(e.currentTarget.value)}
          >
            {radio.name}
          </ToggleButton>
        ))}
      </ButtonGroup>
      <Row>
        <PostForm form={form} />
      </Row>
    </Container><Footer /></>
  );
};

export default CreatePost;
