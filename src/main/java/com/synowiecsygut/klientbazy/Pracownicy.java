package com.synowiecsygut.klientbazy;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Pracownicy
{
    @FXML
    TextField input_id;
    @FXML
    TextField input_naz;
    @FXML
    TextField input_imie;
    @FXML
    TextField input_wyna;
    @FXML
    TextField input_stano;
    @FXML
    void dodaj() {
        String naz = input_naz.getText();
        String imie = input_imie.getText();
        String wynagrodzenieStr = input_wyna.getText();
        String stanowisko = input_stano.getText();

        if (naz.isEmpty() || imie.isEmpty() || wynagrodzenieStr.isEmpty() || stanowisko.isEmpty()) {
            Utilities.showAlert("Błąd", "Wszystkie dane (oprócz ID) muszą być wypełnione!", Alert.AlertType.ERROR);
            return;
        }

        try {
            float wynagrodzenie = Float.parseFloat(wynagrodzenieStr);
            try (DatabaseConnection db = new DatabaseConnection())
            {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call DodajPracownika(?, ?, ?, ?)}");
                callableStatement.setString(1, naz);
                callableStatement.setString(2, imie);
                callableStatement.setFloat(3, wynagrodzenie);
                callableStatement.setString(4, stanowisko);

                callableStatement.execute();
                int rowCount = callableStatement.getUpdateCount();
                if (rowCount > 0) {
                    Utilities.showAlert("Informacja", "Dodano pracownika.", Alert.AlertType.INFORMATION);
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się dodać pracownika.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                System.err.println(e.getLocalizedMessage());
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "Wynagrodzenie musi być liczbą!", Alert.AlertType.ERROR);
        }
    }
    @FXML
    void usun()
    {
    }

    @FXML
    void edytuj()
    {
    }

    @FXML
    void wys()
    {
    }
    @FXML
    void wroc()
    {
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
