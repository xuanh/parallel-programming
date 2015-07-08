package messages;


public class Wheel {
    private final String name = "Wheel";
    private final boolean defected;
    private final double serial;

    public Wheel(boolean defected) {
        this.defected = defected;
        this.serial = System.currentTimeMillis() + Math.random() * 100;
    }

    public String getName() {
        return name;
    }

    public boolean isDefected() {
        return defected;
    }

    public double getSerial() {
        return serial;
    }

    @Override
    public String toString() {
        return "Wheel{" + "name='" + name + '\'' + ", defected=" + defected + ", serial=" + serial + '}';
    }
}
