package messages;

import java.util.UUID;

public class Wheel {
    private final String name = "Wheel";
    private final boolean defected;
    private final UUID serial;

    public Wheel(boolean defected) {
        this.defected = defected;
        this.serial = UUID.randomUUID();
    }

    public boolean isDefected() {
        return defected;
    }

    @Override
    public String toString() {
        return "Wheel{" + "name='" + name + '\'' + ", defected=" + defected + ", serial=" + serial + '}';
    }
}
