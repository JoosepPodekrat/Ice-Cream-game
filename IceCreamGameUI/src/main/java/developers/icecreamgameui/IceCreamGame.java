package developers.icecreamgameui;

import javafx.application.Application;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class IceCreamGame extends Application {
    String backGroundColor = " -fx-background-color: #2e2e2e;";
    String backGroundColorDetails = " -fx-background-color: #4F4F4F;";
    String foreGroundColor = " -fx-background-color: #ffffff;";
    String playerColor = " -fx-background-color: #ffffff;";
    Stage primaryStage = new Stage();
    Events events = new Events();
    Balbiino balbiino = new Balbiino(0.5, 0.5,0.35);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Untitled Ice Cream Game");
        primaryStage.getIcons().add(new Image("IceCreamJoosepPodekrat.png"));

        Label titleLabel = new Label("Ice Cream Game");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        Label authorLabel = new Label("Joosep Podekrat, Priit Teelahk");
        authorLabel.setStyle("-fx-font-size: 12px;");

        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> startNewGame(primaryStage));
        newGameButton.setStyle("-fx-font-size: 12px;" + foreGroundColor);

        Button loadGameButton = new Button("Load Game");
        loadGameButton.setOnAction(e -> showSaves(primaryStage));
        loadGameButton.setStyle("-fx-font-size: 12px;" + foreGroundColor);

        Button exitGameButton = new Button("Exit Game");
        exitGameButton.setOnAction(e -> exitGame());
        exitGameButton.setStyle("-fx-font-size: 12px;" + foreGroundColor);

        VBox vBox = new VBox(20);
        vBox.setStyle("-fx-font-size: 12px;");
        vBox.setAlignment(Pos.CENTER);  // Keskendame elemendid
        vBox.getChildren().addAll(titleLabel, newGameButton, loadGameButton, exitGameButton, authorLabel);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);
        Image backgroundImage = new Image(getClass().getResourceAsStream("/RandSuvineKevinKaatsu.png"));
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        BackgroundImage bgImage = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        borderPane.setBackground(new Background(bgImage));

        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startNewGame(Stage primaryStage) {

        Label label1 = new Label("Who are you:");
        Label label2 = new Label("How many days will you grind:");
        label2.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + foreGroundColor);
        label1.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + foreGroundColor);

        TextField stringTextField = new TextField();
        TextField numericTextField = new TextField();
        stringTextField.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + foreGroundColor);
        numericTextField.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + foreGroundColor);

        Button startGameButton = new Button("I'm ready");
        startGameButton.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + foreGroundColor);
        startGameButton.setOnAction(e -> {
            try {
                Player player = new Player(50, stringTextField.getText());
                player.setGameDays(Integer.parseInt(numericTextField.getText()));
                gamePlayLoop(player, primaryStage);
            } catch (NumberFormatException numberFormatException) {
                label2.setText("Please enter atleast 1 number: ");
            }
                });
        Button backButton = new Button("Main Menu");
        backButton.setOnAction(e -> start(primaryStage));

        numericTextField.textProperty().addListener((observable, oldValue, newValue)-> {
            if (!newValue.matches("\\d*")) {
                numericTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        VBox vBox1 = new VBox(20,label1,label2, backButton);
        VBox vBox2 = new VBox(20,stringTextField,numericTextField, startGameButton );
        HBox hBox1 = new HBox(20, vBox1, vBox2);
        vBox1.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + backGroundColor);
        vBox2.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + backGroundColor);
        hBox1.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + backGroundColor);
        vBox1.setAlignment(Pos.CENTER);
        vBox2.setAlignment(Pos.CENTER);
        hBox1.setAlignment(Pos.CENTER);
        Scene newGameScene = new Scene(hBox1,primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
        primaryStage.setScene(newGameScene);
        primaryStage.show();
    }
    private void showSaves(Stage primaryStage) {
        List<Button> buttonList = new ArrayList<>();
        Label label = new Label("You are currently loading a game.");
        label.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + foreGroundColor);
        //generates 9 buttons, sets their actions to load saves and puts them in a list
        for (int i = 1; i < 10; i++) {
            File save = new File("saveSlot" + i);
            Button newButton = new Button("Save " + i);
            newButton.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + foreGroundColor);
            try {
                Player player = new SaveSystem(save).getPlayer();
                newButton.setText(player.getName() + " " + player.getCurrentDay());
            } catch (Exception exception){
                newButton.setText("Empty slot " + i);
            }
            newButton.setOnAction(e ->{
                try {
                    Player player = new SaveSystem(save).getPlayer();
                    if (player.getName().equals("Empty save")){
                        startNewGame(primaryStage);
                        return;
                    }
                    gamePlayLoop(player, primaryStage);
                } catch (Exception exception){
                    startNewGame(primaryStage);
                    System.out.println("failed to load save");
                    return;
                }
            });
            buttonList.add(newButton);
        }
        VBox row1 = new VBox(30);
        VBox row2 = new VBox(30);
        VBox row3 = new VBox(30);
        row1.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + backGroundColor);
        row2.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + backGroundColor);
        row3.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + backGroundColor);
        row1.setAlignment(Pos.CENTER);
        row2.setAlignment(Pos.CENTER);
        row3.setAlignment(Pos.CENTER);
        HBox rows = new HBox(30);
        rows.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + backGroundColor);
        rows.setAlignment(Pos.CENTER);
        System.out.println(buttonList.size());
        //adds buttons to 3x3 grid
        for (int i = 0; i < buttonList.size(); i++) {
            if (i % 3 == 0){
                row1.getChildren().add(buttonList.get(i));
            } else if (i % 3 == 1) {
                row2.getChildren().add(buttonList.get(i));
            } else {
                row3.getChildren().add(buttonList.get(i));
            }
        }
        Button buttonBack = new Button("Main Menu");
        buttonBack.setOnAction(e -> {
            start(primaryStage);
            return;
        });
        rows.getChildren().addAll(label,row1,row2,row3, buttonBack);

        //Keeps the window the same size while changing scenes.
        double currentWidth = primaryStage.getScene() != null ? primaryStage.getScene().getWidth() : 800;
        double currentHeight = primaryStage.getScene() != null ? primaryStage.getScene().getHeight() : 600;
        Scene scene = new Scene(rows, currentWidth, currentHeight);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void showSaveSlots(Player player, Stage primaryStage) {
        SaveSystem saveSystem = new SaveSystem(player);
        List<Button> buttonList = new ArrayList<>();
        //generates 9 buttons, sets their actions to load saves and puts them in a list
        for (int i = 1; i < 10; i++) {
            File save = new File("saveSlot" + i);
            Button newButton = new Button("SaveSlot " + i);
            newButton.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + foreGroundColor);
            final int j = i;
            try {
                Player savePlayer = new SaveSystem(save).getPlayer();
                newButton.setText(savePlayer.getName());
            } catch (Exception exception){
                newButton.setText("Empty slot " + i);
            }
            newButton.setOnAction(e ->{
                try {
                    saveSystem.createSave(new File("saveSlot" + j));
                    gamePlayLoop(player, primaryStage);
                } catch (Exception exception){
                    startNewGame(primaryStage);
                    System.out.println("Failed to save");
                    return;
                }
            });
            buttonList.add(newButton);
        }
        Label infoLabel = new Label("You are currently saving the game.");
        infoLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + foreGroundColor);
        VBox row1 = new VBox(30);
        VBox row2 = new VBox(30);
        VBox row3 = new VBox(30);
        row1.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + backGroundColor);
        row2.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + backGroundColor);
        row3.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + backGroundColor);
        row1.setAlignment(Pos.CENTER);
        row2.setAlignment(Pos.CENTER);
        row3.setAlignment(Pos.CENTER);
        HBox rows = new HBox(30);
        rows.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + backGroundColor);
        rows.setAlignment(Pos.CENTER);
        System.out.println(buttonList.size());
        //adds buttons to 3x3 grid
        for (int i = 0; i < buttonList.size(); i++) {
            if (i % 3 == 0){
                row1.getChildren().add(buttonList.get(i));
            } else if (i % 3 == 1) {
                row2.getChildren().add(buttonList.get(i));
            } else {
                row3.getChildren().add(buttonList.get(i));
            }
        }
        Button buttonBack = new Button("Main Menu");
        buttonBack.setOnAction(e -> {
            start(primaryStage);
            return;
        });
        rows.getChildren().addAll(infoLabel,row1,row2,row3, buttonBack);

        //Keeps the window the same size while changing scenes.
        double currentWidth = primaryStage.getScene() != null ? primaryStage.getScene().getWidth() : 800;
        double currentHeight = primaryStage.getScene() != null ? primaryStage.getScene().getHeight() : 600;
        Scene scene = new Scene(rows, currentWidth, currentHeight);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void gamePlayLoop(Player player, Stage primaryStage){
        Balbiino balbiino = new Balbiino(0.5,0.5,0.3);
        List<Customer> customers = events.GenerateCustomers();

        VBox vBox = new VBox(20);
        //VBox sidebarreplacer = new VBox(20);
        VBox vBoxMain = new VBox(20);
        HBox hBox = new HBox(20);
        HBox hbox2 = new HBox(10);
        VBox sidebar = new VBox(30);
        sidebar.setStyle("-fx-padding: 10;" + backGroundColorDetails);

        primaryStage.setTitle("Untitled Ice Cream game");

        Label titleLabel = new Label(player.getName());
        titleLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + foreGroundColor);
        //Label playerMoney = new Label();
        //playerMoney.textProperty().bind(player.getCurrentMoneyProperty().asString());
        //playerMoney.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + foreGroundColor);
        Label daytracker = new Label("Day " +player.getCurrentDay() + " of " + player.getGameDays());
        daytracker.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;" + foreGroundColor);

        Label dailyEvent = new Label(events.DisplayAndSetEvents(new Balbiino(1,1,1)));
        dailyEvent.setStyle("-fx-padding: 20;" +  foreGroundColor);
        Button saveGame = new Button("Save game");
        saveGame.setOnAction(e -> {
            showSaveSlots(player,primaryStage);
        });
        Button exitGame = new Button("Exit game");
        exitGame.setOnAction(e -> exitGame());
        Button loadGame = new Button("Load game");
        loadGame.setOnAction(e -> showSaveSlots(player,primaryStage));
        Button changePrice = new Button("Change Prices");
        changePrice.setOnAction(e -> loadPriceChanger(player, vBoxMain));
        Button buyIceCream = new Button("Buy Ice Cream");
        buyIceCream.setOnAction(e -> showShop(player, vBoxMain));

        sidebar.getChildren().addAll(titleLabel,changePrice,buyIceCream,saveGame,loadGame,exitGame);
        vBoxMain.getChildren().add(dailyEvent);
        vBoxMain.setAlignment(Pos.CENTER);
        //sidebarreplacer.getChildren().add(sidebarToggle);

        hBox.setStyle("-fx-padding: 20;" + backGroundColor);
        vBox.setStyle("-fx-padding: 20;" +  backGroundColor);
        vBoxMain.setStyle("-fx-padding: 20;" +  backGroundColor);
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(sidebar);
        //borderPane.setLeft(sidebarreplacer);
        borderPane.setCenter(vBoxMain);

        double currentWidth = primaryStage.getScene() != null ? primaryStage.getScene().getWidth() : 800;
        double currentHeight = primaryStage.getScene() != null ? primaryStage.getScene().getHeight() : 600;
        Scene scene = new Scene(borderPane, currentWidth, currentHeight);
        loadMainGame(player,vBoxMain);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    private void loadPriceChanger(Player player, VBox vBox) {
        // Clear the current contents of vBox
        vBox.getChildren().clear();
        Label changePriceLabel = new Label("Lower priced ice cream has a higher chance of being bought. ");
        changePriceLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;" + foreGroundColor);
        // Create new content for changing prices
        Label changePriceLabelChoco = new Label("Choco price: ");
        changePriceLabelChoco.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;" + foreGroundColor);
        Label changePriceLabelVanilla = new Label("Vanilla price: ");
        changePriceLabelVanilla.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;" + foreGroundColor);
        Label changePriceLabelPopsicle = new Label("Popsicle price: ");
        changePriceLabelPopsicle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;" +foreGroundColor);

        TextField priceFieldChoco = new TextField();
        priceFieldChoco.setPromptText("Choco price: ");
        TextField priceFieldVanilla = new TextField();
        priceFieldVanilla.setPromptText("Vanilla price: ");
        TextField priceFieldPopsicle = new TextField();
        priceFieldPopsicle.setPromptText("Popsicle price: ");

        priceFieldChoco.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                priceFieldChoco.setText(oldValue);
            }
        });
        priceFieldVanilla.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                priceFieldVanilla.setText(oldValue);
            }
        });
        priceFieldPopsicle.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                priceFieldPopsicle.setText(oldValue);
            }
        });

        Button savePriceButton = new Button("Save Price");
        savePriceButton.setOnAction(e -> {

            try {
                player.setChoco_price(Double.parseDouble(priceFieldChoco.getText()));
            } catch (Exception exception){
                player.setChoco_price(player.getChoco_price());
            }
            try {
                player.setVanilla_price(Double.parseDouble(priceFieldChoco.getText()));
            } catch (Exception exception){
                player.setVanilla_price(player.getVanilla_price());
            }
            try {
                player.setPopsicle_price(Double.parseDouble(priceFieldPopsicle.getText()));
            } catch (Exception exception){
                player.setPopsicle_price(player.getPopsicle_price());
            }
            loadMainGame(player,vBox);

        });
        HBox hBox1 = new HBox(30);
        hBox1.getChildren().addAll(changePriceLabelVanilla,priceFieldVanilla);
        HBox hBox2 = new HBox(30);
        hBox2.getChildren().addAll(changePriceLabelChoco,priceFieldChoco);
        HBox hBox3 = new HBox(30);
        hBox3.getChildren().addAll(changePriceLabelPopsicle,priceFieldPopsicle);
        HBox hBox4 = new HBox(30);
        hBox4.getChildren().addAll(savePriceButton);

        // Add new content to vBox
        vBox.getChildren().addAll(changePriceLabel,hBox1,hBox2,hBox3,hBox4);
    }
    private void loadMainGame(Player player, VBox vBox) {
        if (player.getCurrentDay() >= player.getGameDays()){
            endScreen(player, primaryStage);
        }
        balbiino.changePrices();
        vBox.getChildren().clear();

        Customer[] customers = events.GenerateCustomers().toArray(new Customer[0]);
        Button sellingButton = new Button("Go selling");
        vBox.getChildren().addAll( sellingButton);
        sellingButton.setOnAction(e -> {
            double temp  = player.getDailyMoneyMade();
            int soldChoc = player.getSoldChoco();
            int soldVanil = player.getSoldVanilla();
            int soldPop = player.getSoldPopsicle();
            for (Customer customer:customers) {//Jäätise müümine
                customer.buyIceCream(player);
                //System.out.println(customer);
            }
            double moneyMade = player.getDailyMoneyMade()-temp;
            int soldChocT = player.getSoldChoco()-soldChoc;
            int soldVanilT = player.getSoldVanilla()-soldVanil;
            int soldPopT = player.getSoldPopsicle()-soldPop;
            Label reportLabel = new Label("You made: "+ Math.round(moneyMade) + " icecoins and sold "+ soldChocT + "x Chocolate "+ soldVanilT + "x Vanilla and "+ soldPopT + "x Popsicles.");
            reportLabel.setWrapText(true);
            reportLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;" +foreGroundColor);
            Label infoLabel = new Label();
            infoLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;" +foreGroundColor);
            infoLabel.setText(events.DisplayAndSetEvents(balbiino));
            vBox.getChildren().clear();
            vBox.getChildren().addAll(infoLabel,reportLabel, sellingButton);
            player.setCurrentDay(player.getCurrentDay()+1);
            if (player.getCurrentDay() >= player.getGameDays()){
                endScreen(player, primaryStage);
            }

        });

        // Add new content to vBox

    }
    private void showShop(Player player, VBox vBox){
        vBox.getChildren().clear();

        Label shopLabelChoco = new Label("Chocolate price: "+  balbiino.getChoco_Sell_price());
        shopLabelChoco.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;" + foreGroundColor);
        Label shopLabelVanilla = new Label("Vanilla price: "+  balbiino.getVanilla_Sell_price());
        shopLabelVanilla.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;" + foreGroundColor);
        Label shopLabelPopsicle = new Label("Popsicle price: "+  balbiino.getPopsicle_Sell_price());
        shopLabelPopsicle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;" + foreGroundColor);
        Button savePriceButton = new Button("Finish ordering");
        Label playerMoney = new Label();
        playerMoney.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;" + foreGroundColor);
        playerMoney.setText("You currently have " + Math.round(player.getCurrentMoney()) + " icecoins");
        Label playerchoc = new Label();
        playerchoc.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;" + foreGroundColor);
        playerchoc.setText("You currently have " + player.getChocoNr() + " chocolate");
        Label playerVan = new Label();
        playerVan.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;" + foreGroundColor);
        playerVan.setText("You currently have " + player.getVanillaNr() + " vanilla");
        Label playerPop = new Label();
        playerPop.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;" + foreGroundColor);
        playerPop.setText("You currently have " + player.getPopsicleNr() + " popsicles");
        savePriceButton.setOnAction(e -> {
            loadMainGame(player,vBox);
        });
        TextField priceFieldChoco = new TextField();
        priceFieldChoco.setPromptText("Enter amount: ");
        TextField priceFieldVanilla = new TextField();
        priceFieldVanilla.setPromptText("Enter amount: ");
        TextField priceFieldPopsicle = new TextField();
        priceFieldPopsicle.setPromptText("Enter amount: ");

        Button buyCreamButton = new Button("Check out");
        buyCreamButton.setOnAction(e -> {
            int boughtChocs = 0;
            int boughtVanilla = 0;
            int boughtPopsicle = 0;
            try {
                 boughtChocs = Integer.parseInt(priceFieldChoco.getText());
            } catch (NumberFormatException numberFormatException){
                 boughtChocs = 0;
            }
            try {
                boughtVanilla = Integer.parseInt(priceFieldVanilla.getText());
            } catch (NumberFormatException numberFormatException){
                boughtVanilla = 0;
            }
            try {
                 boughtPopsicle = Integer.parseInt(priceFieldPopsicle.getText());
            } catch (NumberFormatException numberFormatException){
                 boughtPopsicle= 0;
            }
            if (boughtPopsicle > 0 || boughtChocs > 0 ||boughtVanilla > 0){
                try {
                    player.buyChoco(balbiino, boughtChocs);
                } catch (NumberFormatException numberFormatException){
                    buyCreamButton.setText("Bought ice Cream");
                }
                try {
                    player.buyVanilla(balbiino, boughtVanilla);
                }catch (NumberFormatException numberFormatException){
                    buyCreamButton.setText("Bought ice Cream");
                }
                try {
                    player.buyPopsicle(balbiino, boughtPopsicle);
                }catch (NumberFormatException numberFormatException){
                    buyCreamButton.setText("Bought ice Cream");
                }

                buyCreamButton.setText("Bought ice Cream");
            }
            priceFieldChoco.setText("");
            priceFieldVanilla.setText("");
            priceFieldPopsicle.setText("");
            playerMoney.setText("You currently have " + Math.round(player.getCurrentMoney()) + " icecoins");
            playerPop.setText("You currently have " + player.getPopsicleNr() + " popsicles");
            playerVan.setText("You currently have " + player.getVanillaNr() + " vanilla");
            playerchoc.setText("You currently have " + player.getChocoNr() + " chocolate");

        });

        priceFieldChoco.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                priceFieldChoco.setText(oldValue);
            }
        });
        priceFieldVanilla.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                priceFieldVanilla.setText(oldValue);
            }
        });
        priceFieldPopsicle.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                priceFieldPopsicle.setText(oldValue);
            }
        });

        HBox hBox = new HBox(30);
        hBox.getChildren().add(playerMoney);
        HBox hBox1 = new HBox(30);
        hBox1.getChildren().addAll(shopLabelChoco, priceFieldChoco, playerchoc);
        HBox hBox2 = new HBox(30);
        hBox2.getChildren().addAll(shopLabelVanilla, priceFieldVanilla, playerVan);
        HBox hBox3 = new HBox(30);
        hBox3.getChildren().addAll(shopLabelPopsicle, priceFieldPopsicle, playerPop);
        HBox hBox4 = new HBox(30);
        hBox4.getChildren().addAll(buyCreamButton, savePriceButton);

        // Add new content to vBox
        vBox.getChildren().addAll(hBox,hBox1,hBox2,hBox3,hBox4);

    }

    private void endScreen(Player player, Stage primaryStage){

        Label playerInfo = new Label(player.getName() +" in " + player.getGameDays() + " days you earned " + player.getCurrentMoney());
        Label iceCreamInfo = new Label("You sold " + player.getSoldChoco() + " chocolate ice creams, " +player.getSoldVanilla() + " vanilla ice creams and " + player.getSoldPopsicle() + " popsicles.");
        Button exitGame = new Button("Close game");
        exitGame.setOnAction(e -> {
            exitGame();
        });
        VBox vBox = new VBox(50);
        vBox.getChildren().addAll(playerInfo,iceCreamInfo, exitGame);
        vBox.setAlignment(Pos.CENTER);
        HBox hBox = new HBox(100);
        hBox.getChildren().add(vBox);
        hBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(hBox);
        Image backgroundImage = new Image(getClass().getResourceAsStream("/RandSuvineKevinKaatsu.png"));
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        BackgroundImage bgImage = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        borderPane.setBackground(new Background(bgImage));

        double currentWidth = primaryStage.getScene() != null ? primaryStage.getScene().getWidth() : 800;
        double currentHeight = primaryStage.getScene() != null ? primaryStage.getScene().getHeight() : 600;
        Scene scene = new Scene(borderPane, currentWidth, currentHeight);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void exitGame() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
