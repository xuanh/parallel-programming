package messages;

import java.util.UUID;

public class Coachwork {
    private final String name = "Coachwork";
    private final boolean defected;
    private final UUID serial;

    public Coachwork(boolean defected) {
        this.defected = defected;
        this.serial = UUID.randomUUID();
    }

    public boolean isDefected() {
        return defected;
    }

    @Override
    public String toString() {
        return "Coachwork{" + "name='" + name + '\'' + ", defected=" + defected + ", serial=" + serial + '}';
    }
}
