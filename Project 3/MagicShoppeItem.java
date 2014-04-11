public class MagicShoppeItem {

    // Constructor
    public MagicShoppeItem() {

    }

    // Getters and Setters

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public MagicShoppeItem getNext() {
        return next;
    }
    public void setNext(MagicShoppeItem next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "[MagicShoppeItem name=" + this.name
                + " price=" + this.price + "]";
    }
    //private
    private String name;
    private double price;
    MagicShoppeItem next;

}
