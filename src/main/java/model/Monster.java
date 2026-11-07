package model;

public class Monster extends Entity {
    private String type;

    public Monster() {
        super();
    }

    public Monster(String name, String type, int maxHp, int armorClass, int attackBonus, String damageDice) {
        super(name, maxHp, armorClass, attackBonus, damageDice);
        this.type = type;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return String.format("Monstre: %s (Type: %s, PV: %d/%d, AC: %d)",
                name, type, currentHp, maxHp, armorClass);
    }
}