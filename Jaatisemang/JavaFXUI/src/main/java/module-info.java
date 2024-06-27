module developers.javafxui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens developers.javafxui to javafx.fxml;
    exports developers.javafxui;
}