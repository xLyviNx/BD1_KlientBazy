module com.synowiecsygut.klientbazy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.synowiecsygut.klientbazy to javafx.fxml;
    exports com.synowiecsygut.klientbazy;
}