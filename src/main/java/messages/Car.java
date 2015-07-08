package messages;

import java.util.List;

public class Car {
    private final String name = "car";
    private final Engine engine;
    private final List<Wheel> wheels;
    private final double serial;

    public Car(Engine engine, List<Wheel> wheels) {
        this.engine = engine;
        this.wheels = wheels;
        this.serial = System.currentTimeMillis() + Math.random() * 100;
    }
}
