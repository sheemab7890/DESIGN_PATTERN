package STRUCTURAL;

import java.util.ArrayList;
import java.util.List;

public class Composite_Design_Pattern {
    public static void main(String[] args) {
        // Create individual devices
        SmartComponent airConditioner = new AirConditioner();
        SmartComponent smartLight = new SmartLight();
        // Create a room and add devices
        CompositeSmartComponent room1 = new CompositeSmartComponent();
        room1.addComponent(airConditioner);
        room1.addComponent(smartLight);
        // Add more rooms for demonstration
        CompositeSmartComponent room2 = new CompositeSmartComponent();
        room2.addComponent(new AirConditioner());
        room2.addComponent(new SmartLight());
        // Create a floor and add rooms
        CompositeSmartComponent floor = new CompositeSmartComponent();
        floor.addComponent(room1);
        floor.addComponent(room2);
        // Create the house and add floors
        CompositeSmartComponent house = new CompositeSmartComponent();
        house.addComponent(floor);
        // Control the entire house
        System.out.println("Turning ON all devices in the house:");
        house.turnOn();
        System.out.println("Turning OFF all devices in the house:");
        house.turnOff();
        // Control a single floor
        System.out.println("Turning ON all devices on the first floor:");
        floor.turnOn();
        System.out.println("Turning OFF all devices on the first floor:");
        floor.turnOff();
        // Control a single room
        System.out.println("Turning ON all devices in Room 1:");
        room1.turnOn();
        System.out.println("Turning OFF all devices in Room 1:");
        room1.turnOff();
    }
}

// SmartComponent.java - Common interface for all components
interface SmartComponent {
    void turnOn();  // Turn on the component
    void turnOff(); // Turn off the component
}

// AirConditioner.java
class AirConditioner implements SmartComponent {
    @Override
    public void turnOn() {
        System.out.println("Air Conditioner is now ON.");
    }
    @Override
    public void turnOff() {
        System.out.println("Air Conditioner is now OFF.");
    }
}

// SmartLight.java
class SmartLight implements SmartComponent {
    @Override
    public void turnOn() {
        System.out.println("Smart Light is now ON.");
    }
    @Override
    public void turnOff() {
        System.out.println("Smart Light is now OFF.");
    }
}

// Create Composite Classes for Groups
//The composite classes represent groups of components (e.g., Room, Floor, House).
// Composite class for groups of components
class CompositeSmartComponent implements SmartComponent {
    private List<SmartComponent> components = new ArrayList<>();
    public void addComponent(SmartComponent component) {
        components.add(component);
    }
    public void removeComponent(SmartComponent component) {
        components.remove(component);
    }
    @Override
    public void turnOn() {
        for (SmartComponent component : components) {
            component.turnOn();
        }
    }
    @Override
    public void turnOff() {
        for (SmartComponent component : components) {
            component.turnOff();
        }
    }
}