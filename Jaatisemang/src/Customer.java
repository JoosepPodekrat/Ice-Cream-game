import java.util.List;

public class Customer {
    private  double money;
    private List<String> likedIceCreams;
    private  String name;


    public Customer(double money, List<String> likedIceCreams, String name) {
        this.money = money;
        this.likedIceCreams = likedIceCreams;
        this.name = name;
    }

    public void buyIceCream(IceCream iceCream){
        this.money = this.money - iceCream.getCustomerPrice();
        System.out.println("Customer "+this.name  + " Bought 1 ice cream");
        //logic to remove 1 ice cream from player

    }


}
