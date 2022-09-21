import  Container from "react-bootstrap/Container";
import  Col  from "react-bootstrap/Col";
import Row from "react-bootstrap/Row"
import Card from "react-bootstrap/Card"
import "./job-listing-card.component.styles.scss";
import{useContext} from "react"
import { UserContext } from "../../context/user.context";
import 'moment/locale/pt'
var moment = require('moment');

const JobListingCard = ({setOfferToView, offer, postID}) => {

    const {currentUser} = useContext(UserContext)
    const sendProps = () => {
        setOfferToView([offer.companyName, offer.titleName, offer.currentTime, offer.employmentType, offer.location, offer.text, offer.workplaceType, currentUser.uid, offer.uuid, postID]);
    }

    let time = moment(offer.currentTime).locale("pt").format('DD MMMM YYYY')

    return (
      <Container>
        <Card id="CardID">
            <Row>
                <Col xs={3}>
                    <img id="CompanyImg" width={100} height={100} src={offer.companyPic} alt="logo da empresa"></img>
                </Col>
                <Col xs={7}>
                    <Card.Body>
                    <Card.Title>
                        <a id="CompanyName" onClick={sendProps} className="stretched-link" >
                        {offer.titleName}
                        </a>
                    </Card.Title>
                    <Card.Text>
                        {offer.companyName}
                    </Card.Text>
                    <Card.Subtitle className="mb-2 text-muted">{offer.location}</Card.Subtitle>
                    </Card.Body>
                    <small className="text-muted">Last updated {time}</small>
                </Col>
            </Row>
        </Card>
      </Container>
    );
  };
  
  export default JobListingCard;