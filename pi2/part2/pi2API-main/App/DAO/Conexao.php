<?php

namespace App\DAO;

abstract class Conexao {

    protected $pdo;

    public function __construct() {
        $host = getenv('DATABASE_HOST');
        $port = getenv('DATABASE_PORT');
        $user = getenv('DATABASE_USER');
        $pass = getenv('DATABASE_PW');
        $dbname = getenv('DATABASE_DBNAME');

        $dsn = "mysql:host={$host};dbname={$dbname};port={$port}";


        $this->pdo = new \PDO($dsn, $user, $pass);
        $this->pdo->setAttribute(
            \PDO::ATTR_ERRMODE,
            \PDO::ERRMODE_EXCEPTION
        );
    }
}