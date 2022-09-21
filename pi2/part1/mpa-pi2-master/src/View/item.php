<?php 
 require_once 'header.php';
 require_once 'navbar.php';
 require_once('../DAO/Item.php');
 use src\DAO\Item;
 $uid = $_SESSION["id"];
 $item = new Item();
 $result = $item->getAllItem($uid);
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
                foreach ($result as $item) {
                    $_SESSION["item"] = $item;
                    include '../Include/cardItem.php';
                }
            ?> 
        </div>
    </div>
</div>





<script src="../script/scripts.js"></script>
<?php require_once 'footer.php';?>