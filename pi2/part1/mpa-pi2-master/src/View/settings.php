<?php 
 namespace src\View;
 require_once 'header.php';
 require_once('../DAO/User.php');
 require_once('../Model/UserModel.php');
 use src\DAO\User;
 use src\Model\UserModel;

 session_start();

 if(!isset($_SESSION['id'])) {
    header('Location:http://localhost:8082/');
 }
 
 if($_SESSION['lang'] == 'PT') {
    require_once '../i18n/i18n_pt.php';
  }
  if($_SESSION['lang'] == 'EN') {
    require_once '../i18n/i18n_en.php';
  }

 $id = $_SESSION["id"];
 $userDAO = new User();
 $userModel = new UserModel();
 $result = $userDAO->getUserData($id);

    function update_user($result, $userDAO) {
        if($_POST['name'] != '') {
            $result->setName($_POST['name']);
        }
        if($_POST['username'] != '') {
            $result->setUsername($_POST['username']);
        }
        if($_POST['email'] != '') {
            $result->setEmail($_POST['email']);
        }
        if($_POST['password'] != '') {
            $result->setPassword(sha1($_POST['password']));
        }
        if($_FILES['photo']['name'] != '') {
            $imagename = $_FILES['photo']['name'];
            $imagetemp = $_FILES['photo']['tmp_name'];
            $imagePath = "../picture/";
            if(is_uploaded_file($imagetemp)) {
                if(move_uploaded_file($imagetemp, $imagePath . $imagename)) {
                    $result->setPhoto($imagename);
                }
            }
        }
        $userDAO->updateUser($result);
    }
?>

<div class="container">
<form id="form-register" action="settings.php" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="InputName" class="form-label"><?php echo NAME ?></label>
        <input type="name" placeholder="<?php echo $result->getName(); ?>" name="name" class="form-control" id="InputNome" aria-describedby="nome">
    </div>
    <div class="mb-3">
        <label for="InputUsername" class="form-label"><?php echo USERNAME ?></label>
        <input type="username" placeholder="<?php echo $result->getUsername(); ?>" onblur="checkUsername(this.value)" name="username" class="form-control"  id="InputUsername" aria-describedby="username">
    </div>
    <div class="mb-3">
        <label for="InputEmail" class="form-label"><?php echo EMAIL ?></label>
        <input type="email" placeholder="<?php echo $result->getEmail(); ?>"  name="email" class="form-control" id="InputEmail" aria-describedby="emailHelp">
    </div>
    <div class="mb-3">
        <label for="InputPassword" class="form-label"><?php echo PASSWORD ?></label>
        <input type="password" name="password" class="form-control" id="InputPassword">
    </div>
    <img width="50px" height="50px" src="../picture/<?php echo $result->getPhoto(); ?>" alt="user profile" />
    <div class="mb-3">
        <label for="formFile" class="form-label"><?php echo PROFILEPICTURE ?></label>
        <input class="form-control" name="file" type="file" id="formFile">
    </div>
    <button type="submit" name="submit" class="btn btn-primary">Submit</button>
    </form>
</div>


<?php
 if($_SERVER['REQUEST_METHOD']=='POST') {
        update_user($result, $userDAO);
    }

require_once 'footer.php';?>