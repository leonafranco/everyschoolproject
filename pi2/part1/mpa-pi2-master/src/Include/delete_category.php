<?php
use src\DAO\Category;
require_once('../DAO/Category.php');

$category = new Category();

if($_SERVER["REQUEST_METHOD"] === "GET") {
    $result = $category->deleteCategory($_GET['id']);
}
?>
