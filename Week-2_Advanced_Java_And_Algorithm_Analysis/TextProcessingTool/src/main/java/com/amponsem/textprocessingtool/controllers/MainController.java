package com.amponsem.textprocessingtool.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

public class MainController {
    @FXML private TextArea textArea;
    @FXML private TextField regexField;
    @FXML private TextField replaceField;
    @FXML private Button searchButton;
    @FXML private Button matchButton;
    @FXML private Button replaceButton;

    @FXML private MenuItem menuItemNew;
    @FXML private MenuItem menuItemOpen;
    @FXML private MenuItem menuItemSave;
    @FXML private MenuItem menuItemExit;
    @FXML private MenuItem menuItemUndo;
    @FXML private MenuItem menuItemRedo;
    @FXML private MenuItem menuItemCut;
    @FXML private MenuItem menuItemCopy;
    @FXML private MenuItem menuItemPaste;
    @FXML private MenuItem menuItemDelete;
    @FXML private MenuItem menuItemWordWrap;
    @FXML private MenuItem menuItemZoomIn;
    @FXML private MenuItem menuItemZoomOut;

    private int lastMatchIndex = 0;
    private double zoomFactor = 1.0;

    private ArrayList<int[]> searchResults = new ArrayList<>();
    private ArrayList<String> undoStack= new ArrayList<>();
    private ArrayList<String> redoStack = new ArrayList<>();


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
            int start = matcher.start();
            int end = matcher.end();
            searchResults.add(new int[]{start, end});
            textArea.selectRange(matcher.start(), matcher.end());
            textArea.selectRange(start, end);
        }
        if(searchResults.isEmpty()){
            showAlert("Search results", "No matches found");
        } else {
            showAlert("Search Results", searchResults.size() + "matches found");
        }
    }
    @FXML
    private void handleSearchButtonAction() {
        String text = textArea.getText();
        String regex = regexField.getText();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find(lastMatchIndex)) {
            textArea.selectRange(matcher.start(), matcher.end());
            lastMatchIndex = matcher.end(); // Update last match index
        } else {
            lastMatchIndex = 0; // Reset if no more matches found
        }
    }

    @FXML
    private void handleMatchButtonAction() {
        String text = textArea.getText();
        String regex = regexField.getText();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        StringBuilder matches = new StringBuilder();
        while (matcher.find()) {
            matches.append(matcher.group()).append("\n");
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Matches Found");
        alert.setHeaderText(null);
        alert.setContentText(matches.toString());
        alert.showAndWait();
    }

    @FXML
    private void handleReplaceButtonAction() {
        String text = textArea.getText();
        String regex = regexField.getText();
        String replaceWith = replaceField.getText();
        text = text.replaceAll(regex, replaceWith);
        textArea.setText(text);
    }

    private void matchText() {
        String text = textArea.getText();
        String regex = regexField.getText();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        boolean matches = matcher.matches();
        showAlert(matches ? "Match" : "No Match", matches ? "The text matches the pattern!" :
                "The text does not match the pattern");
    }

    private void replaceText() {
        String text = textArea.getText();
        String regex = regexField.getText();
        String replacement = replaceField.getText();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        String replacedText = matcher.replaceAll(replacement);

        undoStack.add(text);
        textArea.setText(replacedText);
    }

    private void redo() {
        if(!redoStack.isEmpty()) {
            String lastUndoState = redoStack.remove(redoStack.size() - 1);
            undoStack.add(textArea.getText());
            textArea.setText(lastUndoState);
        } else {
            showAlert("Redo", "No actions to redo");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void handleMenuItemNewAction() {
        textArea.clear();
    }

    @FXML
    private void handleMenuItemOpenAction() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            Path path = Paths.get(file.getAbsolutePath());
            try {
                String content = new String(Files.readAllBytes(path));
                textArea.setText(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleMenuItemSaveAction(){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(new Stage());
        if(file != null){
            Path path = Paths.get(file.getAbsolutePath());
            try{
                Files.write(path, textArea.getText().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML private void handleMenuItemExitAction(){
        Stage stage = (Stage) textArea.getScene().getWindow();
        stage.close();
    }

  @FXML private void handleMenuItemUndoAction(){
        textArea.undo();
    }

    @FXML private void handleMenuItemRedoAction(){
        textArea.redo();
    }
    @FXML private void handleMenuItemCutAction(){
        textArea.cut();
    }

    @FXML private void handleMenuItemCopyAction(){
        textArea.copy();
    }

    @FXML private void handleMenuItemPasteAction(){
        textArea.paste();
    }

    @FXML private void handleMenuItemDeleteAction(){
        textArea.deleteText(textArea.getSelection());
    }

    @FXML private void handleMenuItemWordWrapAction(){
        textArea.setWrapText(!textArea.isWrapText());
    }

    @FXML private void handleMenuItemZoomInAction(){
        zoomFactor += 0.1;
        textArea.setStyle("-fx-font-size: " + (12 * zoomFactor) + "px;");
}
    @FXML private void handleMenuItemZoomOutAction(){
        zoomFactor -= 0.1;
         textArea.setStyle("-fx-font-size: " + (12 * zoomFactor) + "px;");
}
}
