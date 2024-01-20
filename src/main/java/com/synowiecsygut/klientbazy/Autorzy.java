package com.synowiecsygut.klientbazy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

public class Autorzy
{
    @FXML
    TextField input_id;
    @FXML
    TextField input_naz;
    @FXML
    TextField input_imie;


    @FXML
    void dodaj()
    {
        System.out.println("Gruby");
    }

    @FXML
    void usun()
    {
        System.out.println("ty");
    }

    @FXML
    void edytuj()
    {
        System.out.println("jebany");
    }

    @FXML
    void wys()
    {
        System.out.println("zaklamany");
    }

    @FXML
    void wroc()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("StronaGlowna.fxml"));
    }
}
