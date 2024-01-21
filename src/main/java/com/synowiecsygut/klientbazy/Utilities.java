package com.synowiecsygut.klientbazy;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Utilities
{
    public static void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public static Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font(26.0));
        label.setPrefHeight(40.0);
        label.setPrefWidth(1269.0);
        label.setAlignment(Pos.CENTER);
        return label;
    }

}
