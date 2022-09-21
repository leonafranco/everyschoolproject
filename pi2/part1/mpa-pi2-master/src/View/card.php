<?php
$collection = $_SESSION["collection"];
$dt = new DateTime();
?>



    <div class="card" style="width: 20rem;">
      <div class="card-body">
        <h5 class="card-title"><?php echo $collection["name"] ?></h5>
        <p class="card-text"><?php echo $collection["observation"] ?></p>
      </div>
      <div class="card-footer">
        <small class="text-muted"><?php echo date("H:i:s d-m-Y ", substr($collection["startdate"], 0, 10)); ?></small>
      </div>
      <form action="../View/editCollection.php" method="GET" enctype="multipart/form-data">
        <button type="submit" name="collectionID" value=<?php echo $collection['id'] ?> >Edit this Collection</button>
      </form>  
    </div>
    

