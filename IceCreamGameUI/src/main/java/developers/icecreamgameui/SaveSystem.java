package developers.icecreamgameui;
import java.io.*;

public class SaveSystem {

    Player player;

    public SaveSystem(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public SaveSystem(File savename) throws Exception {
        this.player = restoreSave(savename);
    }

    //Defaults to a fresh game, when file not found
    public Player restoreSave(File saveName) throws Exception{
        try (DataInputStream dis = new DataInputStream(new FileInputStream(saveName))){
            String playerName = dis.readUTF();
            Double playerMoney = dis.readDouble();
            int saveDay = dis.readInt();
            int gameDay = dis.readInt();
            int vanillaNr = dis.readInt();
            int popsicleNr = dis.readInt();
            int chocoNr = dis.readInt();
            int soldVanilla = dis.readInt();
            int soldPopsicle = dis.readInt();
            int soldChoco = dis.readInt();
            Player player = new Player(playerMoney,playerName,vanillaNr,popsicleNr,chocoNr,saveDay,gameDay,soldVanilla,soldPopsicle,soldChoco);
            return player;
        } catch (IOException ioException){
            Player savePlayer = new Player(0, "Empty save");
            return savePlayer;
        }
    }
    public void createSave(File saveName) throws Exception {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(saveName))){
            dos.writeUTF(player.getName());
            dos.writeDouble(player.getCurrentMoney());
            dos.writeInt(player.getCurrentDay());
            dos.writeInt(player.getGameDays());
            dos.writeInt(player.getVanillaNr());
            dos.writeInt(player.getPopsicleNr());
            dos.writeInt(player.getChocoNr());
            dos.writeInt(player.getSoldVanilla());
            dos.writeInt(player.getSoldPopsicle());
            dos.writeInt(player.getSoldChoco());
        }catch (FileNotFoundException fileNotFoundException){
            System.out.println("File not found");
        }

    }

    public static void main(String[] args) throws Exception {
    }

}
