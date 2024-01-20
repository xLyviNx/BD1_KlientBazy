package com.synowiecsygut.klientbazy;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

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
                CallableStatement callableStatement = db.getConnection().prepareCall("{call DodajPracownika(?, ?, ?, ?, ?)}");
                callableStatement.setString(1, naz);
                callableStatement.setString(2, imie);
                callableStatement.setFloat(3, wynagrodzenie);
                callableStatement.setString(4, stanowisko);
                callableStatement.registerOutParameter(5, Types.NUMERIC);
                callableStatement.execute();
                int successFlag = callableStatement.getInt(5);
                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Dodano pracownika.", Alert.AlertType.INFORMATION);
                    input_naz.clear();
                    input_imie.clear();
                    input_wyna.clear();
                    input_stano.clear();
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
        String idStr = input_id.getText();

        if (idStr.isEmpty()) {
            Utilities.showAlert("Błąd", "ID pracownika musi być podane!", Alert.AlertType.ERROR);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call UsunPracownika(?, ?)}");
                callableStatement.setInt(1, id);
                callableStatement.registerOutParameter(2, Types.NUMERIC);
                callableStatement.execute();

                int successFlag = callableStatement.getInt(2);

                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Usunięto pracownika.", Alert.AlertType.INFORMATION);
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się usunąć pracownika.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                System.err.println(e.getLocalizedMessage());
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID pracownika musi być liczbą całkowitą!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void edytuj() {
        String idStr = input_id.getText();
        String naz = input_naz.getText();
        String imie = input_imie.getText();
        String wynagrodzenieStr = input_wyna.getText();
        String stanowisko = input_stano.getText();

        if (idStr.isEmpty()) {
            Utilities.showAlert("Błąd", "ID pracownika musi być podane!", Alert.AlertType.ERROR);
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            float wynagrodzenie = (wynagrodzenieStr.isEmpty()) ? -1 : Float.parseFloat(wynagrodzenieStr);

            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call EdytujPracownika(?, ?, ?, ?, ?, ?)}");
                callableStatement.setInt(1, id);
                callableStatement.setString(2, naz);
                callableStatement.setString(3, imie);
                callableStatement.setFloat(4, wynagrodzenie);
                callableStatement.setString(5, stanowisko);
                callableStatement.registerOutParameter(6, Types.NUMERIC);
                callableStatement.execute();

                int successFlag = callableStatement.getInt(6);

                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Zaktualizowano pracownika.", Alert.AlertType.INFORMATION);
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się zaktualizować pracownika.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                System.err.println(e.getLocalizedMessage());
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID pracownika musi być liczbą całkowitą!", Alert.AlertType.ERROR);
        }
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
