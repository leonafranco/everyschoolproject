<?php

namespace src\Model;

final class UserModel {
    private $id;
    private $name;
    private $username;
    private $email;
    private $password;
    private $photo;
    private $startdate;

    public function getId(): int {
        return $this->id;
    }

    public function getName(): string {
        return $this->name;
    }

    public function getUsername(): string {
        return $this->username;
    }

    public function getEmail(): string {
        return $this->email;
    }

    public function getPassword(): string {
        return $this->password;
    }

    public function getPhoto(): string {
        return $this->photo;
    }

    public function getStardate(): int {
        return $this->startdate;
    }

    public function setId(int $id) {
        $this->id = $id;
    }

    public function setName(string $name) {
        $this->name = $name;
    }

    public function setUsername(string $username) {
        $this->username = $username;
    }

    public function setEmail(string $email) {
        $this->email = $email;
    }

    public function setPassword(string $password) {
        $this->password = $password;
    }

    public function setPhoto(string $photo) {
        $this->photo = $photo;
    }

    public function setStartdate(int $startdate) {
        $this->startdate = $startdate;
    }
}