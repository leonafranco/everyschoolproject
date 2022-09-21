<?php
$item = $_SESSION["item"];
?>

    <div class="card" style="width: 20rem;">
      <div class="card-body">
        <h5 class="card-title"><?php echo $item["name"] ?></h5>
        <p class="card-text"><?php echo $item["description"] ?></p>
      </div>
      <div class="card-footer">
        <small class="text-muted"><?php ?></small>
      </div>
      <form action="../View/editItem.php" method="GET" enctype="multipart/form-data">
        <button type="submit" name="itemID" value=<?php echo $item['id'] ?> >Edit this item</button>
      </form>  
    </div>



