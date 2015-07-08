package messages;


public class Engine {
    private final String name = "Engine";
    private final boolean defected;
    private final double serial;

    public Engine(boolean defected) {
        this.defected = defected;
        serial = System.currentTimeMillis() + Math.random() * 100;
    }

    public boolean isDefected() {
        return defected;
    }

    public String getName() {
        return name;
    }

    public double getSerial() {
        return serial;
    }

    @Override
    public String toString() {
        return "Engine{" + "name='" + name + '\'' + ", defected=" + defected + ", serial=" + serial + '}';
    }
}
