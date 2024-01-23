package com.synowiecsygut.klientbazy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Czytelnicy
{
    @FXML
    TextField input_id;
    @FXML
    TextField input_naz;
    @FXML
    TextField input_imie;
    @FXML
    TextField input_ulica;
    @FXML
    TextField input_nrdom;
    @FXML
    TextField input_nrlokalu;
    @FXML
    TextField input_kodpocz;
    @FXML
    TextField input_kara;
    @FXML
    private VBox czytelnicyMain;
    @FXML
    private VBox czytelnicyParent;
    @FXML
    private CheckBox checkBoxId;
    @FXML
    private CheckBox checkBoxImie;
    @FXML
    private CheckBox checkBoxNazwisko;
    @FXML
    private CheckBox checkBoxUlica;
    @FXML
    private CheckBox checkBoxNrDomu;
    @FXML
    private CheckBox checkBoxNrLokalu;
    @FXML
    private CheckBox checkBoxKodPocztowy;
    @FXML
    private CheckBox checkBoxKara;
    @FXML
    private CheckBox checkBoxWypo;
    @FXML
    private TableView<Czytelnik> czytelnicyTable;
    @FXML
    private TableColumn<Czytelnik, Integer> columnId;
    @FXML
    private TableColumn<Czytelnik, String> columnImie;
    @FXML
    private TableColumn<Czytelnik, String> columnNazwisko;
    @FXML
    private TableColumn<Czytelnik, String> columnUlica;
    @FXML
    private TableColumn<Czytelnik, String> columnNrDomu;
    @FXML
    private TableColumn<Czytelnik, String> columnNrLokalu;
    @FXML
    private TableColumn<Czytelnik, String> columnKodPocztowy;
    @FXML
    private TableColumn<Czytelnik, Float> columnKara;
    @FXML
    private TableColumn<Czytelnik, Integer> columnWypo;
    @FXML
    private Label kara;
    @FXML
    public void initialize() {
        czytelnicyMain.setVisible(false);
        checkBoxId.setSelected(true);
        checkBoxImie.setSelected(true);
        checkBoxNazwisko.setSelected(true);
        checkBoxUlica.setSelected(true);
        checkBoxNrDomu.setSelected(true);
        checkBoxNrLokalu.setSelected(true);
        checkBoxKodPocztowy.setSelected(true);
        checkBoxKara.setSelected(true);
        initializeTable();
        try (DatabaseConnection dbc = new DatabaseConnection()) {
            try (CallableStatement callableStatement = dbc.getConnection().prepareCall("{ ? = call oblicz_sume_kar }")) {
                callableStatement.registerOutParameter(1, Types.NUMERIC);
                callableStatement.execute();
                float sumakar = callableStatement.getFloat(1);
                kara.setText("Suma kar: " + sumakar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeTable() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        columnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        columnUlica.setCellValueFactory(new PropertyValueFactory<>("ulica"));
        columnNrDomu.setCellValueFactory(new PropertyValueFactory<>("nrDomu"));
        columnNrLokalu.setCellValueFactory(new PropertyValueFactory<>("nrLokalu"));
        columnKodPocztowy.setCellValueFactory(new PropertyValueFactory<>("kodPocztowy"));
        columnKara.setCellValueFactory(new PropertyValueFactory<>("kara"));
        columnWypo.setCellValueFactory(new PropertyValueFactory<>("iloscKsiazek"));

        czytelnicyTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                handleTableClick();
            }
        });
    }

    private void handleTableClick()
    {
        Czytelnik selectedCzytelnik = czytelnicyTable.getSelectionModel().getSelectedItem();
        if (selectedCzytelnik != null) {
            input_id.setText(String.valueOf(selectedCzytelnik.getId()));
            close();
        }
    }

    @FXML
    void dodaj() {
        String naz = input_naz.getText();
        String imie = input_imie.getText();
        String ulica = input_ulica.getText();
        String nrdom = input_nrdom.getText();
        String nrlokalu = input_nrlokalu.getText();
        String kodpocz = input_kodpocz.getText();
        String karaStr = input_kara.getText();

        if (naz.isEmpty() || imie.isEmpty() || karaStr.isEmpty()) {
            Utilities.showAlert("Błąd", "Wszystkie dane (oprócz ID, Adresu, Kodu Pocztowego) muszą być wypełnione!", Alert.AlertType.ERROR);
            return;
        }

        try {
            float kara = Float.parseFloat(karaStr);
            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call DodajCzytelnika(?, ?, ?, ?, ?, ?, ?, ?)}");
                callableStatement.setString(1, naz);
                callableStatement.setString(2, imie);
                callableStatement.setString(3, ulica);
                callableStatement.setString(4, nrdom);
                callableStatement.setString(5, nrlokalu);
                callableStatement.setString(6, kodpocz);
                callableStatement.setFloat(7, kara);
                callableStatement.registerOutParameter(8, Types.NUMERIC);
                callableStatement.execute();

                int successFlag = callableStatement.getInt(8);
                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Dodano czytelnika.", Alert.AlertType.INFORMATION);
                    clearInputs();
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się dodać czytelnika.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "Kara musi być liczbą!", Alert.AlertType.ERROR);
        }
    }

    private void clearInputs() {
        input_naz.clear();
        input_imie.clear();
        input_ulica.clear();
        input_nrdom.clear();
        input_nrlokalu.clear();
        input_kodpocz.clear();
        input_kara.clear();
    }

    @FXML
    void usun() {
        String idStr = input_id.getText();

        if (idStr.isEmpty()) {
            Utilities.showAlert("Błąd", "ID czytelnika musi być podane!", Alert.AlertType.ERROR);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call UsunCzytelnika(?, ?)}");
                callableStatement.setInt(1, id);
                callableStatement.registerOutParameter(2, Types.NUMERIC);
                callableStatement.execute();

                int successFlag = callableStatement.getInt(2);

                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Usunięto czytelnika.", Alert.AlertType.INFORMATION);
                    clearInputs();
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się usunąć czytelnika.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID czytelnika musi być liczbą całkowitą!", Alert.AlertType.ERROR);
        }
    }
    @FXML
    void edytuj() {
        String idStr = input_id.getText();
        String naz = input_naz.getText();
        String imie = input_imie.getText();
        String ulica = input_ulica.getText();
        String nrDomu = input_nrdom.getText();
        String nrLokalu = input_nrlokalu.getText();
        String kodPocztowy = input_kodpocz.getText();
        String karaStr = input_kara.getText();

        if (idStr.isEmpty()) {
            Utilities.showAlert("Błąd", "ID czytelnika musi być podane!", Alert.AlertType.ERROR);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            float kara = (karaStr.isEmpty()) ? -1 : Float.parseFloat(karaStr);

            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call EdytujCzytelnika(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
                callableStatement.setInt(1, id);
                callableStatement.setString(2, naz);
                callableStatement.setString(3, imie);
                callableStatement.setString(4, ulica);
                callableStatement.setString(5, nrDomu);
                callableStatement.setString(6, nrLokalu);
                callableStatement.setString(7, kodPocztowy);
                callableStatement.setFloat(8, kara);
                callableStatement.registerOutParameter(9, Types.NUMERIC);
                callableStatement.execute();

                int successFlag = callableStatement.getInt(9);

                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Zaktualizowano czytelnika.", Alert.AlertType.INFORMATION);
                    clearInputs();
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się zaktualizować czytelnika.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID czytelnika musi być liczbą całkowitą!", Alert.AlertType.ERROR);
        }
    }
    @FXML
    void wys() {
        columnId.setVisible(checkBoxId.isSelected());
        columnImie.setVisible(checkBoxImie.isSelected());
        columnNazwisko.setVisible(checkBoxNazwisko.isSelected());
        columnUlica.setVisible(checkBoxUlica.isSelected());
        columnNrDomu.setVisible(checkBoxNrDomu.isSelected());
        columnNrLokalu.setVisible(checkBoxNrLokalu.isSelected());
        columnKodPocztowy.setVisible(checkBoxKodPocztowy.isSelected());
        columnKara.setVisible(checkBoxKara.isSelected());
        columnWypo.setVisible(checkBoxWypo.isSelected());

        czytelnicyMain.setVisible(true);
        try (DatabaseConnection db = new DatabaseConnection()) {
            List<Czytelnik> czytelnicy = fetchCzytelnicyFromDatabase(db);

            czytelnicyTable.getItems().setAll(czytelnicy);
        } catch (SQLException e) {
            Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private List<Czytelnik> fetchCzytelnicyFromDatabase(DatabaseConnection db) throws SQLException {
        List<Czytelnik> czytelnicy = new ArrayList<>();

        StringBuilder queryBuilder = getStringBuilderCzytelnicy();

        try (Statement statement = db.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(queryBuilder.toString())) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_czytelnik");
                String imie = checkBoxImie.isSelected() ? resultSet.getString("imie") : "";
                String nazwisko = checkBoxNazwisko.isSelected() ? resultSet.getString("nazwisko") : "";
                String ulica = checkBoxUlica.isSelected() ? resultSet.getString("ulica") : "";
                String nrDomu = checkBoxNrDomu.isSelected() ? resultSet.getString("nr_domu") : "";
                String nrLokalu = checkBoxNrLokalu.isSelected() ? resultSet.getString("nr_lokalu") : "";
                String kodPocztowy = checkBoxKodPocztowy.isSelected() ? resultSet.getString("kod_pocztowy") : "";
                float kara = checkBoxKara.isSelected() ? resultSet.getFloat("kara") : 0;
                int wypo = checkBoxWypo.isSelected()? resultSet.getInt("WYPOZYCZEN") : 0;
                Czytelnik czytelnik = new Czytelnik(id, imie, nazwisko, ulica, nrDomu, nrLokalu, kodPocztowy, kara, wypo);
                czytelnicy.add(czytelnik);
            }
        }

        return czytelnicy;
    }

    private StringBuilder getStringBuilderCzytelnicy() {
        StringBuilder queryBuilder = new StringBuilder("SELECT id_czytelnik");

        if (checkBoxImie.isSelected()) {
            queryBuilder.append(", imie");
        }
        if (checkBoxNazwisko.isSelected()) {
            queryBuilder.append(", nazwisko");
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
        if (checkBoxKara.isSelected()) {
            queryBuilder.append(", kara");
        }
        if (checkBoxWypo.isSelected()) {
            queryBuilder.append(", WYPOZYCZEN");
        }

        queryBuilder.append(" FROM widok_czytelnicy");
        return queryBuilder;
    }

    @FXML
    public void close() {
        czytelnicyMain.setVisible(false);
    }

    @FXML
    void wroc() {
        MainWindow.openScene();
    }
    @FXML
    public void refresh() {
        wys();
    }

    public void wypozyczZapisz(MouseEvent mouseEvent)
    {
        Wypozyczenia.czytZapis=input_id.getText();
    }
}
