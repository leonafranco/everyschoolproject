import  Container from "react-bootstrap/Container";
import  Col  from "react-bootstrap/Col";
import Row from "react-bootstrap/Row"
import Card from "react-bootstrap/Card"
import 'moment/locale/pt'
import { useEffect, useState, useContext} from "react";
import { getDocActualUser,changeCheck } from "../../firebase/firebase";
import "./notification-menu-item.component.styles.scss"
import { PostContext } from "../../context/posts.context";
var moment = require('moment');


const NotificationMenuItem = ({actualUser, check, currentTime, docId, postOwner, text, notificationId}) => {

  let docText;
  const { postsMap } = useContext(PostContext);
  Object.keys(postsMap).map((key) => {
    if(key === docId){
      docText = postsMap[key].text
    }
  })


    const [userWholikedit, setUserWholikedit] = useState();
    const [isLoading, setLoading] = useState(false);
    

    let time = moment(currentTime).locale("pt").format('DD MMMM YYYY');

    useEffect(() => {
        const comments = async () => {
          await getDocActualUser(actualUser).then((resp) => {
            setUserWholikedit(resp)

            setLoading(true);
          });
          await changeCheck(notificationId)
        };
        comments();
      }, []);
      
      let idName;
      if(check) {
        idName = "backgroundWhite"
      }else{
        idName = "backgroundBlue"
      }


    return isLoading ? (
      <Container>
        <Card id={idName}>
            <Row>
                <Col>
                    <Card.Body>
                    <Card.Title>
                        <img id="Style-img" src={userWholikedit.profilePic} width={50} height={50} alt="" />
                        {userWholikedit.displayName} {text} 
                    </Card.Title >
                    <p className="text-muted"> {docText}</p>
                    </Card.Body>
                </Col>
                
            </Row>
        </Card>
      </Container>
    ) : (" ");
  };
  
  export default NotificationMenuItem;