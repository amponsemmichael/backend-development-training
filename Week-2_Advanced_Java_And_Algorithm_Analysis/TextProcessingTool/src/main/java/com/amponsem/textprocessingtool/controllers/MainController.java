package com.amponsem.textprocessingtool.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MainController {
    @FXML private TextArea textArea;
    @FXML private TextField regexField;
    @FXML private TextField replaceField;
    @FXML private Button searchButton;
    @FXML private Button matchButton;
    @FXML private Button replaceButton;

    @FXML
    public void initialize() {
        searchButton.setOnAction(e -> searchText());
        matchButton.setOnAction(e -> matchText());
        replaceButton.setOnAction(e -> replaceText());
    }

    private void searchText() {
        String text = textArea.getText();
        String regex = regexField.getText();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            textArea.selectRange(matcher.start(), matcher.end());
        }
    }

    private void matchText(){
        String text = textArea.getText();
        String regex = regexField.getText();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        boolean matches = matcher.matches();
        showAlert(matches ? "Match" : "No Match" , matches ? "The text matches the pattern!" :
                                "The text does not match the pattern");
    }

    private void replaceText(){
        String text = textArea.getText();
        String regex = regexField.getText();
        String replacement = replaceField.getText();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        String replacedText = matcher.replaceAll(replacement);
        textArea.setText(replacedText);
    }

    private void showAlert(String title, String content){
        Alert alert =  new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
