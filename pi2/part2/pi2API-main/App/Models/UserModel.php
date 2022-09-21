<?php

namespace App\Models;

final class userModel {
    private $id;
    private $name;
    private $age;

    public function getId(): int {
        return $this->id;
    }

    public function getName(): string {
        return $this->name;
    }

    public function getAge(): int {
        return $this->age;
    }

    public function setId(int $id) {
        $this->id = $id;
    }

    public function setName(string $name) {
        $this->name = $name;
    }

    public function setAge(int $age) {
        $this->age = $age;
    }
}