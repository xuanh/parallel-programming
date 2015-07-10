package messages;

import java.util.List;
import java.util.UUID;

public class Car {
    private final String name = "car";
    private final Engine engine;
    private final List<Wheel> wheels;
    private final Coachwork coachwork;
    private final UUID serial;

    public Car(Engine engine, List<Wheel> wheels, Coachwork coachwork) {
        this.engine = engine;
        this.wheels = wheels;
        this.coachwork = coachwork;
        this.serial = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Car{" + "name='" + name + '\'' + ", engine=" + engine + ", wheels=" + wheels + ", coachwork=" + coachwork + ", serial=" + serial + '}';
    }
}
