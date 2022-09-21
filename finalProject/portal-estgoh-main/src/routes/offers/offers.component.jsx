import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import JobListingPanel from "../../components/job-listing-panel/job-listing-panel.component";
import Footer from "../footer/footer.component";
import { useState } from "react";
import ConvertOffersMap from "../../components/convertOffersMap/convertOffersMap.component";

const Offers = () => {
  const [offerToView, setOfferToView] = useState([])


  return (
    <><Container>
      <Row>
        <Col xs={5}>
          <ConvertOffersMap setOfferToView={setOfferToView}/>
        </Col>
        <Col>
          <JobListingPanel offerToView={offerToView}/>
        </Col>
      </Row>
    </Container><Footer /></>

  );
};
 
export default Offers;
