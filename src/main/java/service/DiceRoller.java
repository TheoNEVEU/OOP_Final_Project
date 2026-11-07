package service;

public interface DiceRoller {
    /**
     * Lance les dés selon une notation standard RPG.
     * @param diceNotation Exemples: "1d20", "2d6", "1d8+3"
     * @return Le résultat aléatoire total.
     */
    int roll(String diceNotation);
}