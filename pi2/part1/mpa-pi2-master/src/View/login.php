<?php 
$title = "Sistema de Coleção de caricas";
require_once 'header.php';
session_start();

if($_SESSION['lang'] == 'PT') {
  require_once '../i18n/i18n_pt.php';
}
if($_SESSION['lang'] == 'EN') {
  require_once '../i18n/i18n_en.php';
}


 ?>
<style>
  <?php include 'CSS/login.css'; ?>
</style>

<div class="container">
<h1><?php echo LOGIN ?></h1>
    <form id="form-login" action="../Include/login_user.php" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label"><?php echo USERNAME ?></label>
        <input type="username" name="username" class="form-control" id="usernameInput" aria-describedby="username">
    </div>
    <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label"><?php echo PASSWORD ?></label>
        <input type="password" name="password" class="form-control" id="exampleInputPassword1">
    </div>
    <button type="submit" class="btn btn-primary"><?php echo LOGIN ?></button>
    </form>
</div>
<?php
if($_SERVER["REQUEST_METHOD"] === "GET") {
    if(isset($_GET['error']))
        echo "<script>alert('Erro ao inserir dados')</script>";
}
?>


<script src="../script/scripts.js"></script>
<?php require_once 'footer.php';?>