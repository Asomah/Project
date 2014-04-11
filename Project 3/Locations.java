
public class Locations {

    //
    // Public
    //
    public Locations(Locale x) {
        thisLocale = x;

    }

    public Locations getNorth() {
        return north;
    }
    public void setNorth(Locations north) {
        this.north = north;
    }

    public Locations getSouth() {
        return south;
    }
    public void setSouth(Locations south) {
        this.south = south;
    }
    public Locations getEast() {
        return east;
    }
    public void setEast(Locations east) {
        this.east = east;
    }
    public Locations getWest() {
        return west;
    }
    public void setWest(Locations west) {
        this.west = west;
    }
    public Locale getThisLocale() {
        return thisLocale;
    }
    public void setThisLocale(Locale l) {
        this.thisLocale = l;
    }


    private Locale thisLocale;
    private Locations north=null;
    private Locations south=null;
    private Locations east=null;
    private Locations west=null;
}
