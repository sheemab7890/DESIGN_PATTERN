package CREATIONAL;

public class Prototype_Design_Pattern {
    public static void main(String[] args) {
        CharacterFactory factory = new CharacterFactory();
        Character charterWithNewName = factory.createCharacterWithNewName("Sheemab");
        charterWithNewName.showCharacterInfo();
    }
}

// Traditional  Approach
class Character {

    private String name;
    private int health;
    private int attackPower;
    private int level;

    public Character(String name, int health, int attackPower, int level) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.level = level;
    }

    public void showCharacterInfo() {
        System.out.println("Character [Name=" + name + ", Health=" + health
                + ", AttackPower=" + attackPower + ", Level=" + level + "]");
    }
}

class CharacterFactory {
    // Creating a new character each time with similar attributes
    public Character createCharacterWithNewName(String name) {
        // Creating a new character with the same attributes, just changing the name
        return new Character(name, 100, 50, 1); // Default attributes for simplicity
    }

    public Character createCharacterWithNewLevel(int level) {
        // Creating a new character with the same attributes, just changing the
        // level
        return new Character(
                "DefaultName", 100, 50, level); // Default name and attributes
    }

    public Character createCharacterWithNewAttackPower(int attackPower) {
        // Creating a new character with the same attributes, just changing the
        // attack power
        return new Character(
                "DefaultName", 100, attackPower, 1); // Default name and level
    }
}


// The saviour PROTOTYPE Pattern
class Characterr implements Cloneable {

    private String name;
    private int health;
    private int attackPower;
    private int level;

    public Characterr(String name, int health, int attackPower, int level) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.level = level;
    }

    @Override
    public Characterr clone() {
        try {
            return (Characterr) super.clone();   // shallow copy (enough here)
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    // setters only for fields you want to change after cloning
    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void showCharacterInfo() {
        System.out.println("Character [Name=" + name
                + ", Health=" + health
                + ", AttackPower=" + attackPower
                + ", Level=" + level + "]");
    }
}


class CharacterrFactory {

    private final Characterr prototypeCharacter;

    // default prototype
    public CharacterrFactory() {
        this.prototypeCharacter =
                new Characterr("DefaultName", 100, 50, 1);
    }

    // change only name
    public Characterr createCharacterWithNewName(String name) {

        Characterr cloned = prototypeCharacter.clone();
        cloned.setName(name);
        return cloned;
    }

    // change only level
    public Characterr createCharacterWithNewLevel(int level) {

        Characterr cloned = prototypeCharacter.clone();
        cloned.setLevel(level);
        return cloned;
    }

    // change only attack power
    public Characterr createCharacterWithNewAttackPower(int attackPower) {

        Characterr cloned = prototypeCharacter.clone();
        cloned.setAttackPower(attackPower);
        return cloned;
    }
}


/*
class Characterr {

    private String name;
    private int health;
    private int attackPower;
    private int level;

    public Characterr(String name, int health, int attackPower, int level) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.level = level;
    }

    // 👇 instead of clone()
    public Characterr(Characterr other) {
        this.name = other.name;
        this.health = other.health;
        this.attackPower = other.attackPower;
        this.level = other.level;
    }
}

Usage:
Characterr copy = new Characterr(original);


✅ Option 2 – copy() method (prototype style, no Cloneable)
public Characterr copy() {
    return new Characterr(this);
}

Then you use:
Characterr copy = original.copy();


class Customer {

    String name;

    Customer(String name) {
        this.name = name;
    }

    // helper for deep copy
    Customer copy() {
        return new Customer(this.name); 👉 make a new Customer object with same name
    }
}
class Order {

    int orderId;
    Customer customer;

    Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    // ---------- DEEP COPY ----------
    Order deepCopy() {
        return new Order(this.orderId, this.customer.copy()); 👉 create a new Order and give it a new Customer
    }
}
public class DeepCopyDemo {

    public static void main(String[] args) {

        Customer customer = new Customer("Sheemab");

        Order original = new Order(1, customer);
        Order copy = original.deepCopy();

        // Change only in copied order
        copy.customer.name = "Azeez";

        System.out.println("Original customer : " + original.customer.name);
        System.out.println("Copy customer     : " + copy.customer.name);
    }
}
 */

