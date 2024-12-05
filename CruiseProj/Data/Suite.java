package CruiseProj.Data;

public class Suite extends Cabin {

    private final boolean hasBalcony;
    private final int maxPassengers = 4;
    private final String type = "Suite";

    public Suite(boolean hasBalcony, int number) {
        super(number, 4);
        this.hasBalcony = hasBalcony;
    }

    // -- getters --
    public boolean getHasBalcony() {
        return this.hasBalcony;
    }

    public String getType() {
        return this.type;
    }

    public boolean hasSpace() {
        return super.getNumPassengers() < maxPassengers;
    }

}
