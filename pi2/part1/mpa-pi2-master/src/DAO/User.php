<?php

namespace src\DAO;
require_once('Connection.php');
require_once('../Model/UserModel.php');
use src\Model\UserModel;

class User extends Connection {

    public function __construct(){
        parent::__construct();
    }

    public function checkUsername($username) {

        $statement = $this->pdo->prepare('SELECT * from auth where username = :username');
        $statement->execute(['username' => $username]);
        $statement->setFetchMode(\PDO::FETCH_ASSOC);

        $user_id = $statement->fetch();


        if(isset($user_id['id'])){
            return true;
        }else {
            return false;
        }
    }

    public function auth($username, $password): array | null {
        $statement = $this->pdo->prepare('SELECT * from auth where username = :username AND password = :password AND active = 1');
        $statement->execute(['username' => $username,
                             'password'=> $password
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

    public function getUserData($id) {
        $statement = $this->pdo->prepare('SELECT * from auth where id = :id');
        $statement->execute(['id' => $id]);
        $statement->setFetchMode(\PDO::FETCH_ASSOC);

        $userModel = new UserModel();

        while($result = $statement->fetch()){
            $userModel->setId($result['id']);
            $userModel->setName($result['name']);
            $userModel->setUsername($result['username']);
            $userModel->setPassword($result['password']);
            $userModel->setEmail($result['email']);
            $userModel->setPhoto($result['photo']);
            $userModel->setStartdate($result['startdate']);
        }

        if (isset($userModel)){
            return $userModel;
        }
        return null;
    }

    public function getUserDataByUsername($username) {
        $statement = $this->pdo->prepare('SELECT * from auth where username = :username');
        $statement->execute(['username' => $username]);
        $statement->setFetchMode(\PDO::FETCH_ASSOC);

        $userModel = new UserModel();

        while($result = $statement->fetch()){
            $userModel->setId($result['id']);
            $userModel->setName($result['name']);
            $userModel->setUsername($result['username']);
            $userModel->setPassword($result['password']);
            $userModel->setEmail($result['email']);
            $userModel->setPhoto($result['photo']);
            $userModel->setStartdate($result['startdate']);
        }

        if (isset($userModel)){
            return $userModel;
        }
        return null;
    }


    public function createNewUser($name, $username, $email, $password, $photo): bool {
        $now = time();
        $statement = $this->pdo->prepare("INSERT INTO auth (name, username, email, password, photo, startdate) values(:name, :username, :email, :password, :photo, :now)");
        $statement->execute(['username' => $username,
                             'password'=> $password,
                             'name' => $name,
                             'email'=> $email,
                             'photo'=> $photo,
                             'now' => $now
                            ]);
        if (!$statement)
            return false;
        return true;
    }

    public function updateUser($user) {
        $statement = $this->pdo->prepare("UPDATE auth SET name = :name, username = :username, password = :password, email = :email, photo = :photo WHERE id = :id;");
        $statement->execute(['name' => $user->getName(),
                              'username' => $user->getUsername(),
                              'password' => $user->getPassword(),
                              'email' => $user->getEmail(),
                              'photo' => $user->getPhoto(),
                              'id' => $user->getId()
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

        return $user_id['id'];
    }

    public function activateAccount($username) {
        $statement = $this->pdo->prepare("UPDATE auth SET active = 1 WHERE username = :username;");
        $statement->execute(['username' => $username
                            ]);
        if (!$statement)
            return false;
        return true;
    }

}