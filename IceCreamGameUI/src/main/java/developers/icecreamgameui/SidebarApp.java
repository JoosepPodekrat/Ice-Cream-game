package developers.icecreamgameui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SidebarApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Ice Cream Game");

        // Pealkiri
        Label titleLabel = new Label("Ice Cream Game");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Nupud
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> startNewGame());

        Button loadGameButton = new Button("Load Game");
        loadGameButton.setOnAction(e -> loadGame());

        Button exitGameButton = new Button("Exit Game");
        exitGameButton.setOnAction(e -> exitGame());

        // Paigutus
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(titleLabel, newGameButton, loadGameButton, exitGameButton);

        // Stseen ja lavastus
        Scene scene = new Scene(vBox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startNewGame() {
        // Loo külgriba nupud
        Button buyMoreIceCreamButton = new Button("Buy more Ice Cream");
        buyMoreIceCreamButton.setOnAction(e -> buyMoreIceCream());

        Button goSellingButton = new Button("Go selling");
        goSellingButton.setOnAction(e -> goSelling());

        Button changePricesButton = new Button("Change prices");
        changePricesButton.setOnAction(e -> changePrices());

        // Külgriba paigutus
        VBox sidebar = new VBox(20);
        sidebar.setAlignment(Pos.CENTER_LEFT);
        sidebar.getChildren().addAll(buyMoreIceCreamButton, goSellingButton, changePricesButton);

        // Loo peamine paigutus, mis sisaldab külgriba
        HBox mainLayout = new HBox(20);
        mainLayout.getChildren().add(sidebar);

        // Loo uus stseen ja kuva see
        Scene newScene = new Scene(mainLayout, 400, 300);
        primaryStage.setScene(newScene);
        primaryStage.show();
    }

    private void loadGame() {
        System.out.println("Loading game...");
    }

    private void exitGame() {
        System.out.println("Exiting game...");
        System.exit(0);
    }

    // Hüpoteetilised meetodid
    private void buyMoreIceCream() {
        System.out.println("Buying more ice cream...");
    }

    private void goSelling() {
        System.out.println("Going selling...");
    }

    private void changePrices() {
        System.out.println("Changing prices...");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
