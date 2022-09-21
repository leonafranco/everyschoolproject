<?php 
 require_once 'header.php';
 require_once 'navbar.php';
 require_once('../DAO/Collection.php');
 use src\DAO\Collection;
 $uid = $_SESSION["id"];
 $collection = new Collection();
 $result = $collection->getAllCollection($uid);
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
<div class="container text-center">
    <div class="row">
        <div class="col-2">
            <a class="nav-link" href="/src/View/Newcollection.php"><?php echo CREATECOLLECTION ?></a>
            <a class="nav-link" href="/src/View/Newcategory.php"><?php echo CREATEACATEGORY ?></a>
            <a class="nav-link" href="/src/View/Newitem.php"><?php echo CREATEITEM ?></a>
        </div>
        <div class="col">
            <?php
                if(isset($result)){
                    foreach ($result as $collection) {
                        $_SESSION["collection"] = $collection;
                        include 'card.php';
                    }
                }else {
                    include '../Include/noCardAvailable.php';
                }
            ?> 
        </div>
    </div>
</div>





<script src="../script/scripts.js"></script>
<?php require_once 'footer.php';?>