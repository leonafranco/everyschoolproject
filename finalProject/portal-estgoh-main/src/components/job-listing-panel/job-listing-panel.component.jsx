import  Container from "react-bootstrap/Container";
import  Row  from "react-bootstrap/Row";
import Col from "react-bootstrap/Col"
import Card from "react-bootstrap/Card"
import ButtonRemovePost from "../button-remove-post/button-remove-post.component";
import 'moment/locale/pt'
import { ReactComponent as WorkType } from "../../assets/workType.svg";
var moment = require('moment');

const JobListing = ({offerToView}) => {

  let time = moment(offerToView[2]).locale("pt").format('DD MMMM YYYY')




    return offerToView[0] ? (
      <Container>
        <Card>
          <Card.Body>
            <Row>
              <Col xs={10}>
                <Card.Title>{offerToView[1]}</Card.Title>
              </Col>
              <Col> 
                <ButtonRemovePost logedInUid={offerToView[7]} postOwnerUid={offerToView[8]} postID={offerToView[9]} type={"Offer"} />
              </Col>
            </Row>
            <Card.Subtitle className="mb-2 text-muted">{offerToView[0]}  ·  {offerToView[4]} ({offerToView[6]}) {time}</Card.Subtitle>
            <Card.Subtitle>
              <WorkType/> {offerToView[6]}
            </Card.Subtitle>
            <Card.Text>
              {offerToView[5]}
            </Card.Text>
          </Card.Body>
        </Card>
      </Container>
    ) : ("");
  };
  
  export default JobListing;