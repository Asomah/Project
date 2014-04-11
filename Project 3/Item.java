public class Item{

    //
    // Public
    //
    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Locale getItem() {
        return item;
    }
    public void setItem(Locale item) {
        this.item = item;
    }

    public boolean getHasTaken() {
        return hasTaken;
    }
    public void setHasTaken(boolean hasTaken) {
        this.hasTaken = hasTaken;
    }




    public String toString() {
        return "Item name=" + this.name
                + " desc=" + this.desc
                + "]";
    }

    //
    // Private
    //
    private Locale item;
    private String name;
    private String desc;
    private int id;
    private boolean hasTaken = false;

}
