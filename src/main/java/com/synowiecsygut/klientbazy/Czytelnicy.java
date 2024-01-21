package com.synowiecsygut.klientbazy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
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
                System.err.println(e.getLocalizedMessage());
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
                System.err.println(e.getLocalizedMessage());
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
                System.err.println(e.getLocalizedMessage());
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert("Błąd", "ID czytelnika musi być liczbą całkowitą!", Alert.AlertType.ERROR);
        }
    }
    @FXML
    void wys() {
        czytelnicyMain.setVisible(true);
        czytelnicyParent.getChildren().clear();
        czytelnicyParent.getChildren().add(createTemplateHBox(new Czytelnik(0, "Imię", "Nazwisko", "Ulica", "Nr domu", "Nr lokalu", "Kod pocztowy", 0.0F)));

        try (DatabaseConnection db = new DatabaseConnection()) {
            List<Czytelnik> czytelnicy = fetchCzytelnicyFromDatabase(db);

            for (Czytelnik czytelnik : czytelnicy) {
                HBox templateHBox = createTemplateHBox(czytelnik);
                czytelnicyParent.getChildren().add(templateHBox);
            }

        } catch (SQLException e) {
            Utilities.showAlert("Błąd!", "Błąd bazy danych!\n" + e.getLocalizedMessage(), Alert.AlertType.ERROR);
            System.err.println(e.getLocalizedMessage());
        }
    }

    private HBox createTemplateHBox(Czytelnik czytelnik) {
        HBox templateHBox = new HBox(5);

        if (checkBoxId.isSelected()) {
            if (czytelnik.getId() > 0) {
                templateHBox.getChildren().add(Utilities.createLabel(String.valueOf(czytelnik.getId())));
            } else {
                templateHBox.getChildren().add(Utilities.createLabel("ID"));
            }
        }
        if (checkBoxImie.isSelected()) {
            templateHBox.getChildren().add(Utilities.createLabel(czytelnik.getImie()));
        }
        if (checkBoxNazwisko.isSelected()) {
            templateHBox.getChildren().add(Utilities.createLabel(czytelnik.getNazwisko()));
        }
        if (checkBoxUlica.isSelected()) {
            templateHBox.getChildren().add(Utilities.createLabel(czytelnik.getUlica()));
        }
        if (checkBoxNrDomu.isSelected()) {
            templateHBox.getChildren().add(Utilities.createLabel(czytelnik.getNrDomu()));
        }
        if (checkBoxNrLokalu.isSelected()) {
            templateHBox.getChildren().add(Utilities.createLabel(czytelnik.getNrLokalu()));
        }
        if (checkBoxKodPocztowy.isSelected()) {
            templateHBox.getChildren().add(Utilities.createLabel(czytelnik.getKodPocztowy()));
        }
        if (checkBoxKara.isSelected()) {
            if (czytelnik.getId() > 0) {
                DecimalFormat decimalFormat = new DecimalFormat("#,###,##0.00 zł");
                String formattedKara = decimalFormat.format(czytelnik.getKara());

                templateHBox.getChildren().add(Utilities.createLabel(formattedKara));
            } else {
                templateHBox.getChildren().add(Utilities.createLabel("Kara"));
            }
        }

        if (czytelnik.getId() < 1) {
            templateHBox.setStyle("-fx-background-color: rgba(0,0,0,0.8)");
        } else {
            templateHBox.setOnMouseClicked(event -> {
                input_id.setText(String.valueOf(czytelnik.getId()));
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

                Czytelnik czytelnik = new Czytelnik(id, imie, nazwisko, ulica, nrDomu, nrLokalu, kodPocztowy, kara);
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

        queryBuilder.append(" FROM czytelnicy ORDER BY id_czytelnik");
        return queryBuilder;
    }

    @FXML
    public void close() {
        czytelnicyMain.setVisible(false);
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

    @FXML
    public void refresh() {
        wys();
    }

    public void wypozyczZapisz(MouseEvent mouseEvent)
    {
        Wypozyczenia.czytZapis=input_id.getText();
    }
}
