import java.util.Scanner;

public class Game {
    static Player player = new Player();
    static int health = player.getMaxHealth();
    static int healingPotions = 3;
    static int gold = 0;
    static int keys = 0;
    final static Enemy[] enemies = {
            new Enemy("Rat", 3, 10, 5),
            new Enemy("Slime", 1, 50, 5),
            new Enemy("Rabid Dog", 10, 30, 10),
            new Enemy("Skeleton", 15, 25, 15),
            new Enemy("Zombie", 12, 40, 15),
            new Enemy("Prisoner", 20, 50, 20),
            new Enemy("Corpse Eater", 25, 50, 25)};

    public static void main(String[] args) {
        intro();

        while(health>0) {
            do {
                rest();
            } while(getRandom() > 95);

            encounter();

            if(canLeave()) {
                break;
            }
        }
        if(health > 0) {
            System.out.printf("\nYou return from dungeon. On your way back you also found %s gold, but most importantly, you were able to go back alive", gold);
        } else {
            System.out.println("\nYou died. THE END.");
        }
    }

    private static boolean canLeave() {
        if(keys > 0) {
            System.out.println("\t> You remember picking up a key. Maybe it will open door you entered through and let you leave this horrid place?");
            System.out.println("\t1. Try leaving");
            System.out.println("\t2. Continue dungeon exploration");
            int leave = getInput();
            if (leave == 1) {
                return leave();
            }
        }
        return false;
    }

    private static void intro() {
        System.out.println("You see entrance to dungeon slowly inch open. It has never happened before, you slowly get closer.");
        System.out.println("\t1. Try to check what's inside");
        if (getInput() == 1) {
            System.out.println("You open the door and come inside. You hear loud slam and door behind " +
                    "you is now locked. Maybe you will find key to the doors somewhere inside?");
        } else {
            System.out.println("You stay unmoved for a good while. Before you decided what to do, " +
                    "you felt strong shove from behind sending you straight inside the dungeon." +
                    "Door slams behind you before you managed to realize what just happened." +
                    "They are locked. Maybe you will find key to the doors somewhere inside?");
        }
        System.out.println("You pick metal rod from floor near entrance and head deeper inside.\nYou officially entered the dungeon.");
    }

    private static boolean leave() {
        for (int i = keys; i > 0; i--) {
            if(getRandom() < 70) {
                System.out.println("Key opened entrance door, you may finally leave");
                return true;
            }
        }
        System.out.println("None of the keys you have on yourself could open entrance door. You continue exploration.");
        return false;
    }

    private static void encounter() {
        switch ((int) (Math.random() * 20)) {
            case 0, 1 -> miracleFountain();
            case 2 -> trap();
            case 3, 4, 5, 6 -> treasureChest();
            case 7 -> foundKey();
            default -> fight();
        }
    }

    private static void rest() {
        System.out.println("\n\t> You travel through empty corridors. It gives you enough time to take a breather and think about future actions.");
        if(health < player.getMaxHealth()) {
            System.out.println("\t> You restored some health.");
            heal(player.getHealingPower());
        }
    }

    private static String generateEnemyPrefix(double difficulty) {
        String enemyPrefix;
        if(difficulty <= 5) {
            enemyPrefix = switch(getRandom(10) ) {
                case 1 -> "Deformed ";
                case 2 -> "Sick ";
                case 3 -> "Beaten ";
                case 4 -> "Tiny ";
                case 5 -> "Malnourished ";
                default -> "Feeble ";
            };
        } else if(difficulty <= 15) {
            enemyPrefix = switch(getRandom(10)) {
                case 1 -> "Scared ";
                case 2 -> "Young ";
                case 3 -> "Hurt ";
                case 4 -> "Tired ";
                case 5 -> "Hungry ";
                default -> "Weak";
            };
        } else if(difficulty <= 85) {
            enemyPrefix = switch(getRandom(10)) {
                case 1 -> "Hideous ";
                case 2 -> "Unsightly ";
                case 3 -> "Usual ";
                case 4 -> "Weird ";
                case 5 -> "Normal ";
                default -> "";
            };
        } else if(difficulty <= 95) {
            enemyPrefix = switch(getRandom(10)) {
                case 1 -> "Elder ";
                case 2 -> "Bigger ";
                case 3 -> "Scarred ";
                case 4 -> "Healthy ";
                case 5 -> "Unstable ";
                default -> "Strong ";
            };
        } else {
            enemyPrefix = switch(getRandom(10)) {
                case 1 -> "Humongous ";
                case 2 -> "Hulking ";
                case 3 -> "Enhanced ";
                case 4 -> "Experienced ";
                case 5 -> "Dangerous ";
                default -> "Tyrannical ";
            };
        }
        return enemyPrefix;
    }

    private static double getScaling(double difficulty) {
        if(difficulty <= 5) {
            return 0.7;
        } else if(difficulty <= 15) {
            return 0.9;
        } else if(difficulty <= 85) {
            return 1;
        } else if(difficulty <= 95) {
            return 1.1;
        } else {
            return 1.4;
        }
    }

    private static void fight() {
        String enemyName;
        int enemyDamage;
        int enemyHealth;
        int goldDropped;
        int choice;

        int enemyIndex = (int) (Math.random() * (enemies.length));
        double difficulty = (Math.random() * 100) + 1;
        double scaling = getScaling(difficulty);

        enemyName = generateEnemyPrefix(difficulty) + enemies[enemyIndex].enemyName();
        enemyDamage = (int)(enemies[enemyIndex].damage() * scaling);
        enemyHealth = (int)(enemies[enemyIndex].hitPoints() * scaling);
        goldDropped = (int)(enemies[enemyIndex].goldDropped() * scaling);

        System.out.printf("\n\t# %s has appeared #\n", enemyName);
        fightLoop:
        while(enemyHealth > 0 && health > 0) {
            System.out.printf("\tYour HP: %s\n", health);
            System.out.printf("\t%s's HP: %s\n\n", enemyName, enemyHealth);
            System.out.println("\t1. Attack " + enemyName);
            System.out.println("\t2. Drink health potion (" + healingPotions + " remaining)");
            System.out.println("\t3. Flee");
            choice = getInput();
            switch(choice) {
                case 1:
                    enemyHealth = combat(enemyName, enemyDamage, enemyHealth);
                    break;
                case 2:
                    drinkPotion();
                    enemyHit(enemyName, enemyDamage);
                    break;
                case 3:
                    if(Math.random() * player.getMaxHealth() < health * 0.9) {
                        System.out.println("\t> You fled from enemy.");
                        break fightLoop;
                    } else {
                        System.out.println("\t> You failed to escape.");
                        enemyHit(enemyName, enemyDamage);
                    }
                    break;
                default:
                    miss();
            }
        }
        if(enemyHealth <= 0) {
            System.out.printf("\t> You found %s gold.\n", goldDropped);
            gold += goldDropped;
            if (getRandom() < 30) {
                System.out.println("\t> You also found magic potion.");
                healingPotions++;
            }
            if (getRandom() < 10) {
                System.out.println("\t> You also found key.");
                keys++;
            }
        }
    }

    private static int combat(String enemyName, int enemyDamage, int enemyHealth) {
        if(getRandom() > player.getHitChance()) {
            miss();
        } else {
            System.out.printf("\t> You strike %s for %d damage.\n", enemyName, player.getAttackPower());
            enemyHealth -= player.getAttackPower();
        }
        if(enemyHealth <= 0) {
            System.out.printf("\t> %s died.\n", enemyName);
        } else {
            enemyHit(enemyName, enemyDamage);
        }
        return enemyHealth;
    }

    private static void enemyHit(String enemyName, int enemyDamage) {
        if(getRandom() > 70) {
            System.out.printf("\t> %s misses.\n", enemyName);
        } else {
            System.out.printf("\t> %s hits you dealing %d damage\n", enemyName, enemyDamage);
            health -= enemyDamage;
        }
    }

    private static void drinkPotion() {
        if(healingPotions > 0) {
            if(player.getMaxHealth() < health + 50 ) {
                System.out.println("\t> You healed yourself by: " + (player.getMaxHealth() - health));
            } else {
                System.out.println("\t> You healed yourself by 50 hit points");
            }
            heal(50);
            healingPotions--;
        } else {
            System.out.println("\t> You fumble around your belt trying to grab healing potion. Shame there isn't any.");
        }
    }

    private static void miss() {
        switch (getRandom(10)) {
            case 1 -> System.out.println("\t> You forgot what you wanted to do and let enemy do whatever he wanted.");
            case 2 -> System.out.println("\t> You stumbled over your own leg. Thankfully you didn't fall.");
            case 3 -> {
                System.out.println("\t> Trying to dodge you bent your back way too much and fell, not having time to deal any damage to enemy." +
                        " Old back pains came back (health - 1).");
                health -= 1;
            }
            case 4 -> System.out.println("\t> Enemy dodged your attack.");
            case 5 -> System.out.println("\t> You stylishly grazed enemy dealing no real damage.");
            case 6 -> System.out.println("\t> You bravely charged into enemy. Shame you are shortsighted and missed him by quite length.");
            case 7 -> System.out.println("\t> Waving your weapon frantically in front of you made enemy feel a little colder. At least that what you think it would feel.");
            case 8 -> {
                System.out.println("\t> You hit your own leg (health - 5.");
                health -=5;
            }
            case 9 -> System.out.println("\t> You believed in having secret super power way too much. You don't have any sadly.");
            default -> System.out.println("\t> You missed your hit.");
        }
    }

    private static void miracleFountain() {
        health = player.getMaxHealth();
        System.out.println("\nYou have stumbled upon fountain. It's mere aura heals your all injuries. Your health is at it's max (" + health + ").");
        System.out.println("\t> Do you want to drink from it?");
        System.out.println("\t1. Yes");
        System.out.println("\t2. No");
        switch(getInput()) {
            case 1:
                if(getRandom() < 95) {
                    System.out.println("Drinking from fountain made you feel stronger. Water in it all dried up just after you finished taking a sip.");
                    getBoon();
                } else {
                    System.out.println("After drinking cup worth of fountain water its contents turn blood red. You feel churning in stomach (health - 15).");
                    health -= 15;
                }
                break;
            case 2:
                System.out.println("You decisively leave fountain area. When you look behind there is no sign of any fountain left.");
                break;
            default:
                System.out.println("Before you decided what to do with fountain, it has disappeared turning into mirage.");
                break;
        }

    }

    private static void getBoon() {
        switch (getRandom(20)) {
            case 1 -> {
                System.out.println("\t> Damage increased by 5 points");
                player.increaseAttackPower(5);
            }
            case 2 -> {
                System.out.println("\t> Damage increased by 10 points");
                player.increaseAttackPower(10);
            }
            case 3 -> {
                System.out.println("\t> Regeneration increased by 5 points");
                player.increaseHealingPower(5);
            }
            case 4 -> {
                System.out.println("\t> Regeneration increased by 10 points");
                player.increaseHealingPower(10);
            }
            case 5 -> {
                System.out.println("\t> Max health increased by 25 points");
                player.increaseMaxHealth(5);
                health = player.getMaxHealth();
            }
            case 6 -> {
                System.out.println("\t> Max health increased by 10 points");
                player.increaseMaxHealth(15);
                health = player.getMaxHealth();
            }
            case 7 -> {
                System.out.println("\t> Max health increased by 20 points");
                player.increaseMaxHealth(20);
                health = player.getMaxHealth();
            }
            case 8 -> {
                System.out.println("\t> Max health increased by 25 points");
                player.increaseMaxHealth(25);
                health = player.getMaxHealth();
            }
            default -> {
                System.out.println("\t> Max health increased by 10 points");
                player.increaseMaxHealth(10);
                health = player.getMaxHealth();
            }
        }
    }

    private static void trap() {
        System.out.println("\nYou fell into a trap while walking! (health - 10)");
        health -= 10;
    }

    private static void treasureChest() {
        System.out.println("\nYou have stumbled upon chest. Something useful may be inside.");
        System.out.println("\t> Do you want to open the chest?");
        System.out.println("\t1. Yes");
        System.out.println("\t2. No");
        switch(getInput()) {
            case 1:
                if(getRandom() < 90) {
                    if (getRandom() < 10) {
                        System.out.println("\t> You found a key!");
                        keys++;
                    } else {
                        System.out.println("You see many different kinds of weapons, trinkets and pieces of armor.\n" +
                                "At the moment you picked one you liked the most, chest with its contents disappears with audible *poof*.");
                        getBoon();
                    }
                } else {
                    System.out.println("When you pried the chest open and tried to check what's inside, lid closed crushing your fingers. Damned mimics! (health - 15).");
                    health -= 15;
                }
                break;
            case 2:
                System.out.println("You decisively leave the chest behind. When you look behind there is no sign of any chest left.");
                break;
            default:
                System.out.println("Before you decided what to do with chest, it has disappeared turning black cloud.");
                break;
        }
    }

    private static void foundKey() {
        System.out.println("\n\t> Walking around you found a key on the ground. You pick it up.");
        keys++;
    }

    private static void heal(int amount) {
        health += amount;
        if(health > player.getMaxHealth()) {
            health = player.getMaxHealth();
        }
    }

    private static int getRandom() {
        return (int)(Math.random() * 100) + 1;
    }

    private static int getRandom(int top) {
        return (int)(Math.random() * top) + 1;
    }

    private static int getInput() {
        try {
            return new Scanner(System.in).nextInt();
        } catch (Exception e ) {
            return 13;
        }
    }
}
