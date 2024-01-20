package com.synowiecsygut.klientbazy;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
    private VBox pracparent;
    @FXML
    private VBox pracownicyMain;
    @FXML
    private CheckBox checkBoxId;
    @FXML
    private CheckBox checkBoxImie;
    @FXML
    private CheckBox checkBoxNazwisko;
    @FXML
    private CheckBox checkBoxWynagrodzenie;
    @FXML
    private CheckBox checkBoxStanowisko;
    @FXML
    public void initialize()
    {
        pracownicyMain.setVisible(false);
    }
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
    void wys() {
        pracownicyMain.setVisible(true);
        pracparent.getChildren().clear();
        pracparent.getChildren().add(createTemplateHBox(new Pracownik(0, "Imię", "Nazwisko", 0, "Stanowisko")));

        try (DatabaseConnection db = new DatabaseConnection()) {
            List<Pracownik> pracownicy = fetchPracownicyFromDatabase(db);

            for (Pracownik pracownik : pracownicy) {
                HBox templateHBox = createTemplateHBox(pracownik);
                pracparent.getChildren().add(templateHBox);
            }

        } catch (SQLException e) {
            Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
            System.err.println(e.getLocalizedMessage());
        }
    }

    private HBox createTemplateHBox(Pracownik pracownik) {
        HBox templateHBox = new HBox(5);

        if (checkBoxId.isSelected()) {
            if (pracownik.getId()>0) {
                templateHBox.getChildren().add(createLabel(String.valueOf(pracownik.getId())));
            }else{
                templateHBox.getChildren().add(createLabel("ID"));
            }
        }
        if (checkBoxImie.isSelected()) {
            templateHBox.getChildren().add(createLabel(pracownik.getImie()));
        }
        if (checkBoxNazwisko.isSelected()) {
            templateHBox.getChildren().add(createLabel(pracownik.getNazwisko()));
        }
        if (checkBoxWynagrodzenie.isSelected()) {
            if (pracownik.getId()>0) {
                DecimalFormat decimalFormat = new DecimalFormat("#,###,##0.00 zł");
                String formattedWynagrodzenie = decimalFormat.format(pracownik.getWynagrodzenie());

                templateHBox.getChildren().add(createLabel(formattedWynagrodzenie));
            }else{
                templateHBox.getChildren().add(createLabel("Wynagrodzenie"));
            }
        }
        if (checkBoxStanowisko.isSelected()) {
            templateHBox.getChildren().add(createLabel(pracownik.getStanowisko()));
        }

        if (pracownik.getId() < 1) {
            templateHBox.setStyle("-fx-background-color: rgba(0,0,0,0.8)");
        } else {
            templateHBox.setOnMouseClicked(event -> {
                input_id.setText(String.valueOf(pracownik.getId()));
                close();
            });
            templateHBox.setOnMouseEntered(event -> {
                templateHBox.setStyle("-fx-background-color: rgba(255,255,255,0.3)");
            });
            templateHBox.setOnMouseExited(event -> {
                templateHBox.setStyle("");
            });
        }

        return templateHBox;
    }
    private List<Pracownik> fetchPracownicyFromDatabase(DatabaseConnection db) throws SQLException {
        List<Pracownik> pracownicy = new ArrayList<>();

        StringBuilder queryBuilder = getStringBuilder();

        try (Statement statement = db.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(queryBuilder.toString())) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_pracownik");
                String imie = checkBoxImie.isSelected() ? resultSet.getString("imie") : "";
                String nazwisko = checkBoxNazwisko.isSelected() ? resultSet.getString("nazwisko") : "";
                float wynagrodzenie = checkBoxWynagrodzenie.isSelected() ? resultSet.getFloat("wynagrodzenie") : 0;
                String stanowisko = checkBoxStanowisko.isSelected() ? resultSet.getString("stanowisko") : "";

                Pracownik pracownik = new Pracownik(id, imie, nazwisko, wynagrodzenie, stanowisko);
                pracownicy.add(pracownik);
            }
        }

        return pracownicy;
    }
    private StringBuilder getStringBuilder() {
        StringBuilder queryBuilder = new StringBuilder("SELECT id_pracownik");

        if (checkBoxImie.isSelected()) {
            queryBuilder.append(", imie");
        }
        if (checkBoxNazwisko.isSelected()) {
            queryBuilder.append(", nazwisko");
        }
        if (checkBoxWynagrodzenie.isSelected()) {
            queryBuilder.append(", wynagrodzenie");
        }
        if (checkBoxStanowisko.isSelected()) {
            queryBuilder.append(", stanowisko");
        }

        queryBuilder.append(" FROM pracownicy ORDER BY id_pracownik");
        return queryBuilder;
    }
    @FXML
    public void close()
    {
        pracownicyMain.setVisible(false);
    }
    private static Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font(26.0));
        label.setPrefHeight(40.0);
        label.setPrefWidth(1269.0);
        label.setAlignment(javafx.geometry.Pos.CENTER);

        return label;
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
    @FXML
    public void refresh(){
        wys();
    }
}
