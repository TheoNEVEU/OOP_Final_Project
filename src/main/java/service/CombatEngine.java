package service;

import model.Entity;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CombatEngine {

    private final DiceRoller diceRoller;

    public CombatEngine(DiceRoller diceRoller) {
        this.diceRoller = diceRoller;
    }

    public void startCombat(List<? extends Entity> heroes, List<? extends Entity> monsters) {
        System.out.println("\n--- LE COMBAT COMMENCE ---");

        List<Entity> allCombatants = new ArrayList<>();
        allCombatants.addAll(heroes);
        allCombatants.addAll(monsters);

        allCombatants.sort(Comparator.comparingInt(this::rollInitiative).reversed());

        System.out.println("Ordre d'initiative :");
        allCombatants.forEach(c -> System.out.println("- " + c.getName()));
        System.out.println("--------------------------");

        int round = 1;
        while (teamIsAlive(heroes) && teamIsAlive(monsters)) {
            System.out.println("\n--- ROUND " + round + " ---");

            for (Entity attacker : allCombatants) {
                if (attacker.isAlive() && teamIsAlive(heroes) && teamIsAlive(monsters)) {
                    Entity target = findTargetFor(attacker, heroes, monsters);

                    if (target != null) {
                        executeAttack(attacker, target);
                    }
                }
            }
            round++;
        }

        System.out.println("\n--- FIN DU COMBAT ---");
        if (teamIsAlive(heroes)) {
            System.out.println("VICTOIRE DES HÉROS !");
        } else {
            System.out.println("VICTOIRE DES MONSTRES !");
        }
    }

    private int rollInitiative(Entity entity) {
        return diceRoller.roll("1d20");
    }

    private void executeAttack(Entity attacker, Entity target) {
        System.out.println(attacker.getName() + " attaque " + target.getName() + " !");

        int attackRoll = diceRoller.roll("1d20") + attacker.getAttackBonus();

        if (attackRoll >= target.getArmorClass()) {
            int damage = diceRoller.roll(attacker.getDamageDice());
            target.takeDamage(damage);

            System.out.println("-> TOUCHÉ (" + attackRoll + " vs AC " + target.getArmorClass() + ") ! "
                    + target.getName() + " subit " + damage + " dégâts.");

            if (!target.isAlive()) {
                System.out.println("-> *** " + target.getName() + " EST MORT ! ***");
            }
        } else {
            System.out.println("-> MANQUÉ (" + attackRoll + " vs AC " + target.getArmorClass() + ").");
        }
    }

    private Entity findTargetFor(Entity attacker, List<? extends Entity> heroes, List<? extends Entity> monsters) {
        List<? extends Entity> opposingTeam = heroes.contains(attacker) ? monsters : heroes;

        List<Entity> aliveEnemies = opposingTeam.stream()
                .filter(Entity::isAlive)
                .collect(Collectors.toList());

        if (aliveEnemies.isEmpty()) {
            return null;
        }
        return aliveEnemies.get(0);
    }

    private boolean teamIsAlive(List<? extends Entity> team) {
        return team.stream().anyMatch(Entity::isAlive);
    }
}