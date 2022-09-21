<?php 
 namespace src\View;
 require_once 'header.php';
 require_once('../DAO/Category.php');
 require_once('../Model/CategoryModel.php');
 use src\DAO\Category;
 use src\Model\CategoryModel;

 session_start();
 if(isset($_GET['categoryID'])) {
    $categoryID = $_GET["categoryID"];
    $categoryDAO = new Category();
    $categoryModel = new CategoryModel();
    $result = $categoryDAO->getCategoryByID($categoryID);
 }
 if(!isset($_SESSION['id'])) {
    header('Location: http://localhost:8082/');
 }

    function update_collection($result, $categoryDAO) {
        if($_POST['name'] != '') {
            $result->setName($_POST['name']);
        }
        if($_POST['observation'] != '') {
            $result->setObservation($_POST['observation']);
        }
        $categoryDAO->updateCategory($result);
        header('Location: http://localhost:8082/src/View/homepage.php');
    }
?>

<div class="container">
    <h1>Editar Coleção</h1>
    <form id="form-register" action="editCategory.php?categoryID= <?php echo$categoryID?>" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Nome da Coleção</label>
        <input type="nome" name="name" placeholder="<?php echo $result->getName();?>" class="form-control" id="InputNome" aria-describedby="nome">
    </div>
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Observações</label>
        <input type="observation" name="observation" class="form-control" placeholder="<?php echo $result->getObservation();?>" id="InputNome" aria-describedby="nome">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>


<?php
 if($_SERVER['REQUEST_METHOD']=='POST') {
        update_collection($result, $categoryDAO);
    }

require_once 'footer.php';?>