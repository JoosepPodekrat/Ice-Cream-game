import java.util.List;
import javax.swing.JOptionPane;

public class Player {
    private double currentMoney;
    private String name;

    private int VanillaNr = 0;
    private int ChocoNr = 0;
    private int PopsicleNr = 0;
    private double Vanilla_price;
    private double Choco_price;
    private double Popsicle_price;

    private int SoldVanilla;
    private int SoldChoco;
    private int SoldPopsicle;
    private double DailyMoneyMade;


    public Player(double currentMoney, String name) {
        this.currentMoney = currentMoney;
        this.name = name;

    }

    public int DisplayPlayerScreen(Balbiino balbiino,int days){
        String[] options = {"Osta jäätist",
                            "Muuda hinda",
                            "Mine müüma"};
        String message ="Päev: "+days+"\n"+
                name+"\n"+
                "Raha: "+Math.round(currentMoney * 100.0)/100.0+" eurot\n"+
                "Omatud jäätised"+"\n"+
                "Vanilje jäätis: "+VanillaNr+"\n"+
                        "MüümisHind: "+Vanilla_price+" ;SoetamisHind: "+balbiino.getVanilla_Sell_price()+"\n"+
                "Šokolaadi jäätis: "+ChocoNr+"\n"+
                        "MüümisHind: "+Choco_price+" ;SoetamisHind: "+balbiino.getChoco_Sell_price()+"\n"+
                "Mahlapulk: "+PopsicleNr+"\n"+
                        "MüümisHind: "+Popsicle_price+" ;SoetamisHind: "+balbiino.getPopsicle_Sell_price()+"\n";

        int option = JOptionPane.showOptionDialog(null,
                message,
                "Jäätisemäng",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                0);
        return option;
    }

    public double getCurrentMoney() {
        return currentMoney;
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

    public void buyPopsicle(Balbiino balbiino,int kogus){
        if (balbiino.getPopsicle_Sell_price()*kogus > currentMoney) {
            kogus = (int) Math.floor(currentMoney / balbiino.getPopsicle_Sell_price());
        }
        currentMoney -= balbiino.getPopsicle_Sell_price()*kogus;
        PopsicleNr += kogus;
    }

    public void ChangePrices(){
        String[] options = {"Vanilje","Šokolaad","Mahlapulk"};
        int option = JOptionPane.showOptionDialog(null,
                "Millise jäätise hinda soovid muuta?",
                "Jäätisemäng",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                0);


        if (option == 0){
            double arv = Double.parseDouble(JOptionPane.showInputDialog(null,
                    "Sisesta Vanilje jäätiste uushind",
                    "Jäätisemäng",
                    JOptionPane.QUESTION_MESSAGE));
            Vanilla_price = arv;
        } else if (option == 1) {
            double arv = Double.parseDouble(JOptionPane.showInputDialog(null,
                    "Sisesta Šokolaadi jäätiste uushind",
                    "Jäätisemäng",
                    JOptionPane.QUESTION_MESSAGE));
            Choco_price = arv;
        } else {
            double arv = Double.parseDouble(JOptionPane.showInputDialog(null,
                    "Sisesta Mahlapulkade uushind",
                    "Jäätisemäng",
                    JOptionPane.QUESTION_MESSAGE));
            Popsicle_price = arv;
        }
    }

    public void ResetDaily(){
        SoldChoco = 0;
        SoldPopsicle = 0;
        SoldVanilla = 0;
        DailyMoneyMade = 0;
    }

    public void DailyReport(){
        JOptionPane.showMessageDialog(null,"Päeva raport:\n" +
                "Müüsid \n" +
                SoldVanilla+" Vanilje jäätist\n" +
                SoldChoco+" Šokolaadi jäätist\n" +
                SoldPopsicle+" Mahlapulka\n" +
                "Ja teenisid "+Math.round(DailyMoneyMade * 100.0)/100.0 +" eurot.");
    }

    public void EndReport(){
        JOptionPane.showMessageDialog(null,"Müügikorra raport:\n" +
                "Sul jäi alles \n" +
                VanillaNr+" Vanilje jäätist\n" +
                ChocoNr+" Šokolaadi jäätist\n" +
                PopsicleNr+" Mahlapulka\n" +
                "Kasum:  "+Math.round((currentMoney-50.0) * 100.0)/100.0 +" eurot.");
    }
    public void Bought(String ice_cream, int WillingToBuy,double MaxPrice){
        if(ice_cream.equals("Choco")){
            if (Choco_price<=MaxPrice){
                if (ChocoNr >= WillingToBuy) {
                    ChocoNr -= WillingToBuy;
                    currentMoney += WillingToBuy * Choco_price;
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
