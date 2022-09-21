import { Fragment, useState, useEffect } from "react";
import MenuItem from "../menu-item/menuItem.component";
import {getComment} from "../../firebase/firebase"
import { useParams } from "react-router-dom";

export const CommentPostCard = (postID, uuid) => {

  const { docId } = useParams();
  
  const [comment, setComment] = useState();
  const [isLoading, setLoading] = useState(false);

  //need reload
  useEffect(() => {
    const comments = async () => {
      await getComment(docId).then((resp) => {
        setComment(resp)
        setLoading(true);
      });
    };
    comments();
  }, [docId]);


  return isLoading ? (
    <Fragment>
    {Object.keys(comment).map((key) => (
      <Fragment key={key}>
        <div className="directory-pub">
          <MenuItem
            currentTime={comment[key].currentTime}
            text={comment[key].comment}
            docId={key}
            likes={comment[key].likes}
            uuid={comment[key].uuid}
            type={"comments"}
          />
        </div>
      </Fragment>
    ))}
    </Fragment> 
  ) : ("")
};
