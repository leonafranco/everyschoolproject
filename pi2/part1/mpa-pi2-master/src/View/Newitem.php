<?php 
$title = "Sistema de Coleção de caricas";
require_once 'header.php';
require_once 'navbar.php';
require_once('../DAO/Category.php');
use src\DAO\Category;

if($_SESSION['lang'] == 'PT') {
    require_once '../i18n/i18n_pt.php';
  }
  if($_SESSION['lang'] == 'EN') {
    require_once '../i18n/i18n_en.php';
  }
if(!isset($_SESSION['id'])) {
    header('Location:http://localhost:8082/');
} 
$uid = $_SESSION['id'];
$category = new Category();
$result = $category->getAllCategory($uid);
?>

<style>
  <?php include 'CSS/register.css'; ?>
</style>

<div class="container">
    <h1><?php echo CREATEITEM ?></h1>
    <form id="form-register" action="../Include/register_new_item.php" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label"><?php echo ITEMNAME ?></label>
        <input type="nome" name="name" class="form-control" required id="InputNome" aria-describedby="nome">
    </div>
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label"><?php echo SKU ?></label>
        <input type="SKU" name="SKU" class="form-control" required id="InputNome" aria-describedby="nome">
    </div>
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label"><?php echo DESCRIPTION ?></label>
        <input type="descricao"  name="descricao" class="form-control" required id="InputUsername" aria-describedby="username">
    </div>
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label"><?php echo VALUE ?></label>
        <input type="valor"  name="valor" class="form-control" required id="InputUsername" aria-describedby="username">
    </div>
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label"><?php echo CATEGORY ?></label>
        <select class="form-select" name="category"  aria-label="Default select example">
            <option selected="selected">Escolha uma</option>
            <?php
            if(isset($result)){
                foreach($result as $category){
                    $item = $category['name'];
                    $categoryId = $category['id'];
                    echo "<option value='$categoryId'>$item</option>";
                }
            }
            ?>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>



<script src="../script/scripts.js"></script>
<script src="../script/usernameAjax.js"></script>
<?php require_once 'footer.php';?>