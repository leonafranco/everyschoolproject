<?php 
 namespace src\View;
 require_once 'header.php';
 require_once('../DAO/Item.php');
 require_once('../Model/ItemModel.php');
 require_once('../DAO/Category.php');
 use src\DAO\Category;
 use src\DAO\Item;
 use src\Model\ItemModel;

session_start();

 if(isset($_GET['itemID'])) {
    $itemId = $_GET['itemID'];
    $itemDAO = new Item();
    $itemModel = new ItemModel();
    $result = $itemDAO->getItemByID($itemId);
    $uid = $_SESSION['id'];
    $categoryDAO = new Category();
    $categoryResult = $categoryDAO->getAllCategory($uid);
    $url = "editItem.php?itemID=" . $itemId;
 }
 if(!isset($_SESSION['id'])) {
    header('Location:http://localhost:8082/');
 }

    function update_user($result, $itemDAO, $categoryDAO) {
        if($_POST['name'] != '') {
            $result->setName($_POST['name']);
        }
        if($_POST['SKU'] != '') {
            $result->setIdentificationNumber($_POST['SKU']);
        }
        if($_POST['descricao'] != '') {
            $result->setDescription($_POST['descricao']);
        }
        if($_POST['valor'] != '') {
            $result->setPrice($_POST['valor']);
        }
        if($_POST['category'] != 'Escolha uma') {
            $categoryDAO->updateItemCategory($result->getId(),$_POST['category']);
        }
        if($_POST['flexRadioDefault'] == 1 && $_POST['InfoExchange'] != '') {
            $result->setExchange($_POST['flexRadioDefault']);
            $result->setInfoExchange($_POST['InfoExchange']);
        }elseif ($_POST['flexRadioDefault'] == 1 && $_POST['InfoExchange'] == '') {
            echo "<script>alert('Tem de inserir alguma informação de troca!')</script>";
        }
        $itemDAO->updateItem($result);

    }
?>

<div class="container">
    <h1>Crie um Item</h1>
    <form id="form-register" action="<?php echo $url ?>" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Nome do Item</label>
        <input type="nome" name="name" placeholder="<?php echo $result->getName();?>" class="form-control" id="InputNome" aria-describedby="nome">
    </div>
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Numero de identificação</label>
        <input type="SKU" name="SKU" class="form-control" placeholder="<?php echo $result->getIdentificationNumber(); ?>" id="InputNome" aria-describedby="nome">
    </div>
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Descrição</label>
        <input type="descricao"  name="descricao" class="form-control" placeholder="<?php echo $result->getDescription(); ?>" id="InputUsername" aria-describedby="username">
    </div>
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Valor</label>
        <input type="valor"  name="valor" class="form-control" id="InputUsername" placeholder="<?php echo $result->getPrice(); ?>" aria-describedby="username">
    </div>
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Categoria</label>
        <select class="form-select" name="category"  aria-label="Default select example">
            <option selected="selected">Escolha uma</option>
            <?php
            if(isset($categoryResult)){
                foreach($categoryResult as $category){
                    $item = $category['name'];
                    echo "<option value='$item'>$item</option>";
                }
            }
            ?>
        </select>
    </div>
    <label for="exampleInputEmail1" class="form-label">Para troca:</label>
    <div class="form-check">
        <input class="form-check-input" value=1 type="radio" name="flexRadioDefault" id="flexRadioDefault1">
        <label class="form-check-label" for="flexRadioDefault1">
            Sim
        </label>
        </div>
        <div class="form-check">
        <input class="form-check-input" value=0 type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked>
        <label class="form-check-label" for="flexRadioDefault2">
            Não
        </label>
    </div>
        <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Infomação para a troca</label>
        <input type="InfoExchange"  name="InfoExchange" class="form-control" id="infoForExchange" placeholder="" aria-describedby="username">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>


<?php
 if($_SERVER['REQUEST_METHOD']=='POST') {
        update_user($result, $itemDAO, $categoryDAO);
    }

require_once 'footer.php';?>