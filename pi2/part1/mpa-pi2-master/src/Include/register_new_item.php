<?php
namespace src\Include;

require_once('../DAO/Item.php');
use src\DAO\Item;
require_once('../DAO/User.php');
use src\DAO\User;
session_start();
$username = $_SESSION["username"];
$uid = $_SESSION["id"];
$userDAO = new User();

$itemDAO = new Item();


if(isset($_POST['name']) && isset($_POST['SKU']) && isset($_POST['descricao']) && isset($_POST['valor']) && isset($_POST['category'])) {
    $name = $_POST['name'];
    $observation = $_POST['descricao'];
    $sku = $_POST['SKU'];
    $valor = $_POST['valor'];
    $category = $_POST['category'];

    $result = $itemDAO->createNewItem($name, $observation, $sku, $valor, $uid, $category);

    header("Location: http://localhost:8082/src/View/homepage.php");
    die();
}

?>
