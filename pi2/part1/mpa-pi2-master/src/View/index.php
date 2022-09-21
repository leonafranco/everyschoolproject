<?php 
 require_once 'header.php';
  session_start();
  $_SESSION['lang'] = 'EN';
  if(isset($_GET['lang'])) {
    $_SESSION['lang'] = $_GET['lang'];
  }
  if($_SESSION['lang'] == 'PT') {
    require_once 'src/i18n/i18n_pt.php';
  }
  if($_SESSION['lang'] == 'EN') {
    require_once 'src/i18n/i18n_en.php';
  }
  

  
?>

 <style>
  <?php include 'CSS/index.css'; ?>
 </style>

<div>
    <a href="index.php?lang=PT">PT</a>
    <a href="index.php?lang=EN">EN</a>
</div>
<div class="container">
  <div class = "row">
<div class="col">
    <div class="row">
          <div class="col">
            <h1><?php echo WELCOME ?></h1>
          </div>
      </div>
      <div class="row">
          <div class="col">
            <button type="button" class="btn btn-primary" onclick="send_to_login()" ><?php echo LOGIN ?></button>
          </div>
      </div>
      <div class="row">
          <div class="col">
            <button type="button" class="btn btn-primary" onclick="send_to_register()" ><?php echo REGISTER ?></button>
          </div>
      </div>
    </div>
  </div>
</div>

<?php
 if($_SERVER['REQUEST_METHOD']=='GET') {
       unset($_SESSION['id']);
    }
?>

<?php require_once 'footer.php';?>