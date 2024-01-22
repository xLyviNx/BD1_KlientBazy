package com.synowiecsygut.klientbazy;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Egzemplarze {
    @FXML
    TextField input_id;
    @FXML
    TextField input_idksia;
    @FXML
    TextField input_rokwyd;
    @FXML
    TextField input_stan;
    @FXML
    CheckBox checkBoxId;
    @FXML
    CheckBox checkBoxIdKsiazki;
    @FXML
    CheckBox checkBoxTytul;
    @FXML
    CheckBox checkBoxRokWydania;
    @FXML
    CheckBox checkBoxDostepnosc;
    @FXML
    TableView<Egzemplarz> egzemplarzTable;
    @FXML
    VBox egzemplarzeMain;
    @FXML
    TableColumn<Egzemplarz, Integer> columnId;
    @FXML
    TableColumn<Egzemplarz, Integer> columnIdKsiazki;
    @FXML
    TableColumn<Egzemplarz, Integer> columnRokWydania;
    @FXML
    TableColumn<Egzemplarz, Boolean> columnDostepnosc;

    public static String zapisKsiazka = "";
    @FXML
    TableColumn<Egzemplarz, String> columnTytul;
    @FXML
    private CheckBox checkBoxDostepne;

    @FXML
    TableView statsTable;
    @FXML
    VBox statsMain;

    @FXML
    TableColumn<StatystykiEgzemplarzy, Integer> columnStatId;
    @FXML
    TableColumn<StatystykiEgzemplarzy, String> columnStatTytul;
    @FXML
    TableColumn<StatystykiEgzemplarzy, Integer> columnStatAmount;
    @FXML
    TableColumn<StatystykiEgzemplarzy, Integer> columnStatDostepne;
    @FXML
    TableColumn<StatystykiEgzemplarzy, Integer> columnStatTotal;
    @FXML
    Label statSummary;

    @FXML
    void initialize() {
        input_idksia.setText(zapisKsiazka);
        egzemplarzTable.setOnMouseClicked(this::handleTableRowClick);
    }

    @FXML
    void dodaj() {
        String idKsiazkiStr = input_idksia.getText();
        String rokWydaniaStr = input_rokwyd.getText();
        String stanStr = input_stan.getText();

        if (idKsiazkiStr.isEmpty() || rokWydaniaStr.isEmpty()) {
            Utilities.showAlert("Błąd", "ID książki i rok wydania muszą być podane!", Alert.AlertType.ERROR);
            return;
        }

        try {
            int stan = stanStr.isEmpty() ? 1 : Integer.parseInt(stanStr);
            int idKsiazki = Integer.parseInt(idKsiazkiStr);
            int rokWydania = Integer.parseInt(rokWydaniaStr);

            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call DodajEgzemplarz(?, ?, ?, ?)}");
                callableStatement.setInt(1, idKsiazki);
                callableStatement.setInt(2, rokWydania);
                callableStatement.setInt(3, stan);
                callableStatement.registerOutParameter(4, Types.NUMERIC);
                callableStatement.execute();

                int successFlag = callableStatement.getInt(4);

                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Dodano egzemplarz.", Alert.AlertType.INFORMATION);
                    clearFields();
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się dodać egzemplarza.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                System.err.println(e.getLocalizedMessage());
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID książki i rok wydania muszą być liczbami całkowitymi!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void usun() {
        String idStr = input_id.getText();

        if (idStr.isEmpty()) {
            Utilities.showAlert("Błąd", "ID egzemplarza musi być podane!", Alert.AlertType.ERROR);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);

            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call UsunEgzemplarz(?, ?)}");
                callableStatement.setInt(1, id);
                callableStatement.registerOutParameter(2, Types.NUMERIC);
                callableStatement.execute();

                int successFlag = callableStatement.getInt(2);

                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Usunięto egzemplarz.", Alert.AlertType.INFORMATION);
                    clearFields();
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się usunąć egzemplarza.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                System.err.println(e.getLocalizedMessage());
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID egzemplarza musi być liczbą całkowitą!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void edytuj() {
        String idStr = input_id.getText();
        String idKsiazkiStr = input_idksia.getText();
        String rokWydaniaStr = input_rokwyd.getText();
        String stanStr = input_stan.getText();

        if (idStr.isEmpty()) {
            Utilities.showAlert("Błąd", "ID egzemplarza musi być podane!", Alert.AlertType.ERROR);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            int stan = stanStr.isEmpty() ? -1 : Integer.parseInt(stanStr);
            int idKsiazki = idKsiazkiStr.isEmpty() ? -1 : Integer.parseInt(idKsiazkiStr);
            int rokWydania = rokWydaniaStr.isEmpty() ? -1 : rokWydaniaStr.isEmpty() ? 0 : Integer.parseInt(rokWydaniaStr);

            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call EdytujEgzemplarz(?, ?, ?, ?, ?)}");
                callableStatement.setInt(1, id);
                callableStatement.setInt(2, idKsiazki);
                callableStatement.setInt(3, rokWydania);
                callableStatement.setInt(4, stan);
                callableStatement.registerOutParameter(5, Types.NUMERIC);
                callableStatement.execute();

                int successFlag = callableStatement.getInt(5);

                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Zaktualizowano egzemplarz.", Alert.AlertType.INFORMATION);
                    clearFields();
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się zaktualizować egzemplarza.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                System.err.println(e.getLocalizedMessage());
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID egzemplarza, ID książki i rok wydania muszą być liczbami całkowitymi!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void wys() {
        egzemplarzeMain.setVisible(true);
        refreshTable();
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

    public void wypozyczZapisz(MouseEvent mouseEvent) {
        Wypozyczenia.egzZapis = input_id.getText();
    }

    public void czyscZapis(MouseEvent mouseEvent) {
        zapisKsiazka = "";
        input_idksia.setText("");
    }

    void clearFields() {
        input_id.setText("");
        input_idksia.setText("");
        input_rokwyd.setText("");
        input_stan.setText("");
    }

    @FXML
    private void refreshTable() {
        columnId.setVisible(checkBoxId.isSelected());
        columnIdKsiazki.setVisible(checkBoxIdKsiazki.isSelected());
        columnTytul.setVisible(checkBoxTytul.isSelected());
        columnRokWydania.setVisible(checkBoxRokWydania.isSelected());
        columnDostepnosc.setVisible(checkBoxDostepnosc.isSelected());
        try (DatabaseConnection db = new DatabaseConnection()) {
            Statement statement = db.getConnection().createStatement();
            StringBuilder queryBuilder = new StringBuilder("SELECT ");
            boolean addComma = false;

            queryBuilder.append("id_egzemplarz");
            addComma = true;
            if (checkBoxIdKsiazki.isSelected()) {
                if (addComma) queryBuilder.append(", ");
                queryBuilder.append("id_ksiazka");
                addComma = true;
            }
            if (checkBoxTytul.isSelected()) {
                if (addComma) queryBuilder.append(", ");
                queryBuilder.append("Tytul");
                addComma = true;
            }
            if (checkBoxRokWydania.isSelected()) {
                if (addComma) queryBuilder.append(", ");
                queryBuilder.append("Rok_Wydania");
                addComma = true;
            }
            if (checkBoxDostepnosc.isSelected()) {
                if (addComma) queryBuilder.append(", ");
                queryBuilder.append("stan");
            }

            queryBuilder.append(" FROM WIDOK_EGZEMPLARZE_KSIAZKI");

            if (checkBoxDostepne.isSelected()) {
                queryBuilder.append(" WHERE stan = 1");
            }

            ResultSet resultSet = statement.executeQuery(queryBuilder.toString());

            List<Egzemplarz> egzemplarzeList = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_egzemplarz");
                int idKsiazki = checkBoxIdKsiazki.isSelected() ? resultSet.getInt("ID_Ksiazka") : 0;
                String tytul = checkBoxTytul.isSelected() ? resultSet.getString("Tytul") : "";
                int rokWydania = checkBoxRokWydania.isSelected() ? resultSet.getInt("Rok_Wydania") : 0;
                boolean dostepnosc = checkBoxDostepnosc.isSelected() && resultSet.getBoolean("stan");


                egzemplarzeList.add(new Egzemplarz(id, idKsiazki, tytul, rokWydania, dostepnosc));
            }

            resultSet.close();
            columnId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
            columnIdKsiazki.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdKsiazki()).asObject());
            columnTytul.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTytul()));
            columnRokWydania.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRokWydania()).asObject());
            columnDostepnosc.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isDostepnosc()));

            egzemplarzTable.getItems().setAll(egzemplarzeList);

        } catch (SQLException e) {
            Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void handleTableRowClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Egzemplarz selectedEgzemplarz = egzemplarzTable.getSelectionModel().getSelectedItem();
            if (selectedEgzemplarz != null) {
                input_id.setText(String.valueOf(selectedEgzemplarz.getId()));
                close();
            }
        }
    }

    @FXML
    private void close() {
        egzemplarzeMain.setVisible(false);
        statsMain.setVisible(false);
    }
    @FXML
    public void wyswietlStaty() {
        statsMain.setVisible(true);
        try (DatabaseConnection db = new DatabaseConnection()) {
            Statement statement = db.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM WIDOK_STATYSTYKI_EGZEMPLARZY");

            List<StatystykiEgzemplarzy> statystkilist = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_ksiazka");
                String tytul = resultSet.getString("Tytul");
                int iloscwyp = resultSet.getInt("ilosc_wypozyczonych");
                int iloscwolnych = resultSet.getInt("ILOSC_WOLNYCH");
                int ilosc = iloscwyp+iloscwolnych;
                statystkilist.add(new StatystykiEgzemplarzy(id, tytul, iloscwyp, iloscwolnych, ilosc));
            }

            resultSet.close();
            columnStatId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdKsiazka()).asObject());
            columnStatTytul.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTytul()));
            columnStatAmount.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIloscWypozyczonych()).asObject());
            columnStatDostepne.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIloscWolnych()).asObject());
            columnStatTotal.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIloscWolnych()).asObject());

            statsTable.getItems().setAll(statystkilist);
            resultSet = statement.executeQuery("SELECT * FROM WIDOK_STATYSTYKI_SUMMARY");

            while (resultSet.next()) {
                int iloscwyp = resultSet.getInt("SUM_ILOSC_WYPOZYCZONYCH");
                int iloscwolnych = resultSet.getInt("SUM_ILOSC_WOLNYCH");
                float procentwolnych = resultSet.getFloat("PROCENT_WOLNYCH");

                statSummary.setText("Łącznie jest " + iloscwyp + iloscwolnych + " książek.\n" + iloscwyp + " jest wypożyczonych, " + iloscwolnych + " wolnych co stanowi " + procentwolnych + "% egzemplarzy w bibliotece.");
            }
        } catch (SQLException e) {
            Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
