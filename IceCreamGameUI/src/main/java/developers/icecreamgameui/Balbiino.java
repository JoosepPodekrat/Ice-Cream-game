package developers.icecreamgameui;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Balbiino {
    private double Vanilla_Sell_price;
    private double Choco_Sell_price;
    private double Popsicle_Sell_price;

    Balbiino(double Vanilla_Sell_price,double Choco_Sell_price,double Popsicle_Sell_price){
        this.Popsicle_Sell_price = Popsicle_Sell_price;
        this.Choco_Sell_price = Choco_Sell_price;
        this.Vanilla_Sell_price = Vanilla_Sell_price;
    }
    public double getChoco_Sell_price() {
        return Choco_Sell_price;
    }
    public double getPopsicle_Sell_price() {
        return Popsicle_Sell_price;
    }
    public double getVanilla_Sell_price() {
        return Vanilla_Sell_price;
    }

    public void setChoco_Sell_price(double choco_Sell_price) {
        Choco_Sell_price = choco_Sell_price;
    }
    public void setPopsicle_Sell_price(double popsicle_Sell_price) {
        Popsicle_Sell_price = popsicle_Sell_price;
    }
    public void setVanilla_Sell_price(double vanilla_Sell_price) {
        Vanilla_Sell_price = vanilla_Sell_price;
    }
    public void changePrices(){

        setChoco_Sell_price(getRoundedCurrentMoney(Math.random()*0.5 + 0.5));
        setVanilla_Sell_price(getRoundedCurrentMoney(Math.random()*0.5 + 0.5));
        setPopsicle_Sell_price(getRoundedCurrentMoney(Math.random()*0.5 + 0.35));
    }
    public double getRoundedCurrentMoney(double a) {
        BigDecimal bd = BigDecimal.valueOf(a);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public String toString(){
        return "Vanilla price: "+Vanilla_Sell_price+";Chocolate price: "+Choco_Sell_price+";Popsicle price: "+Popsicle_Sell_price;
    }
}
