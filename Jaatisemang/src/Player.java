import java.util.List;

public class Player {
    private double currentMoney;
    private String name;


    public Player(double currentMoney, String name) {
        this.currentMoney = currentMoney;
        this.name = name;

    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    public String getName() {
        return name;
    }
}
