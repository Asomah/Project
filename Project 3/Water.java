public class Water extends Locale {

    //
    // -- PRIVATE --
    //

    // Constructor
    public Water(int id) {
        super(id);
    }

    // Getters and Setters
    public void setNearestLagoon(String value) {
        this.nearestLagoon = value;
    }
    public String getNearestLagoon() {
        return this.nearestLagoon;
    }

    // Other methods
    @Override
    public String toString() {
        return "Water..." + super.toString() + " nearestLagoon=" + this.nearestLagoon;
    }


    //
    // -- PRIVATE --
    //
    private String nearestLagoon;

}