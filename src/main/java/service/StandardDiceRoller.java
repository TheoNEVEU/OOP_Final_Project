package service;

import exception.InvalidDiceFormatException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StandardDiceRoller implements DiceRoller {
    private final Random random = new Random();

    // Une expression régulière pour lire "1d20" ou "1d8+3"
    private static final Pattern DICE_PATTERN = Pattern.compile("^(\\d+)d(\\d+)(?:\\+(\\d+))?$");

    @Override
    public int roll(String diceNotation) {
        if (diceNotation == null || diceNotation.trim().isEmpty()) {
            throw new InvalidDiceFormatException("La notation de dés ne peut pas être vide.");
        }

        Matcher matcher = DICE_PATTERN.matcher(diceNotation.trim().toLowerCase());

        if (!matcher.matches()) {
            throw new InvalidDiceFormatException("Format invalide : " + diceNotation + ". Attendu: XdY ou XdY+Z");
        }

        int numberOfDice = Integer.parseInt(matcher.group(1));
        int diceFaces = Integer.parseInt(matcher.group(2));

        int modifier = 0;
        if (matcher.group(3) != null) {
            modifier = Integer.parseInt(matcher.group(3));
        }

        if (numberOfDice == 0 || diceFaces == 0) {
            throw new InvalidDiceFormatException("Le nombre de dés et de faces doit être supérieur à 0.");
        }

        int total = 0;
        for (int i = 0; i < numberOfDice; i++) {
            // random.nextInt(X) renvoie un nombre de 0 à X-1. On ajoute 1 pour avoir de 1 à X.
            total += random.nextInt(diceFaces) + 1;
        }

        return total + modifier;
    }
}