<?php
namespace src\Include;

require_once('../DAO/Category.php');
use src\DAO\Category;
require_once('../DAO/User.php');
use src\DAO\User;
session_start();
$uid = $_SESSION["id"];
$userDAO = new User();
// $result = $userDAO->getUserData($username);

$categoryDAO = new Category();


if(isset($_POST['name']) && isset($_POST['observation'])) {
    $name = $_POST['name'];
    $observation = $_POST['observation'];
    $categoryDAO->createNewCategory($name, $observation, $uid);
    header("Location: http://localhost:8082/src/View/homepage.php");
    die();
}

?>
