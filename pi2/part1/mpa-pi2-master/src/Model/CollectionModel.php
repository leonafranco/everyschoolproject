<?php

namespace src\Model;

final class CollectionModel {
    private $id;
    private $name;
    private $startDate;
    private $observation;
    private $active;

    public function getId(): int {
        return $this->id;
    }

    public function getName(): string {
        return $this->name;
    }

    public function getStartDate(): int {
        return $this->startDate;
    }

    public function getObservation(): string {
        return $this->observation;
    }

    public function getActive(): bool {
        return $this->active;
    }

    public function setId(int $id) {
        $this->id = $id;
    }

    public function setName(string $name) {
        $this->name = $name;
    }

    public function setStartDate(int $startDate) {
        $this->startDate = $startDate;
    }

    public function setObservation(string $observation) {
        $this->observation = $observation;
    }

    public function setActive(bool $active) {
        $this->active = $active;
    }

}