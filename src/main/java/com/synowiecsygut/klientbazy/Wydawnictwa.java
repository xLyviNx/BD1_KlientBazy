package com.synowiecsygut.klientbazy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Wydawnictwa {
    @FXML
    TextField input_id;
    @FXML
    TextField input_naz;
    @FXML
    TextField input_ulica;
    @FXML
    TextField input_nrdomu;
    @FXML
    TextField input_nrlokalu;
    @FXML
    TextField input_kodpoczt;
    @FXML
    TextField input_poczta;

    @FXML
    private VBox wydparent;
    @FXML
    private VBox wydawnictwaMain;
    @FXML
    private CheckBox checkBoxId;
    @FXML
    private CheckBox checkBoxNazwa;
    @FXML
    private CheckBox checkBoxUlica;
    @FXML
    private CheckBox checkBoxNrDomu;
    @FXML
    private CheckBox checkBoxNrLokalu;
    @FXML
    private CheckBox checkBoxKodPocztowy;
    @FXML
    private CheckBox checkBoxPoczta;

    @FXML
    public void initialize() {
        wydawnictwaMain.setVisible(false);
    }

    @FXML
    void dodaj() {
        String nazwa = input_naz.getText();
        String ulica = input_ulica.getText();
        String nrDomu = input_nrdomu.getText();
        String nrLokalu = input_nrlokalu.getText();
        String kodPocztowy = input_kodpoczt.getText();
        String poczta = input_poczta.getText();

        if (nazwa.isEmpty() || ulica.isEmpty() || nrDomu.isEmpty() || nrLokalu.isEmpty() || kodPocztowy.isEmpty() || poczta.isEmpty()) {
            Utilities.showAlert("Błąd", "Wszystkie dane (oprócz ID) muszą być wypełnione!", Alert.AlertType.ERROR);
            return;
        }

        try (DatabaseConnection db = new DatabaseConnection()) {
            CallableStatement callableStatement = db.getConnection().prepareCall("{call DodajWydawnictwo(?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setString(1, nazwa);
            callableStatement.setString(2, ulica);
            callableStatement.setString(3, nrDomu);
            callableStatement.setString(4, nrLokalu);
            callableStatement.setString(5, kodPocztowy);
            callableStatement.setString(6, poczta);
            callableStatement.registerOutParameter(7, Types.NUMERIC);
            callableStatement.execute();

            int successFlag = callableStatement.getInt(7);

            if (successFlag == 1) {
                Utilities.showAlert("Informacja", "Dodano wydawnictwo.", Alert.AlertType.INFORMATION);
                clearInputs();
            } else {
                Utilities.showAlert("Błąd!", "Nie udało się dodać wydawnictwa.", Alert.AlertType.ERROR);
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
            Utilities.showAlert("Błąd", "ID wydawnictwa musi być podane!", Alert.AlertType.ERROR);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call UsunWydawnictwo(?, ?)}");
                callableStatement.setInt(1, id);
                callableStatement.registerOutParameter(2, Types.NUMERIC);
                callableStatement.execute();

                int successFlag = callableStatement.getInt(2);

                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Usunięto wydawnictwo.", Alert.AlertType.INFORMATION);
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się usunąć wydawnictwa.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                System.err.println(e.getLocalizedMessage());
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID wydawnictwa musi być liczbą całkowitą!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void edytuj() {
        String idStr = input_id.getText();
        String nazwa = input_naz.getText();
        String ulica = input_ulica.getText();
        String nrDomu = input_nrdomu.getText();
        String nrLokalu = input_nrlokalu.getText();
        String kodPocztowy = input_kodpoczt.getText();
        String poczta = input_poczta.getText();

        if (idStr.isEmpty()) {
            Utilities.showAlert("Błąd", "ID wydawnictwa musi być podane!", Alert.AlertType.ERROR);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);

            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call EdytujWydawnictwo(?, ?, ?, ?, ?, ?, ?, ?)}");
                callableStatement.setInt(1, id);
                callableStatement.setString(2, nazwa);
                callableStatement.setString(3, ulica);
                callableStatement.setString(4, nrDomu);
                callableStatement.setString(5, nrLokalu);
                callableStatement.setString(6, kodPocztowy);
                callableStatement.setString(7, poczta);
                callableStatement.registerOutParameter(8, Types.NUMERIC);
                callableStatement.execute();

                int successFlag = callableStatement.getInt(8);

                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Zaktualizowano wydawnictwo.", Alert.AlertType.INFORMATION);
                    clearInputs();
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się zaktualizować wydawnictwa.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                System.err.println(e.getLocalizedMessage());
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID wydawnictwa musi być liczbą całkowitą!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void wys() {
        wydawnictwaMain.setVisible(true);
        wydparent.getChildren().clear();
        wydparent.getChildren().add(createTemplateHBox(new Wydawnictwo(0, "Nazwa", "Ulica", "Nr domu", "Nr lokalu", "Kod pocztowy", "Poczta")));

        try (DatabaseConnection db = new DatabaseConnection()) {
            List<Wydawnictwo> wydawnictwa = fetchWydawnictwaFromDatabase(db);

            for (Wydawnictwo wydawnictwo : wydawnictwa) {
                HBox templateHBox = createTemplateHBox(wydawnictwo);
                wydparent.getChildren().add(templateHBox);
            }

        } catch (SQLException e) {
            Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
            System.err.println(e.getLocalizedMessage());
        }
    }

    private HBox createTemplateHBox(Wydawnictwo wydawnictwo) {
        HBox templateHBox = new HBox(5);

        if (checkBoxId.isSelected()) {
            if (wydawnictwo.getId() > 0) {
                templateHBox.getChildren().add(Utilities.createLabel(String.valueOf(wydawnictwo.getId())));
            } else {
                templateHBox.getChildren().add(Utilities.createLabel("ID"));
            }
        }
        if (checkBoxNazwa.isSelected()) {
            templateHBox.getChildren().add(Utilities.createLabel(wydawnictwo.getNazwa()));
        }
        if (checkBoxUlica.isSelected()) {
            templateHBox.getChildren().add(Utilities.createLabel(wydawnictwo.getUlica()));
        }
        if (checkBoxNrDomu.isSelected()) {
            templateHBox.getChildren().add(Utilities.createLabel(wydawnictwo.getNrDomu()));
        }
        if (checkBoxNrLokalu.isSelected()) {
            templateHBox.getChildren().add(Utilities.createLabel(wydawnictwo.getNrLokalu()));
        }
        if (checkBoxKodPocztowy.isSelected()) {
            templateHBox.getChildren().add(Utilities.createLabel(wydawnictwo.getKodPocztowy()));
        }
        if (checkBoxPoczta.isSelected()) {
            templateHBox.getChildren().add(Utilities.createLabel(wydawnictwo.getPoczta()));
        }

        if (wydawnictwo.getId() < 1) {
            templateHBox.setStyle("-fx-background-color: rgba(0,0,0,0.8)");
        } else {
            templateHBox.setOnMouseClicked(event -> {
                input_id.setText(String.valueOf(wydawnictwo.getId()));
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
    private List<Wydawnictwo> fetchWydawnictwaFromDatabase(DatabaseConnection db) throws SQLException {
        List<Wydawnictwo> wydawnictwa = new ArrayList<>();

        StringBuilder queryBuilder = getStringBuilder();

        try (Statement statement = db.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(queryBuilder.toString())) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_wydawnictwo");
                String nazwa = checkBoxNazwa.isSelected() ? resultSet.getString("nazwa") : "";
                String ulica = checkBoxUlica.isSelected() ? resultSet.getString("ulica") : "";
                String nrDomu = checkBoxNrDomu.isSelected() ? resultSet.getString("nr_domu") : "";
                String nrLokalu = checkBoxNrLokalu.isSelected() ? resultSet.getString("nr_lokalu") : "";
                String kodPocztowy = checkBoxKodPocztowy.isSelected() ? resultSet.getString("kod_pocztowy") : "";
                String poczta = checkBoxPoczta.isSelected() ? resultSet.getString("poczta") : "";

                Wydawnictwo wydawnictwo = new Wydawnictwo(id, nazwa, ulica, nrDomu, nrLokalu, kodPocztowy, poczta);
                wydawnictwa.add(wydawnictwo);
            }
        }

        return wydawnictwa;
    }
    private void clearInputs() {
        input_id.clear();
        input_naz.clear();
        input_ulica.clear();
        input_nrdomu.clear();
        input_nrlokalu.clear();
        input_kodpoczt.clear();
        input_poczta.clear();
    }

    private StringBuilder getStringBuilder() {
        StringBuilder queryBuilder = new StringBuilder("SELECT id_wydawnictwo");

        if (checkBoxNazwa.isSelected()) {
            queryBuilder.append(", nazwa");
        }
        if (checkBoxUlica.isSelected()) {
            queryBuilder.append(", ulica");
        }
        if (checkBoxNrDomu.isSelected()) {
            queryBuilder.append(", nr_domu");
        }
        if (checkBoxNrLokalu.isSelected()) {
            queryBuilder.append(", nr_lokalu");
        }
        if (checkBoxKodPocztowy.isSelected()) {
            queryBuilder.append(", kod_pocztowy");
        }
        if (checkBoxPoczta.isSelected()) {
            queryBuilder.append(", poczta");
        }

        queryBuilder.append(" FROM wydawnictwa ORDER BY id_wydawnictwo");
        return queryBuilder;
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

    public void refresh(ActionEvent actionEvent)
    {
        wys();
    }

    public void close()
    {
        wydawnictwaMain.setVisible(false);
    }

    public void wypozyczZapisz(MouseEvent mouseEvent)
    {
        Ksiazki.zapisaneWydId=input_id.getText();
    }
}

