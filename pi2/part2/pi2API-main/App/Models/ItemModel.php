<?php

namespace App\Models;

final class itemModel {
    private $id;
    private $name;
    private $amount;
    private $comment;
    private $active;

    public function getId(): int {
        return $this->id;
    }

    public function getName(): string {
        return $this->name;
    }

    public function getActive(): bool {
        return $this->active;
    }

    public function getAmount(): int {
        return $this->amount;
    }

    public function getComment(): string {
        return $this->comment;
    }

    public function setName(string $name) {
        $this->name = $name;
    }

    public function setActive(bool $active) {
        $this->active = $active;
    }

    public function setComment(string $comment) {
        $this->comment = $comment;
    }

    public function setAmount(int $amount) {
        $this->amount = $amount;
    }
}