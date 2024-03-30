public class IceCream {
    private String name; // name of ice cream
    private Double playerPrice; // how much it costs for player to buy
    private double customerPrice; // how much player can sell it to customer for
    private int inInventory;

    public IceCream(String name, Double playerPrice, double customerPrice, int inInventory) {
        this.name = name;
        this.playerPrice = playerPrice;
        this.customerPrice = customerPrice;
        this.inInventory = inInventory;
    }

    public int getInInventory() {
        return inInventory;
    }

    public void setInInventory(int inInventory) {
        this.inInventory = inInventory;
    }

    public String getName() {
        return name;
    }

    public Double getPlayerPrice() {
        return playerPrice;
    }

    public double getCustomerPrice() {
        return customerPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerPrice(Double playerPrice) {
        this.playerPrice = playerPrice;
    }

    public void setCustomerPrice(double customerPrice) {
        this.customerPrice = customerPrice;
    }
}


