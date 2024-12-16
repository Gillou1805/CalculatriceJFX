module be.iramps.javafxEx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;
    requires java.desktop;
    


    opens be.iramps.javafxEx.be.iramps.javafxEx to javafx.fxml;
    exports be.iramps.javafxEx.be.iramps.javafxEx;
}