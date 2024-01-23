package com.synowiecsygut.klientbazy;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorzyKsiazek
{
    @FXML
    TextField input_waznosc;
    @FXML
    TextField input_idks;
    @FXML
    TextField input_idaut;
    @FXML
    private VBox ksAutorzyMain;
    @FXML
    private TableColumn<AutorKsiazki, Integer> columnIdKsiazki;
    @FXML
    private TableColumn<AutorKsiazki, String> columnTytul;
    @FXML
    private TableColumn<AutorKsiazki, Integer> columnIdAutora;
    @FXML
    private TableColumn<AutorKsiazki, String> columnImieNazwiskoAutora;
    @FXML
    private TableColumn<AutorKsiazki, Integer> columnWaznosc;


    @FXML
    TableView<AutorKsiazki> auttable;

    @FXML
    void initialize()
    {
        ksAutorzyMain.setVisible(false);
        initializeTable();
    }
    private void initializeTable() {
        columnIdKsiazki.setCellValueFactory(new PropertyValueFactory<>("idKsiazki"));
        columnIdAutora.setCellValueFactory(new PropertyValueFactory<>("idAutora"));
        columnTytul.setCellValueFactory(new PropertyValueFactory<>("tytul"));
        columnImieNazwiskoAutora.setCellValueFactory(new PropertyValueFactory<>("imieNazwisko"));
        columnWaznosc.setCellValueFactory(new PropertyValueFactory<>("waznosc"));

        auttable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                handleTableClick();
            }
        });
    }

    private void handleTableClick()
    {
        AutorKsiazki autor = auttable.getSelectionModel().getSelectedItem();
        if (autor != null) {
            input_idaut.setText(String.valueOf(autor.getIdAutora()));
            input_idks.setText(String.valueOf(autor.getIdAutora()));
            input_waznosc.setText(String.valueOf(autor.getWaznosc()));
            close();
        }
    }
    @FXML
    void dodaj() {
        String idautstr = input_idaut.getText();
        String idksstr = input_idks.getText();
        String waznoscstr = input_waznosc.getText();

        if (idautstr.isEmpty() || idksstr.isEmpty() || waznoscstr.isEmpty()) {
            Utilities.showAlert("Błąd", "Wszystkie dane muszą być wypełnione!", Alert.AlertType.ERROR);
            return;
        }

        try {
            int idAutora = Integer.parseInt(idautstr);
            int idKsiazki = Integer.parseInt(idksstr);
            int waznosc = Integer.parseInt(waznoscstr);
            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call DodajAutoraKsiazki(?, ?, ?, ?)}");
                callableStatement.setInt(1, idKsiazki);
                callableStatement.setInt(2, idAutora);
                callableStatement.setInt(3, waznosc);
                callableStatement.registerOutParameter(8, Types.NUMERIC);
                callableStatement.execute();

                int successFlag = callableStatement.getInt(8);
                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Dodano autora książki.", Alert.AlertType.INFORMATION);
                    clearInputs();
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się dodać autora książki.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "Kara musi być liczbą!", Alert.AlertType.ERROR);
        }
    }

    private void clearInputs()
    {
        input_idks.clear();
        input_waznosc.clear();
        input_idaut.clear();
    }

    @FXML
    public void usun() {
        String idautstr = input_idaut.getText();
        String idksstr = input_idks.getText();

        if (idautstr.isEmpty() || idksstr.isEmpty()) {
            Utilities.showAlert("Błąd", "Wszystkie dane (oprócz ważności) muszą być wypełnione!", Alert.AlertType.ERROR);
            return;
        }

        try {
            int idAutora = Integer.parseInt(idautstr);
            int idKsiazki = Integer.parseInt(idksstr);

            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call UsunAutoraKsiazki(?, ?)}");
                callableStatement.setInt(1, idKsiazki);
                callableStatement.setInt(2, idAutora);
                callableStatement.execute();

                Utilities.showAlert("Informacja", "Usunięto autora książki.", Alert.AlertType.INFORMATION);
                clearInputs();
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID autora i książki muszą być liczbami!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void wys(MouseEvent mouseEvent)
    {
        ksAutorzyMain.setVisible(true);
        try (DatabaseConnection db = new DatabaseConnection()) {
            List<AutorKsiazki> autorzyks = fetchAutorzyKsiazekFromDatabase(db);
            auttable.getItems().setAll(autorzyks);
        } catch (SQLException e) {
            Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private List<AutorKsiazki> fetchAutorzyKsiazekFromDatabase(DatabaseConnection db) throws SQLException {
        List<AutorKsiazki> aks = new ArrayList<>();

        try (Statement statement = db.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM WIDOK_AUTORZY_KSIAZEK")){

            while (resultSet.next()) {
                int idKs = resultSet.getInt("ID_KSIAZKA");
                String tytul = resultSet.getString("TYTUL");
                String imienazwisko = resultSet.getString("IMIE_I_NAZWISKO");
                int idautora = resultSet.getInt("ID_AUTOR");
                int waznosc = resultSet.getInt("waznosc");
                AutorKsiazki autor = new AutorKsiazki(idKs, tytul, imienazwisko, idautora, waznosc);
                aks.add(autor);
            }
        }
        return aks;
    }

    public void wyczyscZapis(MouseEvent mouseEvent) {
    }

    public void wroc(MouseEvent mouseEvent) {
        MainWindow.openScene();
    }

    public void close()
    {
        ksAutorzyMain.setVisible(false);

    }
}
