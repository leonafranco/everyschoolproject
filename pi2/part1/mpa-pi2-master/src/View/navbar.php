<?php
session_start();
if($_SESSION['lang'] == 'PT') {
    require_once '../i18n/i18n_pt.php';
  }
  if($_SESSION['lang'] == 'EN') {
    require_once '../i18n/i18n_en.php';
  }
?>



<div class="container">
 <nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav">
        <a class="nav-link active" aria-current="page" href="/src/View/homepage.php">Home</a>
        <a class="nav-link" href="/src/View/category.php"><?php echo CATEGORY ?></a>
        <a class="nav-link" href="/src/View/item.php">Items</a>
        <a class="nav-link" href="/src/View/itemsForSale.php">ItemsForSale</a>
        <a class="nav-link" href="/src/View/settings.php"><?php echo SETTINGS ?></a>
      </div>
    </div>
    <div class="d-flex">
      <a class="nav-link"  onclick="" href="/index.php">Logout</a>
    </div>
  </div>
</nav>