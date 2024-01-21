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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Ksiazki
{
    @FXML
    TextField input_id;
    @FXML
    TextField input_tyt;
    @FXML
    TextField input_idwyd;

    public static String zapisWydawnictwo = "";
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
    @FXML
    private void initialize()
    {
        close();
        checkBoxId.setSelected(true);
        checkBoxTytul.setSelected(true);
        checkBoxIdWydawnictwo.setSelected(true);
        checkBoxWydawnictwo.setSelected(true);
        checkBoxAutorzy.setSelected(true);
        booktable.setOnMouseClicked(this::handleTableRowClick);
    }
    @FXML
    public void close()
    {
        ksiazkiMain.setVisible(false);
    }
    @FXML
    void wys() {
        ksiazkiMain.setVisible(true);
        try (DatabaseConnection db = new DatabaseConnection()) {
            booktable.getItems().clear(); // Wyczyść istniejące dane w tabeli
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
    void dodaj()
    {
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

    public void wypozyczZapisz(MouseEvent mouseEvent)
    {
        Egzemplarze.zapisKsiazka=input_id.getText();
    }

    public void refresh(ActionEvent actionEvent)
    {
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
}
