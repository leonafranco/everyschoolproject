<?php
$category = $_SESSION["category"];
$url = "../Include/delete_category.php?id=" . $category["id"];
require_once('../DAO/Category.php');
use src\DAO\Category; 

if($_SERVER['REQUEST_METHOD']=='POST') {
  $category = new Category();
  $categoryResult = $category->deleteCategory($_POST["categoryID"]);
  if($categoryResult == false) {
    echo "<script>alert('Existem itens para esta categoria')</script>";
  }else {
    header("Location:http://localhost:8082/src/View/category.php");
    die();
  }
}
?>

    <div class="card" style="width: 20rem;">
    <div class="row">
      <div class="col">
      <div class="card-body">
        <h5 class="card-title"><?php echo $category["name"] ?> </h5>
        <p class="card-text"><?php echo $category["observation"] ?></p>
      </div>
      <div class="card-footer">
        <small class="text-muted"><?php ?></small>
      </div>
      <form action="../View/editCategory.php" method="GET" enctype="multipart/form-data">
        <button name="categoryID" value=<?php echo $category['id'] ?> >Edit this Category</button>
      </form>  
      <form action="../Include/cardCategory.php" method="POST" enctype="multipart/form-data">
        <button name="categoryID" value="<?php echo $category["id"]?>" >Delete Category</button>
      </form>  
      </div>
    </div>
    </div>
    
