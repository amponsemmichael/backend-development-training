
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
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private final ArrayList<int[]> searchResults = new ArrayList<>();
    private final ArrayList<String> undoStack = new ArrayList<>();
    private final ArrayList<String> redoStack = new ArrayList<>();

    @FXML
    private void initialize() {
        searchButton.setOnAction(e -> handleSearchButtonAction());
        matchButton.setOnAction(e -> handleMatchButtonAction());
        replaceButton.setOnAction(e -> handleReplaceButtonAction());

        // Call examplePatterns to demonstrate regex capabilities
        examplePatterns();
    }

    // Regular Expressions (Regex) Module
    @FXML
    private void handleSearchButtonAction() {
        String text = textArea.getText();
        String regex = regexField.getText();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        searchResults.clear();
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            searchResults.add(new int[]{start, end});
            textArea.selectRange(start, end);
        }

        if (searchResults.isEmpty()) {
            showAlert("Search results", "No matches found");
        } else {
            showAlert("Search Results", searchResults.size() + " matches found");
        }
    }

    @FXML
    private void handleMatchButtonAction() {
        String text = textArea.getText();
        String regex = regexField.getText();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        boolean matches = matcher.matches();
        showAlert(matches ? "Match" : "No Match", matches ? "The text matches the pattern!" : "The text does not match the pattern");
    }

    @FXML
    private void handleReplaceButtonAction() {
        String text = textArea.getText();
        String regex = regexField.getText();
        String replacement = replaceField.getText();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        String replacedText = matcher.replaceAll(replacement);

        undoStack.add(text);
        textArea.setText(replacedText);
    }

    // Data Management Module
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Menu Item Actions
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
    private void handleMenuItemSaveAction() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            Path path = Paths.get(file.getAbsolutePath());
            try {
                Files.write(path, textArea.getText().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleMenuItemExitAction() {
        Stage stage = (Stage) textArea.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleMenuItemUndoAction() {
        if (!undoStack.isEmpty()) {
            String lastState = undoStack.remove(undoStack.size() - 1);
            redoStack.add(textArea.getText());
            textArea.setText(lastState);
        } else {
            showAlert("Undo", "No actions to undo");
        }
    }

    @FXML
    private void handleMenuItemRedoAction() {
        if (!redoStack.isEmpty()) {
            String lastUndoState = redoStack.remove(redoStack.size() - 1);
            undoStack.add(textArea.getText());
            textArea.setText(lastUndoState);
        } else {
            showAlert("Redo", "No actions to redo");
        }
    }

    @FXML
    private void handleMenuItemCutAction() {
        textArea.cut();
    }

    @FXML
    private void handleMenuItemCopyAction() {
        textArea.copy();
    }

    @FXML
    private void handleMenuItemPasteAction() {
        textArea.paste();
    }

    @FXML
    private void handleMenuItemDeleteAction() {
        textArea.deleteText(textArea.getSelection());
    }

    @FXML
    private void handleMenuItemWordWrapAction() {
        textArea.setWrapText(!textArea.isWrapText());
    }

    @FXML
    private void handleMenuItemZoomInAction() {
        zoomFactor += 0.1;
        textArea.setStyle("-fx-font-size: " + (12 * zoomFactor) + "px;");
    }

    @FXML
    private void handleMenuItemZoomOutAction() {
        zoomFactor -= 0.1;
        textArea.setStyle("-fx-font-size: " + (12 * zoomFactor) + "px;");
    }

    // Example usage of regex patterns
    private void examplePatterns() {
        // Example text to search
        String text = "Contact us at example@example.com or visit our website http://example.com on 2024-06-27.";

        // Email pattern
        String emailPattern = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$";
        searchTextWithPattern(text, emailPattern);

        // Phone number pattern
        String phoneNumberPattern = "^\\+?[0-9]{1,3}?[-.●]?[0-9]{1,4}[-.●]?[0-9]{1,4}[-.●]?[0-9]{1,9}$";
        searchTextWithPattern(text, phoneNumberPattern);

        // URL pattern
        String urlPattern = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";
        searchTextWithPattern(text, urlPattern);

        // Date pattern
        String datePattern = "^\\d{4}-\\d{2}-\\d{2}$";
        searchTextWithPattern(text, datePattern);

        // Hex color code pattern
        String hexColorPattern = "^#?([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$";
        searchTextWithPattern(text, hexColorPattern);
    }

    private void searchTextWithPattern(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            System.out.println("Match found: " + matcher.group());
        } else {
            System.out.println("No match found for pattern: " + regex);
        }
    }
}
