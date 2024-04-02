import java.util.List;

public class Customer {
    private  double money;
    private List<String> likedIceCreams;
    private  String name;


<<<<<<< Updated upstream
    public Customer(double money, List<String> likedIceCreams, String name) {
        this.money = money;
        this.likedIceCreams = likedIceCreams;
        this.name = name;
=======
    public Customer(String likedIceCream,int WillingToBuy,double MaxPrice) {
        this.likedIceCream = likedIceCream;// Milline jäätis meeldib
        this.WillingToBuy = WillingToBuy;// Mitu on nõus ostma korraga
        this.MaxPrice = MaxPrice;// Max hind, millega on nõus ostma
>>>>>>> Stashed changes
    }

    public void buyIceCream(IceCream iceCream){
        this.money = this.money - iceCream.getCustomerPrice();
        System.out.println("Customer "+this.name  + " Bought 1 ice cream");
        //logic to remove 1 ice cream from player

    }


}
