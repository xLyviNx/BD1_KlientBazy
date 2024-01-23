package com.synowiecsygut.klientbazy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class StronaGlowna
{
    @FXML
    void prac()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("Pracownicy.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1280, 800);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainWindow.mainStage.setScene(scene);
    }

    @FXML
    void aut()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("Autorzy.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1280, 800);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainWindow.mainStage.setScene(scene);
    }

    @FXML
    void czyt()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("Czytelnicy.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1280, 800);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainWindow.mainStage.setScene(scene);
    }

    @FXML
    void egze()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("Egzemplarze.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1280, 800);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainWindow.mainStage.setScene(scene);
    }

    @FXML
    void ksi()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("Ksiazki.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1280, 800);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainWindow.mainStage.setScene(scene);
    }

    @FXML
    void wyd()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("Wydawnictwa.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1280, 800);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainWindow.mainStage.setScene(scene);
    }

    @FXML
    void wyp()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("Wypozyczenia.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1280, 800);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainWindow.mainStage.setScene(scene);
    }

    public void autorzyKsi(ActionEvent actionEvent)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("AutorzyKsiazek.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1280, 800);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainWindow.mainStage.setScene(scene);
    }
}
