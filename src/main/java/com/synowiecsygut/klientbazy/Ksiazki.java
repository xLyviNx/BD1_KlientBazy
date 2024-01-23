package com.synowiecsygut.klientbazy;

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
import java.util.ArrayList;
import java.util.List;

public class Ksiazki {
    @FXML
    TextField input_id;
    @FXML
    TextField input_tyt;
    @FXML
    TextField input_idwyd;

    @FXML
    private TableView<Ksiazka> booktable;
    @FXML
    private TableColumn<Ksiazka, Integer> columnId;
    @FXML
    private TableColumn<Ksiazka, String> columnTytul;
    @FXML
    private TableColumn<Ksiazka, Integer> columnIdWydawnictwo;
    @FXML
    private TableColumn<Ksiazka, String> columnWydawnictwo;
    @FXML
    private TableColumn<Ksiazka, String> columnAutorzy;
    @FXML
    VBox ksiazkiMain;
    @FXML
    private CheckBox checkBoxId;
    @FXML
    private CheckBox checkBoxTytul;
    @FXML
    private CheckBox checkBoxIdWydawnictwo;
    @FXML
    private CheckBox checkBoxWydawnictwo;
    @FXML
    private CheckBox checkBoxAutorzy;
    public static String zapisaneWydId = "";

    @FXML
    private void initialize() {
        close();
        checkBoxId.setSelected(true);
        checkBoxTytul.setSelected(true);
        checkBoxIdWydawnictwo.setSelected(true);
        checkBoxWydawnictwo.setSelected(true);
        checkBoxAutorzy.setSelected(true);
        booktable.setOnMouseClicked(this::handleTableRowClick);
        input_idwyd.setText(zapisaneWydId);
    }

    @FXML
    public void close() {
        ksiazkiMain.setVisible(false);
    }

    @FXML
    void wys() {
        ksiazkiMain.setVisible(true);
        try (DatabaseConnection db = new DatabaseConnection()) {
            booktable.getItems().clear();
            columnId.setVisible(checkBoxId.isSelected());
            columnTytul.setVisible(checkBoxTytul.isSelected());
            columnIdWydawnictwo.setVisible(checkBoxIdWydawnictwo.isSelected());
            columnWydawnictwo.setVisible(checkBoxWydawnictwo.isSelected());
            columnAutorzy.setVisible(checkBoxAutorzy.isSelected());
            if (!checkBoxId.isSelected() && !checkBoxTytul.isSelected() && !checkBoxIdWydawnictwo.isSelected() &&
                    !checkBoxWydawnictwo.isSelected() && !checkBoxAutorzy.isSelected()) {
                return;
            }
            booktable.getItems().setAll(fetchKsiazkiFromDatabase(db));
        } catch (SQLException e) {
            Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private List<Ksiazka> fetchKsiazkiFromDatabase(DatabaseConnection db) throws SQLException {
        List<Ksiazka> ksiazki = new ArrayList<>();

        StringBuilder queryBuilder = getStringBuilderForKsiazki();

        try (Statement statement = db.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(queryBuilder.toString())) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_ksiazka");
                String tytul = (checkBoxTytul.isSelected()) ? resultSet.getString("tytul") : null;
                int idWydawnictwo = (checkBoxIdWydawnictwo.isSelected()) ? resultSet.getInt("id_wydawnictwo") : 0;
                String wydawnictwo = (checkBoxWydawnictwo.isSelected()) ? resultSet.getString("wydawnictwo") : null;
                String autorzy = (checkBoxAutorzy.isSelected()) ? resultSet.getString("autorzy") : null;

                Ksiazka ksiazka = new Ksiazka(id, tytul, idWydawnictwo, wydawnictwo, autorzy);
                ksiazki.add(ksiazka);
            }
        }

        return ksiazki;
    }


    private StringBuilder getStringBuilderForKsiazki() {
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append("id_ksiazka, ");
        if (checkBoxTytul.isSelected()) {
            queryBuilder.append("tytul, ");
        }
        if (checkBoxIdWydawnictwo.isSelected()) {
            queryBuilder.append("id_wydawnictwo, ");
        }
        if (checkBoxWydawnictwo.isSelected()) {
            queryBuilder.append("wydawnictwo, ");
        }
        if (checkBoxAutorzy.isSelected()) {
            queryBuilder.append("autorzy, ");
        }
        if (queryBuilder.length() > 0 && queryBuilder.charAt(queryBuilder.length() - 1) == ' ') {
            queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
        }
        queryBuilder.append(" FROM WIDOK_KSIAZKI_AUTORZY");
        return queryBuilder;
    }

    @FXML
    void dodaj() {
        String tytul = input_tyt.getText();
        String idWydawnictwoStr = input_idwyd.getText();

        if (tytul.isEmpty()) {
            Utilities.showAlert("Błąd", "Tytuł musi być podany!", Alert.AlertType.ERROR);
            return;
        }
        try {
            int idWydawnictwo = idWydawnictwoStr.isEmpty() ? 0 : Integer.parseInt(idWydawnictwoStr);
            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call DodajKsiazke(?, ?, ?)}");
                callableStatement.setString(1, tytul);
                callableStatement.setInt(2, idWydawnictwo);
                callableStatement.registerOutParameter(3, Types.NUMERIC);
                callableStatement.execute();
                int successFlag = callableStatement.getInt(3);
                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Dodano książkę.", Alert.AlertType.INFORMATION);
                    clearInputs();
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się dodać książki.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                System.err.println(e.getLocalizedMessage());
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID wydawnictwa musi być liczbą całkowitą!", Alert.AlertType.ERROR);
        }
    }

    private void clearInputs() {
    }

    @FXML
    void usun() {
        String idStr = input_id.getText();

        if (idStr.isEmpty()) {
            Utilities.showAlert("Błąd", "ID książki musi być podane!", Alert.AlertType.ERROR);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call UsunKsiazke(?, ?)}");
                callableStatement.setInt(1, id);
                callableStatement.registerOutParameter(2, Types.NUMERIC);
                callableStatement.execute();

                int successFlag = callableStatement.getInt(2);

                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Usunięto książkę.", Alert.AlertType.INFORMATION);
                    clearInputs();
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się usunąć książki.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                System.err.println(e.getLocalizedMessage());
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID książki musi być liczbą całkowitą!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void edytuj() {
        String idStr = input_id.getText();
        String tytul = input_tyt.getText();
        String idWydawnictwoStr = input_idwyd.getText();

        if (idStr.isEmpty()) {
            Utilities.showAlert("Błąd", "ID książki musi być podane!", Alert.AlertType.ERROR);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            int idWydawnictwo = (idWydawnictwoStr.isEmpty()) ? -1 : Integer.parseInt(idWydawnictwoStr);

            try (DatabaseConnection db = new DatabaseConnection()) {
                CallableStatement callableStatement = db.getConnection().prepareCall("{call EdytujKsiazke(?, ?, ?, ?)}");
                callableStatement.setInt(1, id);
                callableStatement.setString(2, tytul);
                callableStatement.setInt(3, idWydawnictwo);
                callableStatement.registerOutParameter(4, Types.NUMERIC);
                callableStatement.execute();

                int successFlag = callableStatement.getInt(4);

                if (successFlag == 1) {
                    Utilities.showAlert("Informacja", "Zaktualizowano książkę.", Alert.AlertType.INFORMATION);
                    clearInputs();
                } else {
                    Utilities.showAlert("Błąd!", "Nie udało się zaktualizować książki.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                System.err.println(e.getLocalizedMessage());
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID książki musi być liczbą całkowitą!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void wroc() {
        MainWindow.openScene();
    }

    public void wypozyczZapisz(MouseEvent mouseEvent) {
        Egzemplarze.zapisKsiazka = input_id.getText();
    }

    public void refresh(ActionEvent actionEvent) {
        wys();
    }

    private void handleTableRowClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Ksiazka selectedKsiazka = booktable.getSelectionModel().getSelectedItem();
            if (selectedKsiazka != null) {
                input_id.setText(String.valueOf(selectedKsiazka.getId()));
                close();
            }
        }
    }

    public void wyczyscWyd(MouseEvent mouseEvent) {
        zapisaneWydId = "";
        input_idwyd.setText("");
    }

    @FXML
    public void zapiszAutor(MouseEvent mouseEvent) {
        AutorzyKsiazek.zapisaneIDKsiazki = input_id.getText();
    }
}
