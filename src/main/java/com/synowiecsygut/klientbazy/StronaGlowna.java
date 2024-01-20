package com.synowiecsygut.klientbazy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class StronaGlowna
{
    @FXML
    void prac()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("Pracownicy.fxml"));
    }

    @FXML
    void aut()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("Autorzy.fxml"));
    }

    @FXML
    void czyt()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("Czytelnicy.fxml"));
    }

    @FXML
    void egze()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("Egzemplarze.fxml"));
    }

    @FXML
    void ksi()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("Ksiazki.fxml"));
    }

    @FXML
    void wyd()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("Wydawnictwa.fxml"));
    }

    @FXML
    void wyp()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("Wypozyczenia.fxml"));
    }
}
