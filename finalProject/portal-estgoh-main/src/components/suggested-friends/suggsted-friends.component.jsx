import React, { Fragment, useContext } from "react";
import SuggestedFriendsCard from "../suggested-friends-card/suggested-friends-card.component";

import { SuggestedFriendsContext } from "../../context/suggestedFriends.context";
import { UserContext } from "../../context/user.context";

const SuggestedFriends = () => {
  const { currentUser } = useContext(UserContext);
  const { suggestedFriendsMap } = useContext(SuggestedFriendsContext);

  const sliced = Object.keys(suggestedFriendsMap).slice(0, 3).reduce((result, key) => {
    result[key] = suggestedFriendsMap[key];

    return result;
}, {});  

  return (
    <Fragment>
      <h6>Suggestions for you</h6>
      {Object.keys(sliced).map((key) => (
        <Fragment key={key}>
          <div className="directory-pub">
            <SuggestedFriendsCard
              displayName={sliced[key].displayName}
              email={sliced[key].email}
              suggestedFriendUserId={key}
              currentUserId={currentUser.uid}
            />
          </div>
        </Fragment>
      ))}
    </Fragment>
  );
};

export default SuggestedFriends;
