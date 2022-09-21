<?php
use src\DAO\User;
use src\Include\Mail;
require_once('../DAO/User.php');
require_once('Mail.php');


$userDAO = new User();
$mail = new Mail();


if($_SERVER["REQUEST_METHOD"] === "GET") {
    $userDAO = new User();

    $user = $userDAO->getUserDataByUsername($_GET['username']);
    $result = $userDAO->activateAccount($_GET['username']);


    if($user->getStardate() > time() + 172800) {
        $mail->sendMail($user->getEmail(), $user->getUsername());
    }else {
        session_start();
        $_SESSION["username"] = $_GET['username'];
        $_SESSION["id"] = $user->getId();
    
        header("Location: http://localhost:8082/src/View/homepage.php");
        die();
    
        return $result;
    }
}
?>



