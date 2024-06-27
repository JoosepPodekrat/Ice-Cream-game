package developers.icecreamgameui;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Player {
    private double currentMoney;
    private String name;

    private int VanillaNr = 0;
    private int ChocoNr = 0;
    private int PopsicleNr = 0;
    private double Vanilla_price = 0.6;
    private double Choco_price = 0.6;
    private double Popsicle_price = 0.4;
    private int CurrentDay = 0;
    private int GameDays = 1;

    private int SoldVanilla = 0;
    private int SoldChoco = 0;
    private int SoldPopsicle = 0;
    private double DailyMoneyMade = 0;
    private DoubleProperty currentMoneyProp = new SimpleDoubleProperty();

    public DoubleProperty getCurrentMoneyProperty() {

        return currentMoneyProp;
    }
    public double getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentDay(int currentDay) {
        CurrentDay = currentDay;
    }

    public void setCurrentMoney(double money) {
        currentMoney = (money);
    }

    public double getVanilla_price() {
        return Vanilla_price;
    }

    public double getChoco_price() {
        return Choco_price;
    }

    public double getPopsicle_price() {
        return Popsicle_price;
    }

    public void increaseMoney(double amount) {
        setCurrentMoney(getCurrentMoney() + amount);
    }

    public void decreaseMoney(double amount) {
        setCurrentMoney(getCurrentMoney() - amount);
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGameDays() {
        return GameDays;
    }

    public void setGameDays(int gameDays) {
        GameDays = gameDays;
    }

    public Player(double currentMoney, String name, int vanillaNr, int chocoNr, int popsicleNr, int currentDay,int gameDay, int soldVanilla, int soldChoco, int soldPopsicle) {
        this.currentMoney = currentMoney;
        this.name = name;
        this.VanillaNr = vanillaNr;
        this.ChocoNr = chocoNr;
        this.PopsicleNr = popsicleNr;
        this.CurrentDay = currentDay;
        this.GameDays = gameDay;
        this.SoldVanilla = soldVanilla;
        this.SoldChoco = soldChoco;
        this.SoldPopsicle = soldPopsicle;
    }

    public Player(double currentMoney, String name) {
        this.currentMoney = currentMoney;
        this.name = name;

    }

    public int getVanillaNr() {
        return VanillaNr;
    }

    public int getChocoNr() {
        return ChocoNr;
    }

    public int getPopsicleNr() {
        return PopsicleNr;
    }

    public void setVanillaNr(int vanillaNr) {
        VanillaNr = vanillaNr;
    }

    public void setChocoNr(int chocoNr) {
        ChocoNr = chocoNr;
    }

    public void setPopsicleNr(int popsicleNr) {
        PopsicleNr = popsicleNr;
    }

    public int getCurrentDay() {
        return CurrentDay;
    }

    public int getSoldVanilla() {
        return SoldVanilla;
    }

    public int getSoldChoco() {
        return SoldChoco;
    }

    public int getSoldPopsicle() {
        return SoldPopsicle;
    }

    public double getDailyMoneyMade() {
        return DailyMoneyMade;
    }


    public String getName() {
        return name;
    }

    public void setVanilla_price(double vanilla_price) {
        Vanilla_price = vanilla_price;
    }

    public void setChoco_price(double choco_price) {
        Choco_price = choco_price;
    }

    public void setPopsicle_price(double popsicle_price) {
        Popsicle_price = popsicle_price;
    }


    @Override
    public String toString() {
        return "Player{" +
                "currentMoney=" + currentMoney +
                ", name='" + name + '\'' +
                ", VanillaNr=" + VanillaNr +
                ", ChocoNr=" + ChocoNr +
                ", PopsicleNr=" + PopsicleNr +
                ", Vanilla_price=" + Vanilla_price +
                ", Choco_price=" + Choco_price +
                ", Popsicle_price=" + Popsicle_price +
                ", CurrentDay=" + CurrentDay +
                ", SoldVanilla=" + SoldVanilla +
                ", SoldChoco=" + SoldChoco +
                ", SoldPopsicle=" + SoldPopsicle +
                ", DailyMoneyMade=" + DailyMoneyMade +
                '}';
    }
    public void buyFromShop(){

    }
    public void buyVanilla(Balbiino balbiino,int kogus){
        if (balbiino.getVanilla_Sell_price()*kogus > currentMoney) {
            kogus = (int) Math.floor(currentMoney / balbiino.getVanilla_Sell_price());
        }
        currentMoney -= balbiino.getVanilla_Sell_price()*kogus;
        VanillaNr += kogus;
    }
    public void buyChoco(Balbiino balbiino,int kogus){
        if (balbiino.getChoco_Sell_price()*kogus > currentMoney) {
            kogus = (int) Math.floor(currentMoney / balbiino.getChoco_Sell_price());
        }
        currentMoney -= balbiino.getChoco_Sell_price()*kogus;
        ChocoNr += kogus;
    }
    public void buyPopsicle(Balbiino balbiino, int kogus){
        if (balbiino.getPopsicle_Sell_price()*kogus > currentMoney) {
            kogus = (int) Math.floor(currentMoney / balbiino.getPopsicle_Sell_price());
        }
        currentMoney -= balbiino.getPopsicle_Sell_price()*kogus;
        PopsicleNr += kogus;
    }

    public void Bought(String ice_cream, int WillingToBuy,double MaxPrice){
        if(ice_cream.equals("Choco")){
            if (Choco_price<=MaxPrice){
                if (ChocoNr >= WillingToBuy) {
                    ChocoNr -= WillingToBuy;
                    setCurrentMoney(currentMoney + WillingToBuy * Choco_price);
                    SoldChoco += WillingToBuy;
                    DailyMoneyMade += WillingToBuy * Choco_price;
                } else {
                    currentMoney += ChocoNr * Choco_price;//Puudumise puhul ei tule raha juurde 0*x
                    SoldChoco += ChocoNr;
                    DailyMoneyMade += ChocoNr * Choco_price;
                    ChocoNr -= ChocoNr;
                }
            }

        } else if (ice_cream.equals("Vanilla")) {
            if (Vanilla_price<=MaxPrice){
                if (VanillaNr >= WillingToBuy) {
                    VanillaNr -= WillingToBuy;
                    currentMoney += WillingToBuy * Vanilla_price;
                    SoldVanilla += WillingToBuy;
                    DailyMoneyMade += WillingToBuy * Vanilla_price;
                } else {
                    currentMoney += VanillaNr * Vanilla_price;//Puudumise puhul ei tule raha juurde 0*x
                    SoldVanilla += VanillaNr;
                    DailyMoneyMade += VanillaNr * Vanilla_price;
                    VanillaNr -= VanillaNr;
                }
            }
        } else {
            if (Popsicle_price<=MaxPrice){
                if (PopsicleNr >= WillingToBuy) {
                    PopsicleNr -= WillingToBuy;
                    currentMoney += WillingToBuy * Popsicle_price;
                    SoldPopsicle += WillingToBuy;
                    DailyMoneyMade += WillingToBuy * Popsicle_price;
                } else {
                    currentMoney += PopsicleNr * Popsicle_price;//Puudumise puhul ei tule raha juurde 0*x
                    SoldPopsicle += PopsicleNr;
                    DailyMoneyMade += PopsicleNr * Popsicle_price;
                    PopsicleNr -= PopsicleNr;
                }
            }
        }
    }
}
