/*package tests;

import org.junit.jupiter.api.Test;
import pt.thenerdguylulu.controller.PlayerController;
import pt.thenerdguylulu.model.Player;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {
    private PlayerController playerController = new PlayerController();

    @Test
    void addPlayer() {
        final boolean inserted = this.playerController.addPlayer(
                new Player(null, "aLuís")
        );

        assertTrue(inserted);
    }

    @Test
    void addPlayerWithSameName() {
        this.playerController.addPlayer(
                new Player(null, "Luís")
        );

        final boolean inserted = this.playerController.addPlayer(
                new Player(null, "Luís")
        );

        assertFalse(inserted);
    }

    @Test
    void getNextPlayer() {
        this.playerController.addPlayer(
                new Player(null, "Luís")
        );

        final Player nextPlayer = new Player(null, "André");
        this.playerController.addPlayer(nextPlayer);

        assertEquals(this.playerController.getNextPlayer(), nextPlayer);

    }
}*/