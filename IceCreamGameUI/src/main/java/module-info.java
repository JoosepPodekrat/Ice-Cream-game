module developers.icecreamgameui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens developers.icecreamgameui to javafx.fxml;
    exports developers.icecreamgameui;
}