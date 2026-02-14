package STRUCTURAL;

public class Bridege_Design_Pattern {
    public static void main(String[] args) {

    Shape blueSquare = new Square(new BlueColor());
    blueSquare.draw();

    Shape greenSquare = new Square(new GreenColor());
    greenSquare.draw();

    }
}


/*
 * Implementor
 * Defines interface for implementation classes
 */
interface Color {
    void applyColor();
}

class RedColor implements Color {

    @Override
    public void applyColor() {
        System.out.println("Applying Red Color");
    }
}

class GreenColor implements Color{

    @Override
    public void applyColor() {
        System.out.println("Applying Green Color");
    }
}

class BlueColor implements Color {

    @Override
    public void applyColor() {
        System.out.println("Applying Blue Color");
    }
}


/*
 * Abstraction
 * Has reference to implementation
 */
abstract class Shape {

    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    abstract void draw();
}

// Concrete implementation of abstraction
class Circle extends Shape {

    public Circle(Color color) {
        super(color);
    }

    @Override
    void draw() {
        System.out.print("Drawing Circle with ");
        color.applyColor();
    }
}


class Rectangle extends Shape {

    public Rectangle(Color color) {
        super(color);
    }

    @Override
    void draw() {
        System.out.print("Drawing Rectangle with ");
        color.applyColor();
    }
}

class Square extends Shape{

    public Square(Color color){
        super(color);
    }

    @Override
    void draw() {
        System.out.print("Drawing Square with ");
        color.applyColor();
    }
}
