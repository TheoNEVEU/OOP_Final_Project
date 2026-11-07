package model;

public class Hero extends Entity {
    private String heroClass;

    public Hero() {
        super();
    }

    public Hero(String name, String heroClass, int maxHp, int armorClass, int attackBonus, String damageDice) {
        super(name, maxHp, armorClass, attackBonus, damageDice);
        this.heroClass = heroClass;
    }

    public String getHeroClass() { return heroClass; }
    public void setHeroClass(String heroClass) { this.heroClass = heroClass; }

    @Override
    public String toString() {
        return String.format("Héros: %s (Classe: %s, PV: %d/%d, AC: %d)",
                name, heroClass, currentHp, maxHp, armorClass);
    }
}