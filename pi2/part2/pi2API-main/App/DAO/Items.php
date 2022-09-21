<?php

namespace App\DAO;

use App\Models\ItemModel;
use App\Models\listModel;

class Items extends Conexao {

    public function __construct(){
        parent::__construct();
    }

    public function getAllLists() {

        $users = $this->pdo->query('SELECT * from list')->fetchAll(\PDO::FETCH_ASSOC);

        return $users;
    }

    public function getListItems($list_id): array | null {
        $statement = $this->pdo->prepare('SELECT i.name, i.amount, i.comment, i.active, i.id, i.deleted
                                            FROM items i
                                            JOIN list_items li ON i.id = li.items_id 
                                            JOIN list l ON li.list_id = l.id
                                            WHERE l.id = :id');
        
        $statement->execute(['id' => $list_id]);
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

    public function getLastIdList(): int{
        $statement = $this->pdo->prepare('SELECT MAX(id) FROM list');
        $statement->setFetchMode(\PDO::FETCH_ASSOC);

        $list_id = $statement->fetch();

        return $list_id;
    }

    public function getLastIdItem(): int{
        $statement = $this->pdo->prepare('SELECT MAX(id) FROM items');
        $statement->execute();
        $statement->setFetchMode(\PDO::FETCH_ASSOC);

        $item_id = $statement->fetch();
        var_dump($item_id);

        return $item_id['MAX(id)'];
    }

    public function joinListToAuth($auth_id, $list_id) {
        $statement = $this->pdo->prepare("INSERT INTO auth_list (auth_id, list_id) VALUES (:auth_id , :list_id);");
        $statement->execute(['auth_id' => $auth_id,
                             'list_id'=> $list_id
                            ]);
        if (!$statement)
            return false;
        return true;
    }

    public function joinItemToList($item_id, $list_id) {
        $statement = $this->pdo->prepare("INSERT INTO list_items (items_id, list_id) VALUES (:items_id , :list_id);");
        $statement->execute(['items_id' => $item_id,
                             'list_id'=> $list_id
                            ]);
        if (!$statement)
            return false;
        return true;
    }


    public function createNewItem(ItemModel $item, $list_id): bool {
        $statement = $this->pdo->prepare("INSERT INTO items (name, amount, comment) values(:name, :amount, :comment)");
        $statement->execute(['name' => $item->getName(),
                             'amount'=> $item->getAmount(),
                             'comment'=> $item->getComment()
                            ]);
        if (!$statement)
            return false;
        else {
            $this->joinItemToList($this->getLastIdItem(), $list_id);
            return true;
        }
    }

    public function deleteItem($items_id) {
        $statement = $this->pdo->prepare("UPDATE items SET deleted = 1 WHERE id = :items_id;");
        $statement->execute(['items_id' => $items_id
                            ]);
        if (!$statement)
            return false;
        return true;
    }

    public function buyItem($items_id, $result) {
       $result ? $result = 0 : $result = 1;
        $statement = $this->pdo->prepare("UPDATE items SET active = :result WHERE id = :items_id;");
        $statement->execute(['items_id' => $items_id,
                             'result' => $result
                            ]);
        if (!$statement)
            return false;
        return true;
    }
}