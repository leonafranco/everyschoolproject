<?php

namespace src\DAO;
require_once('Connection.php');

use src\Model\CategoryModel;

class Category extends Connection {

    public function __construct(){
        parent::__construct();
    }

    public function createNewCategory($name, $observation, $auth_id): bool {
        $statement = $this->pdo->prepare("INSERT INTO category (name, observation) values(:name, :observation)");
        $statement->execute(['name' => $name,
                             'observation'=> $observation
                            ]);
        if (!$statement){
            return false;
        }else {
            $this->joinCategoryToAuth($auth_id, $this->getLastIdCategory());
            return true;
        }
    }

    public function getLastIdCategory(): int{
        $statement = $this->pdo->prepare('SELECT MAX(id) FROM category');
        $statement->execute();
        $statement->setFetchMode(\PDO::FETCH_ASSOC);

        $collection_id = $statement->fetch();
    

        return $collection_id['MAX(id)'];
    }

    public function joinCategoryToAuth($auth_id, $category_id) {
        $statement = $this->pdo->prepare("INSERT INTO auth_category (auth_id, category_id) VALUES (:auth_id , :category_id);");
        $statement->execute(['auth_id' => $auth_id,
                             'category_id'=> $category_id
                            ]);
        if (!$statement)
            return false;
        return true;
    }

    public function deleteCategory($category_id) {
        if($this->checkIfExistAnyItemToThisCategory($category_id)) return false;

        $statement = $this->pdo->prepare("UPDATE category set active = 0 WHERE id = :category_id;");
        $statement->execute(['category_id'=> $category_id]);
        if (!$statement)
            return false;
        return true;
    }

    public function getAllCategory($auth_id) {
        $statement = $this->pdo->prepare('SELECT c.id, c.name, c.observation, c.active
                                        FROM category c
                                            JOIN auth_category ac ON c.id = ac.category_id
                                            JOIN auth a ON ac.auth_id = a.id
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

    public function getCategoryByID($category_id) {
        $statement = $this->pdo->prepare('SELECT * FROM category WHERE  id = :id');
        $statement->execute(['id' => $category_id]);
        $statement->setFetchMode(\PDO::FETCH_ASSOC);

        $categoryModel = new CategoryModel();

        while($result = $statement->fetch()){
            $categoryModel->setId($result['id']);
            $categoryModel->setName($result['name']);
            $categoryModel->setObservation($result['observation']);
        }
        if (isset($categoryModel)){
            return $categoryModel;
        }
        return null;
    }

    public function getCategoryByName($category) {
        $statement = $this->pdo->prepare('SELECT * FROM category WHERE  name = :category');
        $statement->execute(['category' => $category]);
        $statement->setFetchMode(\PDO::FETCH_ASSOC);

        while($result = $statement->fetch()){
            if (isset($result)){
                return $result;
            }
        }
        return null;
    }

    public function updateCategory($result) {
        $statement = $this->pdo->prepare('UPDATE category SET name = :name, observation = :observation WHERE id = :id');
        $statement->execute(['name' => $result->getName(),
                             'observation' => $result->getObservation(),
                             'id' => $result->getId()]);

        if (!$statement){
            return false;
        }else {
            return true;
        }
    }

    public function checkIfExistAnyItemToThisCategory($category_id) {
        $statement = $this->pdo->prepare('select * from category c
                                            join category_item ci ON c.id = ci.category_id
                                            join item i ON ci.item_id = i.id
                                            where c.id = :id');
        $statement->execute(['id' => $category_id]);
        $statement->setFetchMode(\PDO::FETCH_ASSOC);
        $i = 0;

        while($result = $statement->fetch()){
            $array_result[$i] = $result;
            $i++;
        }
        
        if (isset($array_result)){
            return true;
        }
        return false;
    }

    public function updateItemCategory($item_id, $categoryName) {
        $category = $this->getCategoryByName($categoryName);
        $statement = $this->pdo->prepare("UPDATE category_item set category_id = :category_id where item_id = :item_id;");
        $statement->execute(['category_id' => $category['id'],
                             'item_id'=> $item_id
                            ]);
        if (!$statement)
            return false;
        return true;
    }

    

    

}