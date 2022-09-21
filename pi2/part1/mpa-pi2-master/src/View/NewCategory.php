<?php 
$title = "Sistema de Coleção de caricas";
require_once 'header.php';
require_once 'navbar.php';

if($_SESSION['lang'] == 'PT') {
    require_once '../i18n/i18n_pt.php';
  }
  if($_SESSION['lang'] == 'EN') {
    require_once '../i18n/i18n_en.php';
  }
  if(!isset($_SESSION['id'])) {
    header('Location:http://localhost:8082/');
 }
?>
<style>
  <?php include 'CSS/register.css'; ?>
</style>

<div class="container" id="CreateContainer">
    <h1><?php echo CREATEACATEGORY ?></h1>
    <form id="form-register" action="../Include/register_new_category.php" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label"><?php echo CATEGORYNAME ?></label>
        <input type="nome" name="name" class="form-control" required id="InputNome" aria-describedby="nome">
    </div>
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label"><?php echo OBSERVATION ?></label>
        <input type="observation"  name="observation" class="form-control" required id="observation" aria-describedby="observation">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>



<script src="../script/scripts.js"></script>
<script src="../script/usernameAjax.js"></script>
<?php require_once 'footer.php';?>