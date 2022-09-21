<?php

namespace src\Include;

require_once('../DAO/User.php');

use src\DAO\User;


$userDAO = new User();

if (isset($_POST['username']) && isset($_POST['password'])) {

    $username = $_POST['username'];
    $password = sha1($_POST['password']);

    $result = $userDAO->auth($username, $password);


    if (isset($result)) {
        session_start();
        $_SESSION["id"] = $result['id'];
        $_SESSION["username"] = $username;
        header('Location:http://localhost:8082/src/view/homepage.php');
    } else {
        header('Location:http://localhost:8082/src/view/login.php?error=true');
    }
}
