<?php

namespace src\DAO;
require_once('Connection.php');
require_once('../Model/ItemModel.php');
use src\Model\ItemModel;
require_once('../DAO/Category.php');
use src\DAO\Category;
class Item extends Connection {

    public function __construct(){
        parent::__construct();
    }

    public function getLastIdItem(): int{
        $statement = $this->pdo->prepare('SELECT MAX(id) FROM item');
        $statement->execute();
        $statement->setFetchMode(\PDO::FETCH_ASSOC);

        $item_id = $statement->fetch();


        return $item_id['MAX(id)'];
    }

    public function joinItemToAuth($auth_id, $item_id) {
        $statement = $this->pdo->prepare("INSERT INTO auth_item (auth_id, item_id) VALUES (:auth_id , :item_id)");
        $statement->execute(['auth_id' => $auth_id,
                             'item_id'=> $item_id
                            ]);
        if (!$statement)
            return false;
        return true;
    }

    public function jointItemToCategory($category_id, $item_id) {


        $statement = $this->pdo->prepare("INSERT INTO category_item (category_id, item_id) VALUES (:category_id , :item_id)");
        $statement->execute(['category_id' => $category_id,
                             'item_id'=> $item_id
                            ]);
        if (!$statement)
            return false;
        return true;
    }

    public function createNewItem($name, $observation, $sku, $valor, $uid, $category_id): bool {
        $statement = $this->pdo->prepare("INSERT INTO item (name, identificationnumber, description, price) values (:name, :sku, :description, :price)");
        $statement->execute(['name' => $name,
                             'sku'=> $sku,
                             'description' => $observation,
                             'price'=> $valor
                            ]);
        if (!$statement){
            return false;
        }else {
            $this->joinItemToAuth($uid, $this->getLastIdItem());
            $this->jointItemToCategory($category_id, $this->getLastIdItem());
            return true;
        }
    }


    public function getAllItem($auth_id) {
        $statement = $this->pdo->prepare('SELECT i.id, i.name, i.identificationnumber, i.description, i.price, i.exchange, i.infoExchange
                                        FROM item i
                                            JOIN auth_item ai ON i.id = ai.item_id
                                            JOIN auth a ON ai.auth_id = a.id
                                            WHERE a.id = :auth_id');

        $statement->execute(['auth_id' => $auth_id]);
        $statement->setFetchMode(\PDO::FETCH_ASSOC);
        $i = 0;

        while($result = $statement->fetch()){
            $array_result[$i] = $result;
            $i++;
        }

        if (isset($array_result)){
            return $array_result;
        }
        return null;
    }

    public function getAllItemForSale() {
        $statement = $this->pdo->prepare('SELECT * FROM item WHERE  exchange = 1');
        $statement->execute();
        $statement->setFetchMode(\PDO::FETCH_ASSOC);
        $i = 0;

        while($result = $statement->fetch()){
            $array_result[$i] = $result;
            $i++;
        }

        if (isset($array_result)){
            return $array_result;
        }
        return null;
    }

    public function getItemByID($item_id) {
        $statement = $this->pdo->prepare('SELECT * FROM item WHERE  id = :id');
        $statement->execute(['id' => $item_id]);
        $statement->setFetchMode(\PDO::FETCH_ASSOC);
        
        $itemModel = new ItemModel();

        while($result = $statement->fetch()){
            $itemModel->setId($result['id']);
            $itemModel->setName($result['name']);
            $itemModel->setIdentificationNumber($result['identificationnumber']);
            $itemModel->setDescription($result['description']);
            $itemModel->setPrice($result['price']);
            $itemModel->setExchange($result['exchange']);
            $itemModel->setInfoExchange($result['infoExchange']);
        }

        if (isset($itemModel)){
            return $itemModel;
        }
        return null;
    }

    public function updateItem($result) {
        if($result->getExchange() === false) {
            $exchange = 0;
        }else {
            $exchange = 1;
        }
        $statement = $this->pdo->prepare('UPDATE item SET name = :name, identificationnumber = :identificationnumber, price = :price, exchange = :exchange, infoExchange = :infoExchange, description = :description WHERE  id = :id');
        $statement->execute(['name' => $result->getName(),
                             'identificationnumber' => $result->getIdentificationNumber(),
                             'price' => $result->getPrice(),
                             'exchange' => $exchange,
                             'infoExchange' => $result->getInfoExchange(),
                             'description' => $result->getDescription(),
                             'id' => $result->getId()]);

        if (!$statement){
            return false;
        }else {
            return true;
        }
    }
}