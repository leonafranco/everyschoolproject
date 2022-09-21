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
  <?php include 'CSS/register.css'; ?>
</style>

<div class="container">
    <h1><?php echo CREATEACCOUNT ?></h1>
    <form id="form-register" action="../Include/register_new_user.php" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label"><?php echo NAME ?></label>
        <input type="nome" name="name" class="form-control" required id="InputNome" aria-describedby="nome">
    </div>
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label"><?php echo USERNAME ?></label>
        <input type="username" onblur="checkUsername(this.value)"  name="username" class="form-control" required id="InputUsername" aria-describedby="username">
    </div>
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label"><?php echo EMAIL ?></label>
        <input type="email"  name="email" class="form-control" required id="InputEmail" aria-describedby="emailHelp">
    </div>
    <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label"><?php echo PASSWORD ?></label>
        <input type="password" name="password" required class="form-control" id="InputPassword">
    </div>
    <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label"><?php echo REPEATPASSWORD ?></label>
        <input type="password" name="repeatPassword" required class="form-control" id="InputRepeatPassword">
    </div>
    <div class="mb-3">
        <label for="formFile" class="form-label"><?php echo PROFILEPICTURE ?></label>
        <input class="form-control" name="photo" type="file" id="formFile">
    </div>
    <button type="submit" class="btn btn-primary"><?php echo REGISTER ?></button>
    <button class="btn btn-primary" onclick="send_to_login_from_register()" ><?php echo GOLOGIN ?></button>
    </form>
</div>
<?php
if($_SERVER["REQUEST_METHOD"] === "GET") {
    if(isset($_GET['error']))
        echo "<script>alert('Passwords não são iguais')</script>";
}
?>



<script src="../script/scripts.js"></script>
<script src="../script/usernameAjax.js"></script>
<?php require_once 'footer.php';?>