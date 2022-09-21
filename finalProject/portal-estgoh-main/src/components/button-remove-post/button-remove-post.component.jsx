import Button from "react-bootstrap/Button";
import { removePost } from "../../firebase/firebase";


import "./button-remove-post.component.styles.scss"

const ButtonRemovePost = ({postOwnerUid, logedInUid, postID, type}) => {

    const handleRemovePostsButton = async () => {
      if(window.confirm("Excluir mensagem? \nEsta ação não pode ser desfeita.")) {
        await removePost(postID, type)
        window.location.reload() 
      }else{

      };
      }

    return postOwnerUid === logedInUid ? (
         <Button onClick={handleRemovePostsButton} id="Remove-Posts">X</Button>
    ) : " ";
  };
  
  export default ButtonRemovePost;