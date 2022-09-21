<?php
namespace src\Include;

require_once('../DAO/Collection.php');
use src\DAO\Collection;
require_once('../DAO/User.php');
require_once('../Model/UserModel.php');
use src\DAO\User;
use src\Model\UserModel;
session_start();
$id = $_SESSION["id"];
$userDAO = new User();
$user = $userDAO->getUserData($id);

$collectionDAO = new Collection();
if(isset($_POST['name']) && isset($_POST['observation'])) {
    $name = $_POST['name'];
    $observation = $_POST['observation'];
    $result = $collectionDAO->createNewCollection($name, $observation, $user->getId());
    header("Location: http://localhost:8082/src/View/homepage.php");
    die();
}

?>
