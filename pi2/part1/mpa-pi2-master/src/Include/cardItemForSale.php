<?php
$item = $_SESSION["item"];
?>

    <div class="card" style="width: 20rem;">
      <div class="card-body">
        <h5 class="card-title"><?php echo $item["name"] ?></h5>
        <p class="card-text"><?php echo $item["infoExchange"] ?></p>
      </div>
      <div class="card-footer">
        <small class="text-muted"><?php ?></small>
      </div>
    </div>



