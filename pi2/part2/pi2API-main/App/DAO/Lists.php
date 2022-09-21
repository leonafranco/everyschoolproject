<?php

namespace App\DAO;

use App\Models\ItemModel;
use App\Models\ListModel;

class Lists extends Conexao {

    public function __construct(){
        parent::__construct();
    }

    public function getAllLists() {

        $users = $this->pdo->query('SELECT * from list')->fetchAll(\PDO::FETCH_ASSOC);

        return $users;
    }

    public function getUserLists($username): array | null {

        $statement = $this->pdo->prepare('SELECT l.id, l.name, l.active
                                            FROM list l
                                            JOIN auth_list al ON l.id = al.list_id
                                            JOIN auth a ON al.auth_id = a.id
                                            WHERE a.username = :username');
        
        $statement->execute(['username' => $username]);
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

    public function getAuthUserId($username): int{
        $statement = $this->pdo->prepare('SELECT id from auth where username = :username');
        $statement->execute(['username' => $username]);
        $statement->setFetchMode(\PDO::FETCH_ASSOC);

        $auth_id = $statement->fetch();

        return $auth_id['id'];
    }

    public function getLastIdList(): int{
        $statement = $this->pdo->prepare('SELECT MAX(id) FROM list');
        $statement->execute();
        $statement->setFetchMode(\PDO::FETCH_ASSOC);

        $list_id = $statement->fetch();
        var_dump($list_id);


        return $list_id['MAX(id)'];
    }

    public function joinListToAuth($auth_id, $list_id) {
        var_dump($auth_id);
        var_dump($list_id);
        $statement = $this->pdo->prepare("INSERT INTO auth_list (auth_id, list_id) VALUES (:auth_id , :list_id);");
        $statement->execute(['auth_id' => $auth_id,
                             'list_id'=> $list_id
                            ]);
        if (!$statement)
            return false;
        return true;
    }

    public function createNewList(listModel $list, $username): bool {
        $statement = $this->pdo->prepare("INSERT INTO list (name) values(:name)");
        $statement->execute(['name' => $list->getName()]);
        if (!$statement)
            return false;
        else {
            $this->joinListToAuth($this->getAuthUserId($username), $this->getLastIdList());
            return true;
        }
    }

    public function updateList($list_id) {
        $statement = $this->pdo->prepare("UPDATE list SET active = 0 WHERE id = :list_id;");
        $statement->execute(['list_id' => $list_id
                            ]);
        if (!$statement)
            return false;
        return true;
    }
}