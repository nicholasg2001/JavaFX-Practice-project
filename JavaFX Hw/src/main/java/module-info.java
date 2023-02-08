module com.mycompany.csc311.hw {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.sql;
    opens com.mycompany.csc311.hw to javafx.fxml, com.google.gson;
    exports com.mycompany.csc311.hw;
}
