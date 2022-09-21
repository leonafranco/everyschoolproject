<?php

namespace App\DAO;

use App\Model\UserModel;

class Users extends Conexao {

    public function __construct(){
        parent::__construct();
    }

    public function getAllUsers() {

        $users = $this->pdo->query('SELECT * from auth')->fetchAll(\PDO::FETCH_ASSOC);

        return $users;
    }

    public function auth($username, $password): array | null {
        $statement = $this->pdo->prepare('SELECT * from auth where username = :username AND password = :password');
        $statement->execute(['username' => $username,
                             'password'=> sha1($password)
                            ]);
        $statement->setFetchMode(\PDO::FETCH_ASSOC);

        while($result = $statement->fetch()){
            $array_result = array_merge($result);
        }

        if (isset($array_result)){
            return $array_result;
        }
        return null;
    }


    public function createNewUser($login, $password): bool {
        if($this->getUserId($login) !== null) return false;

        $statement = $this->pdo->prepare("INSERT INTO auth (username, password) values(:username, :password)");
        $statement->execute(['username' => $login,
                             'password'=> $password
                            ]);
        if (!$statement)
            return false;
        return true;
    }


    public function getUserId($username) {
        $statement = $this->pdo->prepare('SELECT id from auth where username = :username');
        $statement->execute(['username' => $username]);
        $statement->setFetchMode(\PDO::FETCH_ASSOC);

        $user_id = $statement->fetch();
        if($user_id == false) {
            return null;
        }else {
            return $user_id['id'];
        }
    }

    public function updateUser($username, $param, $newAtribute) {
        if($param == "username") {
            $this->updateUsername($username, $newAtribute);
        }
        if($param == "password") {
            $this->updatePassword($username, $newAtribute);
        }
    }

    public function updateUsername($username, $newAtribute) {
        var_dump("this should be outputed");
        $user_id = $this->getUserId($username);
        var_dump($user_id);
        $statement = $this->pdo->prepare("UPDATE auth set username = :username WHERE id = :user_id;");
        $statement->execute(['user_id' => $user_id,
                              'username' => $newAtribute
                            ]);
        if (!$statement)
            return false;
        return true;
    }

    public function updatePassword($username, $newAtribute) {
        $user_id = $this->getUserId($username);
        $statement = $this->pdo->prepare("UPDATE auth set password = :password WHERE id = :user_id;");
        $statement->execute(['user_id' => $user_id,
                              'password' => sha1($newAtribute)
                            ]);
        if (!$statement)
            return false;
        return true;
    }
}