package com.synowiecsygut.klientbazy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

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
    private VBox autorzyMain;
    @FXML
    private VBox autorzyParent;
    @FXML
    private CheckBox checkBoxId;
    @FXML
    private CheckBox checkBoxImie;
    @FXML
    private CheckBox checkBoxNazwisko;
    @FXML
    public void initialize() {
        autorzyMain.setVisible(false);
    }

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
        autorzyMain.setVisible(true);
        autorzyParent.getChildren().clear();
        autorzyParent.getChildren().add(createTemplateHBox(new Autor(0, "Imię", "Nazwisko")));

        try (DatabaseConnection db = new DatabaseConnection()) {
            List<Autor> autorzy = fetchAutorzyFromDatabase(db);

            for (Autor autor : autorzy) {
                HBox templateHBox = createTemplateHBox(autor);
                autorzyParent.getChildren().add(templateHBox);
            }

        } catch (SQLException e) {
            Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
            System.err.println(e.getLocalizedMessage());
        }
    }

    @FXML
    void close() {
        autorzyMain.setVisible(false);
    }

    @FXML
    void refresh() {
        wys();
    }

    private HBox createTemplateHBox(Autor autor) {
        HBox templateHBox = new HBox(5);

        if (checkBoxId.isSelected()) {
            if (autor.getId() > 0) {
                templateHBox.getChildren().add(createLabel(String.valueOf(autor.getId())));
            } else {
                templateHBox.getChildren().add(createLabel("ID"));
            }
        }
        if (checkBoxImie.isSelected()) {
            templateHBox.getChildren().add(createLabel(autor.getImie()));
        }
        if (checkBoxNazwisko.isSelected()) {
            templateHBox.getChildren().add(createLabel(autor.getNazwisko()));
        }

        if (autor.getId() < 1) {
            templateHBox.setStyle("-fx-background-color: rgba(0,0,0,0.8)");
        } else {
            templateHBox.setOnMouseClicked(event -> {
                input_id.setText(String.valueOf(autor.getId()));
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


    private List<Autor> fetchAutorzyFromDatabase(DatabaseConnection db) throws SQLException {
        List<Autor> autorzy = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT id_autor");

        if (checkBoxImie.isSelected()) {
            queryBuilder.append(", imie");
        }
        if (checkBoxNazwisko.isSelected()) {
            queryBuilder.append(", nazwisko");
        }

        queryBuilder.append(" FROM autorzy ORDER BY id_autor");

        try (Statement statement = db.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(queryBuilder.toString())) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_autor");
                String imie = checkBoxImie.isSelected() ? resultSet.getString("imie") : "";
                String nazwisko = checkBoxNazwisko.isSelected() ? resultSet.getString("nazwisko") : "";

                Autor autor = new Autor(id, imie, nazwisko);
                autorzy.add(autor);
            }
        }

        return autorzy;
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
