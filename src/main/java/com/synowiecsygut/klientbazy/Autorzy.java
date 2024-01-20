package com.synowiecsygut.klientbazy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Autorzy {
    @FXML
    TextField input_id;
    @FXML
    TextField input_naz;
    @FXML
    TextField input_imie;

    @FXML
    void dodaj() {
        String naz = input_naz.getText();
        String imie = input_imie.getText();

        if (naz.isEmpty() || imie.isEmpty()) {
            Utilities.showAlert("Błąd", "Wszystkie dane (oprócz ID) muszą być wypełnione!", Alert.AlertType.ERROR);
            return;
        }

        try (DatabaseConnection db = new DatabaseConnection()) {
            CallableStatement callableStatement = db.getConnection().prepareCall("{call DodajAutora(?, ?, ?)}");
            callableStatement.setString(1, naz);
            callableStatement.setString(2, imie);
            callableStatement.registerOutParameter(3, Types.NUMERIC);
            callableStatement.execute();

            int successFlag = callableStatement.getInt(3);

            if (successFlag == 1) {
                Utilities.showAlert("Informacja", "Dodano autora.", Alert.AlertType.INFORMATION);
                input_naz.clear();
                input_imie.clear();
            } else {
                Utilities.showAlert("Błąd!", "Nie udało się dodać autora.", Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
            System.err.println(e.getLocalizedMessage());
        }
    }

    @FXML
    void usun() {
        String idStr = input_id.getText();

        if (idStr.isEmpty()) {
            Utilities.showAlert("Błąd", "ID autora musi być podane!", Alert.AlertType.ERROR);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call UsunAutora(?, ?)}");
                callableStatement.setInt(1, id);
                callableStatement.registerOutParameter(2, Types.NUMERIC);
                callableStatement.execute();

                int successFlag = callableStatement.getInt(2);

                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Usunięto autora.", Alert.AlertType.INFORMATION);
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się usunąć autora.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                System.err.println(e.getLocalizedMessage());
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID autora musi być liczbą całkowitą!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void edytuj() {
        String idStr = input_id.getText();
        String naz = input_naz.getText();
        String imie = input_imie.getText();

        if (idStr.isEmpty()) {
            Utilities.showAlert("Błąd", "ID autora musi być podane!", Alert.AlertType.ERROR);
            return;
        }
        try {
            int id = Integer.parseInt(idStr);

            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call EdytujAutora(?, ?, ?, ?)}");
                callableStatement.setInt(1, id);
                callableStatement.setString(2, naz);
                callableStatement.setString(3, imie);
                callableStatement.registerOutParameter(4, Types.NUMERIC);
                callableStatement.execute();

                int successFlag = callableStatement.getInt(4);

                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Zaktualizowano autora.", Alert.AlertType.INFORMATION);
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się zaktualizować autora.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                System.err.println(e.getLocalizedMessage());
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID autora musi być liczbą całkowitą!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void wys() {

    }

    @FXML
    void wroc() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("StronaGlowna.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1280, 800);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainWindow.mainStage.setScene(scene);
    }
}
