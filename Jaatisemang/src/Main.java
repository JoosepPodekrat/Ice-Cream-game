import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    private static String startMessage = "To start the game, press X";
    //private static String startMessage = "Et mängu käivitada, vajuta X-i.";
    //Nii saaks muuta mängu keelt, aga ilmselt on mingi lihtsam viis
    public static void main(String[] args) {
        //praegu testimiseks, hiljem tuleks siia main gameplay loopi jaoks vajalikud commandid jms
        Player player = new Player(100, "Toomas");
        System.out.println(startMessage);
        System.out.println(player.getName());
        System.out.println();
        System.out.println(player.getCurrentMoney());
    }
}