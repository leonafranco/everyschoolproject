import Container from "react-bootstrap/Container";
import Card from "react-bootstrap/Card";
import NotificationMenuItem from "../notification-menu-item/notification-menu-item.component";
import { Fragment } from "react";

const NotificationCard = ({notification}) => {


  return (
    <Fragment>
    {Object.keys(notification).map((key) => (
      <Fragment key={key}>
        <NotificationMenuItem
          actualUser={notification[key].actualUser}
          check={notification[key].check}
          currentTime={notification[key].currentTime}
          docId={notification[key].docId}
          postOwner={notification[key].postOwner}
          text={notification[key].text}
          notificationId={key}
          />
      </Fragment>
    ))}
    </Fragment> 
  );
};

export default NotificationCard;