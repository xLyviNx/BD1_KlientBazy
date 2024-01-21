package com.synowiecsygut.klientbazy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Egzemplarze
{
    @FXML
    TextField input_id;
    @FXML
    TextField input_idksia;
    @FXML
    TextField input_idwyd;
    @FXML
    TextField input_rokwyd;
    @FXML
    TextField input_stan;
    public static String zapisKsiazka = "";

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

    public void wypozyczZapisz(MouseEvent mouseEvent)
    {
        Wypozyczenia.egzZapis=input_id.getText();
    }
}

