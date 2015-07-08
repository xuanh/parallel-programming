package messages;

public class Produce {
    private final int defectPercentage;

    public Produce(int defectPercentage) {
        this.defectPercentage = defectPercentage;
    }

    public int getDefectPercentage() {
        return defectPercentage;
    }
}
