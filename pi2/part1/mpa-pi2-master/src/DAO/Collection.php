<?php

namespace src\DAO;

use src\Model\collectionModel;

require_once('Connection.php');

class Collection extends Connection {

    public function __construct(){
        parent::__construct();
    }

    public function createNewCollection($name, $observation, $auth_id): bool {
        $now = time();
        $statement = $this->pdo->prepare("INSERT INTO collection (name, observation, startdate) values(:name, :observation, :startdate)");
        $statement->execute(['name' => $name,
                             'observation'=> $observation,
                             'startdate' => $now
                            ]);
        if (!$statement){
            return false;
        }else {
            $this->joinCollectionToAuth($auth_id, $this->getLastIdCollection());
            return true;
        }
    }

    public function getLastIdCollection(): int{
        $statement = $this->pdo->prepare('SELECT MAX(id) FROM collection');
        $statement->execute();
        $statement->setFetchMode(\PDO::FETCH_ASSOC);

        $collection_id = $statement->fetch();


        return $collection_id['MAX(id)'];
    }

    public function joinCollectionToAuth($auth_id, $collection_id) {

        $statement = $this->pdo->prepare("INSERT INTO auth_collection (auth_id, collection_id) VALUES (:auth_id , :collection_id)");
        $statement->execute(['auth_id' => $auth_id,
                             'collection_id'=> $collection_id
                            ]);
        if (!$statement)
            return false;
        return true;
    }

    public function getAllCollection($auth_id) {
                            $statement = $this->pdo->prepare('SELECT c.id, c.name, c.observation, c.startdate
                                                            FROM collection c
                                                                JOIN auth_collection ac ON c.id = ac.collection_id
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

    public function getCollectionByID($collection_id) {
        $statement = $this->pdo->prepare('SELECT * FROM collection WHERE  id = :id');
        $statement->execute(['id' => $collection_id]);
        $statement->setFetchMode(\PDO::FETCH_ASSOC);

        $collectionModel = new CollectionModel;
        while($result = $statement->fetch()){
            $collectionModel->setId($result['id']);
            $collectionModel->setName($result['name']);
            $collectionModel->setObservation($result['observation']);
        }

        if (isset($collectionModel)){
            return $collectionModel;
        }
        return null;
    }

    public function updateCollection($result) {
        $statement = $this->pdo->prepare('UPDATE collection SET name = :name, observation = :observation WHERE  id = :id');
        $statement->execute(['name' => $result->getName(),
                             'observation' => $result->getObservation(),
                             'id' => $result->getId()]);

        if (!$statement){
            return false;
        }else {
            return true;
        }
    }
}