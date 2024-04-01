import java.util.List;

public class Customer {
    private String likedIceCream;
    private int WillingToBuy;
    private double MaxPrice;


    public Customer(String likedIceCream,int WillingToBuy,double MaxPrice) {
        this.likedIceCream = likedIceCream;
        this.WillingToBuy = WillingToBuy;
        this.MaxPrice = MaxPrice;
    }

    public void buyIceCream(Player player){
        player.Bought(likedIceCream,WillingToBuy,MaxPrice);
    }

    @Override
    public String toString(){
        return likedIceCream+"on lemmik ja on nõus ostma "+WillingToBuy;
    }


}
