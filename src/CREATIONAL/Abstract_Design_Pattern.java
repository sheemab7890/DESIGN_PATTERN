package CREATIONAL;

/*
 * Abstract Factory Pattern example
 *
 * Idea:
 * One factory creates a FULL matching meal set.
 * A meal set = FoodItem + Drink
 *
 * We have two families:
 * 1. Veg family
 * 2. Non-veg family
 *
 * Client only talks to FoodFactory.
 * Client never creates concrete classes directly.
 */

public class Abstract_Design_Pattern {

    public static void main(String[] args) {

        /*
         * Choose which family of objects you want.
         * Changing only this line switches the entire meal set.
         */
        FoodFactory foodFactory = new VegFoodFactory();
        // FoodFactory foodFactory = new NonVegFoodFactory();

        /*
         * Create related objects from the same family
         */
        FoodItem item = foodFactory.createFoodItems();
        Drink drink = foodFactory.createDrink();

        /*
         * Use the objects through interfaces
         */
        System.out.println(item.getName());
        System.out.println(drink.getName());
    }
}

/*
 * Product 1
 * Represents any food item in the system
 */
interface FoodItem {
    String getName();
}

/*
 * Product 2
 * Represents any drink in the system
 */
interface Drink {
    String getName();
}

/*
 * Veg family – food item
 */
class VegBurger implements FoodItem {

    @Override
    public String getName() {
        return "Veg Burger";
    }
}

/*
 * Veg family – drink
 */
class VegJuice implements Drink {

    @Override
    public String getName() {
        return "Fresh Veg Juice";
    }
}

/*
 * Non-veg family – food item
 */
class ChickenBurger implements FoodItem {

    @Override
    public String getName() {
        return "Chicken Burger";
    }
}

/*
 * Non-veg family – drink
 */
class ColdDrink implements Drink {

    @Override
    public String getName() {
        return "Coca-Cola";
    }
}

/*
 * Abstract Factory
 *
 * This defines what a complete meal factory must create.
 * It creates a family of related objects:
 *  - FoodItem
 *  - Drink
 */
interface FoodFactory {

    FoodItem createFoodItems();

    Drink createDrink();
}

/*
 * Concrete factory for Veg family
 * It always returns matching Veg objects
 */
class VegFoodFactory implements FoodFactory {

    @Override
    public FoodItem createFoodItems() {
        return new VegBurger();
    }

    @Override
    public Drink createDrink() {
        return new VegJuice();
    }
}

/*
 * Concrete factory for Non-veg family
 * It always returns matching Non-veg objects
 */
class NonVegFoodFactory implements FoodFactory {

    @Override
    public FoodItem createFoodItems() {
        return new ChickenBurger();
    }

    @Override
    public Drink createDrink() {
        return new ColdDrink();
    }
}




