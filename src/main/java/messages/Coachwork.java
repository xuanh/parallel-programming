package messages;

public class Coachwork {
    private final String name = "Coachwork";
    private final boolean defected;
    private final double serial;

    public Coachwork(boolean defected) {
        this.defected = defected;
        this.serial = System.currentTimeMillis() + Math.random() * 100;
    }

    public boolean isDefected() {
        return defected;
    }

    @Override
    public String toString() {
        return "Coachwork{" +
                "name='" + name + '\'' +
                ", defected=" + defected +
                ", serial=" + serial +
                '}';
    }
}
