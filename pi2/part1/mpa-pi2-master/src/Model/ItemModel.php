<?php

namespace src\Model;

final class ItemModel {
    private $id;
    private $name;
    private $identificationNumber;
    private $description;
    private $price;
    private $exchange;
    private $infoExchange;

    public function getId(): int {
        return $this->id;
    }

    public function getName(): string {
        return $this->name;
    }

    public function getIdentificationNumber(): int {
        return $this->identificationNumber;
    }

    public function getDescription(): string {
        return $this->description;
    }

    public function getPrice(): string {
        return $this->price;
    }

    public function getExchange(): bool {
        return $this->exchange;
    }

    public function getInfoExchange() {
        return $this->infoExchange;
    }

    public function setId(int $id) {
        $this->id = $id;
    }

    public function setName(string $name) {
        $this->name = $name;
    }

    public function setIdentificationNumber(int $identificationNumber) {
        $this->identificationNumber = $identificationNumber;
    }

    public function setDescription(string $description) {
        $this->description = $description;
    }

    public function setPrice(int $price) {
        $this->price = $price;
    }

    public function setExchange(bool $exchange) {
        $this->exchange = $exchange;
    }

    public function setInfoExchange($infoExchange) {
        $this->infoExchange = $infoExchange;
    }
}