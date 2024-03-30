import java.util.List;

public class Player {
    private double currentMoney;
    private String name;
    private List<String> ownedIceCreams;

    public Player(double currentMoney, String name, List<String> ownedIceCreams) {
        this.currentMoney = currentMoney;
        this.name = name;
        this.ownedIceCreams = ownedIceCreams;
    }
}
