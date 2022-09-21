import React, { Fragment, useContext } from "react";
import MenuItem from "../menu-item/menuItem.component";

import { PostContext } from "../../context/posts.context";

const Directory = () => {
  const { postsMap } = useContext(PostContext);
  return (
    <Fragment>
      {Object.keys(postsMap).map((key) => (
        <Fragment key={key}>
          <div className="directory-pub">
            <MenuItem
              currentTime={postsMap[key].currentTime}
              text={postsMap[key].text}
              docId={key}
              likes={postsMap[key].likes}
              uuid={postsMap[key].uuid}
              type={"POST"}
            />
          </div>
        </Fragment>
      ))}
    </Fragment>
  );
};

export default Directory;
