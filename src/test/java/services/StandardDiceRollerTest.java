package services;

import exception.InvalidDiceFormatException;
import org.junit.jupiter.api.Test;
import service.DiceRoller;
import service.StandardDiceRoller;

import static org.junit.jupiter.api.Assertions.*;

class StandardDiceRollerTest {

    private final DiceRoller diceRoller = new StandardDiceRoller();

    @Test
    void shouldRollSingleDiceCorrectly() {
        // Teste "1d20" : on lance 1 dé de 20 faces, le résultat doit être entre 1 et 20.
        int result = diceRoller.roll("1d20");
        assertTrue(result >= 1 && result <= 20, "Le résultat 1d20 doit être entre 1 et 20");
    }

    @Test
    void shouldRollDiceWithModifierCorrectly() {
        // Teste "1d8+3" : le résultat minimum est 1+3=4, le maximum est 8+3=11.
        int result = diceRoller.roll("1d8+3");
        assertTrue(result >= 4 && result <= 11, "Le résultat 1d8+3 doit être entre 4 et 11");
    }

    @Test
    void shouldThrowExceptionForInvalidFormat() {
        // Teste que l'exception est bien lancée si on met n'importe quoi.
        assertThrows(InvalidDiceFormatException.class, () -> {
            diceRoller.roll("un dé vingt");
        });
    }
}