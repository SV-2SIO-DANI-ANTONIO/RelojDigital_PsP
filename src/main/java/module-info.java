module com.svalero.relojdigitalpsp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.svalero.relojdigitalpsp to javafx.fxml;
    exports com.svalero.relojdigitalpsp;
}