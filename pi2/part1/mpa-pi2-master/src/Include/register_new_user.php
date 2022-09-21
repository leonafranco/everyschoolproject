<?php
namespace src\Include;

require_once('../DAO/User.php');
require_once('Mail.php');
use src\DAO\User;
use src\Include\Mail;

$userDAO = new User();
$mail = new Mail();

if(isset($_POST['name']) && isset($_POST['username']) && isset($_POST['email']) && isset($_POST['password'])) {
    $name = $_POST['name'];
    $username = $_POST['username'];
    $email = $_POST['email'];
    $password = sha1($_POST['password']);
    $repeatPassword = sha1($_POST['repeatPassword']);

    if($password == $repeatPassword) {
        if(strlen($_FILES['photo']['name']) == 0) {
            $defaultPhoto = "default-profile.jpeg";
            $userDAO->createNewUser($name, $username, $email, $password, $defaultPhoto);
            $mail->sendMail($email, $username);
            if($mail == true) {
                echo "<script type='javascript'>alert('Verifique o seu email para ativar sua conta!');";
            }
        }else {
            // $image = $_POST['pic'];
            // //Stores the filename as it was on the client computer.
            $imagename = $_FILES['photo']['name'];
            // //Stores the filetype e.g image/jpeg
            // $imagetype = $_FILES['pic']['type'];
            // //Stores any error codes from the upload.
            // $imageerror = $_FILES['pic']['error'];
            // //Stores the tempname as it is given by the host when uploaded.
             $imagetemp = $_FILES['photo']['tmp_name'];
        
            //The path you wish to upload the image to
            $imagePath = "../picture/";
        
            if(is_uploaded_file($imagetemp)) {
                if(move_uploaded_file($imagetemp, $imagePath . $imagename)) {
                    $userDAO->createNewUser($name, $username, $email, $password, $imagename);
                    $mail->sendMail($email, $username);
                    header("Location: http://localhost:8082/src/View/homepage.php");
                    die();
                }
                else {
                    echo "Failed to move your image.";
                }
            }
            else {
                echo "Failed to upload your image.";
            }
        }
    }else {
        header('Location:http://localhost:8082/src/view/register.php?error=true');
    }
}



?>
