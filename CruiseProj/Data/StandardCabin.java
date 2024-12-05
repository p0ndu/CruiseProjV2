package CruiseProj.Data;

public class StandardCabin extends Cabin {
    private final boolean hasSeaView;
    private final int maxPassengers = 6;
    private final String type  = "Standard Cabin";

    public StandardCabin(boolean hasSeaView, int number){
        super(number, 6);
        this.hasSeaView = hasSeaView;
    }

    // -- getters --

    public boolean getHasSeaView(){
        return this.hasSeaView;
    }

    public String getType(){
        return this.type;
    }

    public boolean hasSpace(){
        return super.getNumPassengers() < maxPassengers;
    }
}
