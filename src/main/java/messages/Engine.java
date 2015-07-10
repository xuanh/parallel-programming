package messages;

import java.util.UUID;

public class Engine {
    private final String name = "Engine";
    private final boolean defected;
    private final UUID serial;

    public Engine(boolean defected) {
        this.defected = defected;
        serial = UUID.randomUUID();
    }

    public boolean isDefected() {
        return defected;
    }

    @Override
    public String toString() {
        return "Engine{" + "name='" + name + '\'' + ", defected=" + defected + ", serial=" + serial + '}';
    }
}
