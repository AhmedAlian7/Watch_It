module com.example.loginpagedemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    opens com.example.loginpagedemo to javafx.fxml;
    exports com.example.loginpagedemo;
    exports watchIt;
}
