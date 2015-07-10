package messages;

import java.util.List;
import java.util.UUID;

public class Car {
    private final String name = "car";
    private final Engine engine;
    private final List<Wheel> wheels;
    private final UUID serial;

    public Car(Engine engine, List<Wheel> wheels) {
        this.engine = engine;
        this.wheels = wheels;
        this.serial = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Car{" + "name='" + name + '\'' + ", engine=" + engine + ", wheels=" + wheels + ", serial=" + serial + '}';
    }
}
