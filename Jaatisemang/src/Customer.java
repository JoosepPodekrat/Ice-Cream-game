import java.util.List;

public class Customer {
    private String likedIceCream;
    private int WillingToBuy;
    private double MaxPrice;


Priit
    public Customer(String likedIceCream,int WillingToBuy,double MaxPrice) {
        this.likedIceCream = likedIceCream;
        this.WillingToBuy = WillingToBuy;
        this.MaxPrice = MaxPrice;

 Updated upstream
    public Customer(double money, List<String> likedIceCreams, String name) {
        this.money = money;
        this.likedIceCreams = likedIceCreams;
        this.name = name;

    public Customer(String likedIceCream,int WillingToBuy,double MaxPrice) {
        this.likedIceCream = likedIceCream;// Milline jäätis meeldib
        this.WillingToBuy = WillingToBuy;// Mitu on nõus ostma korraga
        this.MaxPrice = MaxPrice;// Max hind, millega on nõus ostma
 Stashed changes
 main
    }

    public void buyIceCream(Player player){
        player.Bought(likedIceCream,WillingToBuy,MaxPrice);
    }

    @Override
    public String toString(){
        return likedIceCream+"on lemmik ja on nõus ostma "+WillingToBuy;
    }


}
