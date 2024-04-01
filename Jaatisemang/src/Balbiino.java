import javax.swing.JOptionPane;

public class Balbiino {
    private double Vanilla_Sell_price;
    private double Choco_Sell_price;
    private double Popsicle_Sell_price;

    Balbiino(double Vanilla_Sell_price,double Choco_Sell_price,double Popsicle_Sell_price){
        this.Popsicle_Sell_price = Popsicle_Sell_price;
        this.Choco_Sell_price = Choco_Sell_price;
        this.Vanilla_Sell_price = Vanilla_Sell_price;
    }

    public void BuyIceCream(Player player){
        String[] options = {"Vanilje","Šokolaad","Mahlapulk"};
        int option = JOptionPane.showOptionDialog(null,
                "Millist jäätist soovid osta?",
                "Jäätisemäng",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                0);

        if (option == 0){
            int arv = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Palju Vanilje jäätist soovid osta.\n" +
                            "Hind: "+Vanilla_Sell_price+"\n"+
                            "(Kui soovitud arvu otmiseks raha ei ole, ostetakse maksimaalne arv, mis olemasoleva rahaga osta saab.)",
                    "Jäätisemäng",
                    JOptionPane.QUESTION_MESSAGE));
            player.buyVanilla(this,arv);
        } else if (option == 1) {
            int arv = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Palju Šokolaadi jäätist soovid osta.\n" +
                            "Hind: "+Choco_Sell_price+"\n"+
                            "(Kui soovitud arvu otmiseks raha ei ole, ostetakse maksimaalne arv, mis olemasoleva rahaga osta saab.)",
                    "Jäätisemäng",
                    JOptionPane.QUESTION_MESSAGE));
            player.buyChoco(this,arv);
        } else {
            int arv = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Palju Mahlapulki soovid osta.\n" +
                            "Hind: "+Popsicle_Sell_price+"\n"+
                            "(Kui soovitud arvu otmiseks raha ei ole, ostetakse maksimaalne arv, mis olemasoleva rahaga osta saab.)",
                    "Jäätisemäng",
                    JOptionPane.QUESTION_MESSAGE));
            player.buyPopsicle(this,arv);
        }
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

    @Override
    public String toString(){
        return "Vanilla price: "+Vanilla_Sell_price+";Chocolate price: "+Choco_Sell_price+";Popsicle price: "+Popsicle_Sell_price;
    }
}
