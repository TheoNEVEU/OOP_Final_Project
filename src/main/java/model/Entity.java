package model;

public abstract class Entity {
    protected String name;
    protected int maxHp;
    protected int currentHp;
    protected int armorClass;
    protected int attackBonus;
    protected String damageDice;

    public Entity() {}

    public Entity(String name, int maxHp, int armorClass, int attackBonus, String damageDice) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.armorClass = armorClass;
        this.attackBonus = attackBonus;
        this.damageDice = damageDice;
    }

    public void takeDamage(int amount) {
        if (amount > 0) {
            this.currentHp -= amount;
            if (this.currentHp < 0) {
                this.currentHp = 0;
            }
        }
    }

    public boolean isAlive() {
        return this.currentHp > 0;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getMaxHp() { return maxHp; }
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
        this.currentHp = maxHp;
    }

    public int getCurrentHp() { return currentHp; }
    public void setCurrentHp(int currentHp) { this.currentHp = currentHp; }

    public int getArmorClass() { return armorClass; }
    public void setArmorClass(int armorClass) { this.armorClass = armorClass; }

    public int getAttackBonus() { return attackBonus; }
    public void setAttackBonus(int attackBonus) { this.attackBonus = attackBonus; }

    public String getDamageDice() { return damageDice; }
    public void setDamageDice(String damageDice) { this.damageDice = damageDice; }
}