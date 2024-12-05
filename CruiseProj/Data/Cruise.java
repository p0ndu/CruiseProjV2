package CruiseProj.Data;

public class Cruise {
    private final Ship ship;
    private final int maxExcursions = 3; // just making max excursions for now, will add proper functionality to this later to allow for 3 per week but more total
    private Excursion[] excursions;

    public Cruise(Ship ship){
        this.ship = ship;
        this.excursions = new Excursion[maxExcursions];
    }



    public Ship getShip(){
        return this.ship;
    }   
    

    public Excursion[] getExcursions(){
        return this.excursions;
    }  

    public Excursion getExcursion(int index){
        return this.excursions[index];
    }

    public int getNumExcursions(){
        return maxExcursions;
    }

    // -- setters -- 
    public void addExcursion(Excursion excursion){ // adds excursion if there is room
        boolean added = false;
        for(int i = 0; i < excursions.length; i++){
            if(excursions[i] == null){
                excursions[i] = excursion;
                i = excursions.length;
                added = true;
            }
        }
        if(!added){
            System.out.println("Excursions are full");
        }
    }
}
