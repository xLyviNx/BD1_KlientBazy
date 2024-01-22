package com.synowiecsygut.klientbazy;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Wypozyczenia
{
    @FXML
    TextField input_id;
    @FXML
    TextField input_idczyt;
    @FXML
    TextField input_idegze;
    @FXML
    TextField input_idpracwyp;
    @FXML
    DatePicker wypoData;
    @FXML
    TextField input_idpraczwr;
    @FXML
    DatePicker input_datazwr;
    @FXML
    private TableView<Wypozyczenie> wypozyczeniaTable;
    @FXML
    private TableColumn<Wypozyczenie, Integer> columnIdWyp;
    @FXML
    private TableColumn<Wypozyczenie, Integer> columnIdCzytelnik;
    @FXML
    private TableColumn<Wypozyczenie, String> columnCzytelnik;
    @FXML
    private TableColumn<Wypozyczenie, Integer> columnIdEgzemplarz;
    @FXML
    private TableColumn<Wypozyczenie, Integer> columnIdKsiazki;
    @FXML
    private TableColumn<Wypozyczenie, String> columnTytulKsiazki;
    @FXML
    private TableColumn<Wypozyczenie, Integer> columnIdPracownikWyp;
    @FXML
    private TableColumn<Wypozyczenie, String> columnPracownikWyp;
    @FXML
    private TableColumn<Wypozyczenie, LocalDate> columnDataWyp;
    @FXML
    private TableColumn<Wypozyczenie, Integer> columnIdPracownikZwr;
    @FXML
    private TableColumn<Wypozyczenie, String> columnPracownikZwr;
    @FXML
    private TableColumn<Wypozyczenie, LocalDate> columnDataZwr;
    @FXML
    private CheckBox checkBoxIdWyp;
    @FXML
    private CheckBox checkBoxIdCzytelnik;
    @FXML
    private CheckBox checkBoxCzytelnik;
    @FXML
    private CheckBox checkBoxIdEgzemplarz;
    @FXML
    private CheckBox checkBoxIdKsiazki;
    @FXML
    private CheckBox checkBoxTytulKsiazki;
    @FXML
    private CheckBox checkBoxIdPracownikWyp;
    @FXML
    private CheckBox checkBoxPracownikWyp;
    @FXML
    private CheckBox checkBoxDataWyp;
    @FXML
    private CheckBox checkBoxIdPracownikZwr;
    @FXML
    private CheckBox checkBoxPracownikZwr;
    @FXML
    private CheckBox checkBoxDataZwr;
    @FXML
    private CheckBox checkBoxBezZwrotu;
    @FXML
    VBox wypozyczeniaMain;
    public static String pracWypo = "";
    public static String pracZwrot = "";
    public static String czytZapis = "";
    public static String egzZapis = "";
    @FXML
    Label srednieWyp;
    @FXML
    void initialize()
    {
        input_idpracwyp.setText(pracWypo);
        input_idpraczwr.setText(pracZwrot);
        input_idczyt.setText(czytZapis);
        input_idegze.setText(egzZapis);
        close();
        columnIdWyp.setCellValueFactory(new PropertyValueFactory<>("idWypozyczenie"));
        columnIdCzytelnik.setCellValueFactory(new PropertyValueFactory<>("idCzytelnik"));
        columnCzytelnik.setCellValueFactory(new PropertyValueFactory<>("czytelnik"));
        columnIdEgzemplarz.setCellValueFactory(new PropertyValueFactory<>("idEgzemplarz"));
        columnIdKsiazki.setCellValueFactory(new PropertyValueFactory<>("idKsiazka"));
        columnTytulKsiazki.setCellValueFactory(new PropertyValueFactory<>("tytulKsiazki"));
        columnIdPracownikWyp.setCellValueFactory(new PropertyValueFactory<>("idPracownikWypozyczenia"));
        columnPracownikWyp.setCellValueFactory(new PropertyValueFactory<>("pracownikWypozyczenia"));
        columnDataWyp.setCellValueFactory(new PropertyValueFactory<>("dataWypozyczenia"));
        columnIdPracownikZwr.setCellValueFactory(new PropertyValueFactory<>("idPracownikZwrotu"));
        columnPracownikZwr.setCellValueFactory(new PropertyValueFactory<>("pracownikZwrotu"));
        columnDataZwr.setCellValueFactory(new PropertyValueFactory<>("dataZwrotu"));
        wypozyczeniaTable.setOnMouseClicked(this::handleTableRowClick);
        try (DatabaseConnection dbc = new DatabaseConnection()) {
            try (CallableStatement callableStatement = dbc.getConnection().prepareCall("{ ? = call srednia_wypozyczen_w_tym_roku }")) {
                callableStatement.registerOutParameter(1, Types.VARCHAR);
                callableStatement.execute();
                String wiadomosc = callableStatement.getString(1);
                srednieWyp.setText(wiadomosc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        wypozyczeniaTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                handleTableClick();
            }
        });
    }

    private void handleTableClick()
    {
        Wypozyczenie selectedWypozyczenie = wypozyczeniaTable.getSelectionModel().getSelectedItem();
        if (selectedWypozyczenie != null) {
            input_id.setText(String.valueOf(selectedWypozyczenie.getIdWypozyczenia()));
            close();
        }
    }

    private void handleTableRowClick(MouseEvent mouseEvent)
    {
    }

    @FXML
    void dodaj() {
        String idCzytelnikStr = input_idczyt.getText();
        String idEgzemplarzaStr = input_idegze.getText();
        String idPracownikWypStr = input_idpracwyp.getText();
        LocalDate dataWypozyczenia = wypoData.getValue();

        if (idCzytelnikStr.isEmpty() || idEgzemplarzaStr.isEmpty() || idPracownikWypStr.isEmpty() || dataWypozyczenia == null) {
            Utilities.showAlert("Błąd", "Wszystkie pola muszą być wypełnione!", Alert.AlertType.ERROR);
            return;
        }

        try {
            int idCzytelnik = Integer.parseInt(idCzytelnikStr);
            int idEgzemplarza = Integer.parseInt(idEgzemplarzaStr);
            int idPracownikWyp = Integer.parseInt(idPracownikWypStr);

            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call DodajWypozyczenie(?, ?, ?, ?, ?)}");
                callableStatement.setInt(1, idCzytelnik);
                callableStatement.setInt(2, idEgzemplarza);
                callableStatement.setInt(3, idPracownikWyp);
                callableStatement.setDate(4, Date.valueOf(dataWypozyczenia));
                callableStatement.registerOutParameter(5, Types.NUMERIC);
                callableStatement.execute();

                int successFlag = callableStatement.getInt(5);

                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Dodano wypożyczenie.", Alert.AlertType.INFORMATION);
                    clearFields();
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się dodać wypożyczenia.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID czytelnika, ID egzemplarza i ID pracownika muszą być liczbami całkowitymi!", Alert.AlertType.ERROR);
        }
    }

    private void clearFields() {
        input_id.clear();
        input_idczyt.clear();
        input_idegze.clear();
        input_idpracwyp.clear();
        wypoData.setValue(null);
        input_idpraczwr.clear();
        input_datazwr.setValue(null);
    }



    @FXML
    void usun() {
        String idStr = input_id.getText();

        if (idStr.isEmpty()) {
            Utilities.showAlert("Błąd", "ID wypożyczenia musi być podane!", Alert.AlertType.ERROR);
            return;
        }

        try {
            int idWypozyczenia = Integer.parseInt(idStr);

            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call UsunWypozyczenie(?, ?)}");
                callableStatement.setInt(1, idWypozyczenia);
                callableStatement.registerOutParameter(2, Types.NUMERIC);
                callableStatement.execute();

                int successFlag = callableStatement.getInt(2);

                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Usunięto wypożyczenie.", Alert.AlertType.INFORMATION);
                    clearFields();
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się usunąć wypożyczenia.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                System.err.println(e.getLocalizedMessage());
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID wypożyczenia musi być liczbą całkowitą!", Alert.AlertType.ERROR);
        }
    }
    @FXML
    void edytuj() {
        String idWypozyczeniaStr = input_id.getText();
        String idCzytelnikaStr = input_idczyt.getText();
        String idEgzemplarzaStr = input_idegze.getText();
        String idPracownikaWypStr = input_idpracwyp.getText();
        LocalDate dataWypozyczenia = wypoData.getValue();
        String idPracownikaZwrStr = input_idpraczwr.getText();
        LocalDate dataZwrotu = input_datazwr.getValue();

        if (idWypozyczeniaStr.isEmpty()) {
            Utilities.showAlert("Błąd", "ID wypożyczenia musi być podane!", Alert.AlertType.ERROR);
            return;
        }

        try {
            int idWypozyczenia = Integer.parseInt(idWypozyczeniaStr);
            int idCzytelnika = idCzytelnikaStr.isEmpty() ? -1 : Integer.parseInt(idCzytelnikaStr);
            int idEgzemplarza = idEgzemplarzaStr.isEmpty() ? -1 : Integer.parseInt(idEgzemplarzaStr);
            int idPracownikaWyp = idPracownikaWypStr.isEmpty() ? -1 : Integer.parseInt(idPracownikaWypStr);
            int idPracownikaZwr = idPracownikaZwrStr.isEmpty() ? -1 : Integer.parseInt(idPracownikaZwrStr);

            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call EdytujWypozyczenie(?, ?, ?, ?, ?, ?, ?, ?)}");
                callableStatement.setInt(1, idWypozyczenia);
                callableStatement.setInt(2, idCzytelnika);
                callableStatement.setInt(3, idEgzemplarza);
                callableStatement.setInt(4, idPracownikaWyp);
                callableStatement.setObject(5, dataWypozyczenia != null ? Date.valueOf(dataWypozyczenia) : null);
                callableStatement.setInt(6, idPracownikaZwr);
                callableStatement.setObject(7, dataZwrotu != null ? Date.valueOf(dataZwrotu) : null);
                callableStatement.registerOutParameter(8, Types.NUMERIC);
                callableStatement.execute();

                int successFlag = callableStatement.getInt(8);

                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Zaktualizowano wypożyczenie.", Alert.AlertType.INFORMATION);
                    clearFields();
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się zaktualizować wypożyczenia.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                System.err.println(e.getLocalizedMessage());
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID wypożyczenia, ID czytelnika, ID egzemplarza i ID pracownika muszą być liczbami całkowitymi!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void wys()
    {
        wypozyczeniaMain.setVisible(true);
        refreshTable();
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

    public void wyczyscZapis(MouseEvent mouseEvent)
    {
        pracWypo = "";
        pracZwrot = "";
        czytZapis = "";
        egzZapis = "";
        clearFields();
    }

    public void dataDzis(MouseEvent mouseEvent)
    {
        wypoData.setValue(LocalDate.now());
    }

    @FXML
    private void refreshTable() {
        columnIdWyp.setVisible(checkBoxIdWyp.isSelected());
        columnIdCzytelnik.setVisible(checkBoxIdCzytelnik.isSelected());
        columnCzytelnik.setVisible(checkBoxCzytelnik.isSelected());
        columnIdEgzemplarz.setVisible(checkBoxIdEgzemplarz.isSelected());
        columnIdKsiazki.setVisible(checkBoxIdKsiazki.isSelected());
        columnTytulKsiazki.setVisible(checkBoxTytulKsiazki.isSelected());
        columnIdPracownikWyp.setVisible(checkBoxIdPracownikWyp.isSelected());
        columnPracownikWyp.setVisible(checkBoxPracownikWyp.isSelected());
        columnDataWyp.setVisible(checkBoxDataWyp.isSelected());
        columnIdPracownikZwr.setVisible(checkBoxIdPracownikZwr.isSelected());
        columnPracownikZwr.setVisible(checkBoxPracownikZwr.isSelected());
        columnDataZwr.setVisible(checkBoxDataZwr.isSelected());

        try (DatabaseConnection db = new DatabaseConnection()) {
            Statement statement = db.getConnection().createStatement();
            StringBuilder queryBuilder = new StringBuilder("SELECT ");
            boolean addComma = false;

            queryBuilder.append("ID_WYPOZYCZENIE");

            addComma = true;
            if (checkBoxIdCzytelnik.isSelected()) {
                if (addComma) queryBuilder.append(", ");
                queryBuilder.append("ID_CZYTELNIK");
                addComma = true;
            }
            if (checkBoxCzytelnik.isSelected()) {
                if (addComma) queryBuilder.append(", ");
                queryBuilder.append("CZYTELNIK");
                addComma = true;
            }
            if (checkBoxIdEgzemplarz.isSelected()) {
                if (addComma) queryBuilder.append(", ");
                queryBuilder.append("ID_EGZEMPLARZ");
                addComma = true;
            }
            if (checkBoxIdKsiazki.isSelected()) {
                if (addComma) queryBuilder.append(", ");
                queryBuilder.append("ID_KSIAZKA");
                addComma = true;
            }
            if (checkBoxTytulKsiazki.isSelected()) {
                if (addComma) queryBuilder.append(", ");
                queryBuilder.append("TYTUL_KSIAZKI");
                addComma = true;
            }
            if (checkBoxIdPracownikWyp.isSelected()) {
                if (addComma) queryBuilder.append(", ");
                queryBuilder.append("ID_PRACOWNIK_WYP");
                addComma = true;
            }
            if (checkBoxPracownikWyp.isSelected()) {
                if (addComma) queryBuilder.append(", ");
                queryBuilder.append("PRACOWNIK_WYP");
                addComma = true;
            }
            if (checkBoxDataWyp.isSelected()) {
                if (addComma) queryBuilder.append(", ");
                queryBuilder.append("DATA_WYPOZYCZENIA");
                addComma = true;
            }
            if (checkBoxIdPracownikZwr.isSelected()) {
                if (addComma) queryBuilder.append(", ");
                queryBuilder.append("ID_PRACOWNIK_ZWR");
                addComma = true;
            }
            if (checkBoxPracownikZwr.isSelected()) {
                if (addComma) queryBuilder.append(", ");
                queryBuilder.append("PRACOWNIK_ZWR");
                addComma = true;
            }
            if (checkBoxDataZwr.isSelected()) {
                if (addComma) queryBuilder.append(", ");
                queryBuilder.append("DATA_ZWROTU");
            }

            queryBuilder.append(" FROM WIDOK_WYPOZYCZENIA");
            if (checkBoxBezZwrotu.isSelected())
                queryBuilder.append("_BEZZWROTU");
            ResultSet resultSet = statement.executeQuery(queryBuilder.toString());

            List<Wypozyczenie> wypozyczeniaList = new ArrayList<>();
            while (resultSet.next()) {
                int idWypozyczenia = resultSet.getInt("ID_WYPOZYCZENIE");
                int idCzytelnika = checkBoxIdCzytelnik.isSelected() ? resultSet.getInt("ID_CZYTELNIK") : 0;
                String czytelnik = checkBoxCzytelnik.isSelected() ? resultSet.getString("CZYTELNIK") : "";
                int idEgzemplarza = checkBoxIdEgzemplarz.isSelected() ? resultSet.getInt("ID_EGZEMPLARZ") : 0;
                int idKsiazki = checkBoxIdKsiazki.isSelected() ? resultSet.getInt("ID_KSIAZKA") : 0;
                String tytulKsiazki = checkBoxTytulKsiazki.isSelected() ? resultSet.getString("TYTUL_KSIAZKI") : "";
                int idPracownikaWyp = checkBoxIdPracownikWyp.isSelected() ? resultSet.getInt("ID_PRACOWNIK_WYP") : 0;
                String pracownikWyp = checkBoxPracownikWyp.isSelected() ? resultSet.getString("PRACOWNIK_WYP") : "";
                LocalDate dataWypozyczenia = checkBoxDataWyp.isSelected() && resultSet.getDate("DATA_WYPOZYCZENIA") != null ?
                        resultSet.getDate("DATA_WYPOZYCZENIA").toLocalDate() : null;
                int idPracownikaZwr = checkBoxIdPracownikZwr.isSelected() ? resultSet.getInt("ID_PRACOWNIK_ZWR") : 0;
                String pracownikZwr = checkBoxPracownikZwr.isSelected() ? resultSet.getString("PRACOWNIK_ZWR") : "";
                LocalDate dataZwrotu = checkBoxDataZwr.isSelected() && resultSet.getDate("DATA_ZWROTU") != null ?
                        resultSet.getDate("DATA_ZWROTU").toLocalDate() : null;


                wypozyczeniaList.add(new Wypozyczenie(idWypozyczenia, idCzytelnika, czytelnik, idEgzemplarza, idKsiazki, tytulKsiazki,
                        idPracownikaWyp, pracownikWyp, dataWypozyczenia, idPracownikaZwr, pracownikZwr, dataZwrotu));
            }

            resultSet.close();

            columnIdWyp.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdWypozyczenia()).asObject());
            columnIdCzytelnik.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdCzytelnika()).asObject());
            columnCzytelnik.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCzytelnik()));
            columnIdEgzemplarz.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdEgzemplarza()).asObject());
            columnIdKsiazki.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdKsiazki()).asObject());
            columnTytulKsiazki.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTytulKsiazki()));
            columnIdPracownikWyp.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdPracownikaWyp()).asObject());
            columnPracownikWyp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPracownikWyp()));
            columnDataWyp.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDataWypozyczenia()));
            columnIdPracownikZwr.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdPracownikaZwr()).asObject());
            columnPracownikZwr.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPracownikZwr()));
            columnDataZwr.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDataZwrotu()));

            wypozyczeniaTable.getItems().setAll(wypozyczeniaList);

        } catch (SQLException e) {
            Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }


    public void close()
    {
        wypozyczeniaMain.setVisible(false);
    }

    public void dataDzisZwr(MouseEvent mouseEvent) {
        input_datazwr.setValue(LocalDate.now());
    }
}
