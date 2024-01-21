package com.synowiecsygut.klientbazy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.LocalDate;

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
    TextField input_datazwr;

    public static String pracWypo = "";
    public static String pracZwrot = "";
    public static String czytZapis = "";
    public static String egzZapis = "";
    @FXML
    void initialize()
    {
        input_idpracwyp.setText(pracWypo);
        input_idpraczwr.setText(pracZwrot);
        input_idczyt.setText(czytZapis);
        input_idegze.setText(egzZapis);
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
    void wys()
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

    public void wyczyscZapis(MouseEvent mouseEvent)
    {
        pracWypo = "";
        pracZwrot = "";
        czytZapis = "";
        egzZapis = "";
    }

    public void dataDzis(MouseEvent mouseEvent)
    {
        wypoData.setValue(LocalDate.now());
    }
}
