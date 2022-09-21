<?php

namespace src\Model;

final class CategoryModel {
    private $id;
    private $name;
    private $observation;
    private $active;

    public function getId(): int {
        return $this->id;
    }

    public function getName(): string {
        return $this->name;
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

    public function setObservation(string $observation) {
        $this->observation = $observation;
    }

    public function setActive(bool $active) {
        $this->active = $active;
    }
}