<?php

namespace App\Models;

final class listModel {
    private $id;
    private $name;
    private $active;
    private $createdAt;

    public function getId(): int {
        return $this->id;
    }

    public function getName(): string {
        return $this->name;
    }

    public function getActive(): bool {
        return $this->active;
    }

    public function getCreatedAt(): int {
        return $this->createdAt;
    }

    public function setName(string $name) {
        $this->name = $name;
    }

    public function setActive(bool $active) {
        $this->active = $active;
    }

    public function setCreatedAt(int $createdAt) {
        $this->createdAt = $createdAt;
    }
}