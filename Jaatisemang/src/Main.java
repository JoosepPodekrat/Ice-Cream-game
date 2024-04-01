import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class Main {
    private static String startMessage = "Õpetus: Sa oled rannas suvel jäätisemüüja. \n" +
            "Mängu eesmärk on võimalikult palju raha teenida.\n" +
            "Mäng koosneb neljast etapist:\n" +
            "   Ilmateate ja lisainfo lugemine\n" +
            "   Jäätise Balbiino poest ostmine\n" +
            "   Müügihindade reguleerimine\n" +
            "   Jäätise rannas edasimüümine\n" +
            "\n\nAlustamiseks vajuta ok";
    private static String title = "Jäätisemäng";
    //private static String startMessage = "Et mängu käivitada, vajuta X-i.";
    //Nii saaks muuta mängu keelt, aga ilmselt on mingi lihtsam viis
    public static void main(String[] args) {
        Balbiino balbiino = new Balbiino(0.5,0.5,0.3);
        Events events = new Events();

        int option = JOptionPane.showConfirmDialog(null, startMessage, title,
                JOptionPane.OK_CANCEL_OPTION);

        if (option == 0){
            int days = Integer.parseInt(JOptionPane.showInputDialog(null, "Mitu mängu päeva soovid mängida?", title,
                    JOptionPane.QUESTION_MESSAGE));
            String nimi=JOptionPane.showInputDialog(null, "Sisesta oma nimi ", title,
                    JOptionPane.QUESTION_MESSAGE);
            Player player = new Player(50.00, nimi);
            //Default prices
            player.setChoco_price(0.6);
            player.setPopsicle_price(0.4);
            player.setVanilla_price(0.6);
            int daycounter = 1;
            while (days+1>daycounter){ // Main LOOP
                player.ResetDaily();
                events.DisplayAndSetEvents(balbiino);
                Customer[] customers = events.GenerateCustomers().toArray(new Customer[0]);
                int choice = -1;
                while (choice != 2) { //Kuni müüma pole mindud ei tule uut päeva;
                    choice = player.DisplayPlayerScreen(balbiino,daycounter);
                    if (choice == 0) { //Osta jäätist
                        balbiino.BuyIceCream(player);
                    } else if (choice == 1) { // Muuda müügihinda
                        player.ChangePrices();
                    } else { //Mine müüma
                        daycounter++;
                    }
                }

                for (Customer customer:customers) {//Jäätise müümine
                    customer.buyIceCream(player);
                    //System.out.println(customer);
                }
                player.DailyReport();

            }
            player.EndReport();
        }
    }
}