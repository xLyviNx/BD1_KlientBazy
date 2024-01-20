package com.synowiecsygut.klientbazy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;

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
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1280, 800);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainWindow.mainStage.setScene(scene);
    }
}
