package BEHAVIORAL;

public class Template_Design_Pattern {
    public static void main(String[] args) {
    Beverage tea = new TeaBeverage();
    tea.prepareRecipe();

    Beverage coffee = new CoffeeBeverage();
    coffee.prepareRecipe();

    }
}

// TRADITIONAL APPROACH
// Making Coffee the old-fashioned way
class Coffee {
    public void prepare() {
        boilWater();
        brewCoffee();
        pourInCup();
        addSugarAndMilk();
    }
    private void boilWater() {
        System.out.println("Boiling water...");
    }
    private void brewCoffee() {
        System.out.println("Brewing coffee...");
    }
    private void pourInCup() {
        System.out.println("Pouring into cup...");
    }
    private void addSugarAndMilk() {
        System.out.println("Adding sugar and milk...");
    }
}

// Making Tea the old-fashioned way
class Tea {
    public void prepare() {
        boilWater();
        steepTeaBag();
        pourInCup();
        addLemon();
    }
    private void boilWater() {
        System.out.println("Boiling water...");
    }
    private void steepTeaBag() {
        System.out.println("Steeping tea bag...");
    }
    private void pourInCup() {
        System.out.println("Pouring into cup...");
    }
    private void addLemon() {
        System.out.println("Adding lemon...");
    }
}

// USING TEMPLATE DESIGN PATTERN
// Our abstract template that defines the skeleton of beverage preparation
abstract class Beverage {
    // The template method - makes sure the algorithm steps are followed
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }
    // Common methods
    void boilWater() {
        System.out.println("Boiling water...");
    }
    void pourInCup() {
        System.out.println("Pouring into cup...");
    }
    // Steps to be customized by subclasses
    abstract void brew();
    abstract void addCondiments();
}

// Concrete implementation for Coffee
class CoffeeBeverage extends Beverage {
    @Override
    void brew() {
        System.out.println("Brewing coffee...");
    }
    @Override
    void addCondiments() {
        System.out.println("Adding sugar and milk...");
    }
}

// Concrete implementation for Tea
class TeaBeverage extends Beverage {
    @Override
    void brew() {
        System.out.println("Steeping tea bag...");
    }
    @Override
    void addCondiments() {
        System.out.println("Adding lemon...");
    }
}