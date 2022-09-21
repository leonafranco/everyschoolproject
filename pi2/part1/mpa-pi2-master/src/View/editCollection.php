<?php 
 namespace src\View;
 require_once 'header.php';
 require_once('../DAO/Collection.php');
 require_once('../Model/CollectionModel.php');
 use src\DAO\Collection;
 use src\Model\CollectionModel;

 session_start();

 if(isset($_GET['collectionID'])) {
    $collectionID = $_GET['collectionID'];
    $collectionDAO = new Collection();
    $collectionModel = new CollectionModel();
    $result = $collectionDAO->getCollectionByID($collectionID);
 }

 if(!isset($_SESSION['id'])) {
    header('Location: http://localhost:8082/');
 }

    function update_collection($result, $collectionDAO) {
        if($_POST['name']!= '') {
            $result->setName($_POST['name']);
        }
        if($_POST['observation']!= '') {
            $result->setObservation($_POST['observation']);
        }
        $collectionDAO->updateCollection($result);
        header('Location: http://localhost:8082/src/View/homepage.php');
    }
?>

<div class="container">
    <h1>Crie uma Coleção</h1>
    <form id="form-register" action="editCollection.php?collectionID= <?php echo $collectionID ?>" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Nome da Coleção</label>
        <input type="nome" name="name" placeholder="<?php echo $result->getName();?>" class="form-control" id="InputNome" aria-describedby="nome">
    </div>
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Observações</label>
        <input type="observation" name="observation" placeholder="<?php echo $result->getObservation();?>" class="form-control" id="InputNome" aria-describedby="nome">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>


<?php
 if($_SERVER['REQUEST_METHOD']=='POST') {
        update_collection($result, $collectionDAO);
    }

require_once 'footer.php';?>