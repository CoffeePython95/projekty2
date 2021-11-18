public class Player {
    private int attackPower;
    private int hitChance; //percent
    private int maxHealth;
    private int healingPower;


    public Player() {
        this.attackPower = 10;
        this.hitChance = 90;
        this.maxHealth = 100;
        this.healingPower = 10;
    }


    public int getAttackPower() {
        return this.attackPower;
    }

    public int getHitChance() {
        return hitChance;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public int getHealingPower() {
        return healingPower;
    }

    public void increaseAttackPower(int increase) {
        this.attackPower += increase;
    }

    public void increaseMaxHealth(int increase) {
        this.maxHealth += increase;
    }

    public void increaseHealingPower(int increase) {
        this.healingPower += increase;
    }
}
