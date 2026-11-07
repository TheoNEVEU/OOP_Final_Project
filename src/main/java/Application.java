import io.DataLoader;
import io.JsonDataLoader;
import model.Hero;
import model.Monster;
import service.CombatEngine;
import service.DiceRoller;
import service.StandardDiceRoller;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println("=== INITIALISATION DU JEU ===");

        DataLoader dataLoader = new JsonDataLoader();

        List<Hero> heroes = dataLoader.loadData("heroes.json", Hero.class);
        System.out.println(heroes.size() + " héros chargés.");

        List<Monster> monsters = dataLoader.loadData("bestiary.json", Monster.class);
        System.out.println(monsters.size() + " monstres chargés.");

        DiceRoller diceRoller = new StandardDiceRoller();
        CombatEngine combatEngine = new CombatEngine(diceRoller);

        combatEngine.startCombat(heroes, monsters);
    }
}