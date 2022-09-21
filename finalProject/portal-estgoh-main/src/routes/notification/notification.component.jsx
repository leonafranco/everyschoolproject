import Col from "react-bootstrap/Col"
import Container from "react-bootstrap/Container"
import Row from "react-bootstrap/Row"
import HomePageLeftMenu from "../../components/home-page-left-menu/home-page-left-menu.component";
import NotificationCard from "../../components/notification-card/notification-card.component";
import Footer from "../footer/footer.component";
import { UserContext } from "../../context/user.context";
import { useContext, useState, useEffect } from "react"
import { getNotifications } from "../../firebase/firebase";


const Notification = () => {
    const {currentUser} = useContext(UserContext)

    const [notification, setNotification] = useState();
    const [isLoading, setLoading] = useState(false);
  
    const getAllNotification = async () => {
      await getNotifications(currentUser.uid).then((resp) => {
        setNotification(resp)
        setLoading(true);
      });
    };
  
    useEffect(() => {
      getAllNotification();
    },[]);

    return isLoading ? (
      <><Container>
        <Row>
          <Col>
            <HomePageLeftMenu />
          </Col>
          <Col xs={7}>
            <NotificationCard notification={notification}/>
          </Col>
          <Col>
          </Col>
        </Row>
      </Container><Footer /></>
    ) : (" ");
  };
  
  export default Notification;