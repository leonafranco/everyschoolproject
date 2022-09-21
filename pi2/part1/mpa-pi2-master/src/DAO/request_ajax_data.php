<?php
use src\DAO\User;
require_once('../DAO/User.php');

if($_SERVER["REQUEST_METHOD"] === "GET") {
    $userDAO = new User();

    $result = $userDAO->checkUsername($_GET['username']);

    echo $result;

    return $result;
}
?>



