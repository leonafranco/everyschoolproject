<?php 
 require_once 'header.php';
 require_once 'navbar.php';
 require_once('../DAO/Category.php');

use src\DAO\Category;
 $uid = $_SESSION["id"];
 $category = new Category();
 $result = $category->getAllCategory($uid);
 if(!isset($_SESSION['id'])) {
    header('Location:http://localhost:8082/');
 }

if($_SESSION['lang'] == 'PT') {
    require_once '../i18n/i18n_pt.php';
  }
  if($_SESSION['lang'] == 'EN') {
    require_once '../i18n/i18n_en.php';
  }
 ?>
<div class="container text-center">
    <div class="row">
        <div class="col-2">
            <a class="nav-link" href="/src/View/Newcollection.php"><?php echo CREATECOLLECTION ?></a>
            <a class="nav-link" href="/src/View/Newcategory.php"><?php echo CREATEACATEGORY ?></a>
            <a class="nav-link" href="/src/View/Newitem.php"><?php echo CREATEITEM ?></a>
        </div>
        <div class="col">
            <?php
                foreach ($result as $category) {
                    if($category["active"] == 1){
                        $_SESSION["category"] = $category;
                        include '../Include/cardCategory.php';
                    }
                }
            ?> 
        </div>
    </div>
</div>





<script src="../script/scripts.js"></script>
<?php require_once 'footer.php';?>