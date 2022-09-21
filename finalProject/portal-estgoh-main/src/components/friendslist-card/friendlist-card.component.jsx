import React, { Fragment, useContext } from "react";

import SuggestedFriendsCard from "../suggested-friends-card/suggested-friends-card.component";
import { UserContext } from "../../context/user.context";

const FriendlistCard = ({suggestedFriendsMap, term}) => {
    const { currentUser } = useContext(UserContext);

    Object.keys(suggestedFriendsMap).map((key) => {
        if(key === currentUser.uid)
          delete suggestedFriendsMap[key]
    })

    let newSuggestedFriendsMap = {}
    Object.keys(suggestedFriendsMap).forEach((key) => {
      if(suggestedFriendsMap[key].displayName.toLowerCase().indexOf(term.toLowerCase()) !== -1){
        newSuggestedFriendsMap[key] = suggestedFriendsMap[key]
      }
  })
  
  return (
    <Fragment>
      {Object.keys(newSuggestedFriendsMap).map((key) => (
        <Fragment key={key}>
          <div className="directory-pub">
            <SuggestedFriendsCard
              displayName={newSuggestedFriendsMap[key].displayName}
              email={newSuggestedFriendsMap[key].email}
              suggestedFriendUserId={key}
              currentUserId={currentUser.uid}
            />
          </div>
        </Fragment>
      ))}
    </Fragment>
  );
};

export default FriendlistCard;
