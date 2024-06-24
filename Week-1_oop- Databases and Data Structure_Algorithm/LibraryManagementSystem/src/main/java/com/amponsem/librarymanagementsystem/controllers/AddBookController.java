package com.amponsem.librarymanagementsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddBookController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField categoryIdField;
    @FXML
    private DatePicker publicationDatePicker;
    @FXML
    private TextField copiesOwnedField;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleSave() {
        String title = titleField.getText();
        int categoryId = Integer.parseInt(categoryIdField.getText());
        LocalDate publicationDate = publicationDatePicker.getValue();
        int copiesOwned = Integer.parseInt(copiesOwnedField.getText());

        saveBookToDatabase(title, categoryId, publicationDate, copiesOwned);
    }

    @FXML
    private void handleCancel() {
        stage.close();
    }

    private void saveBookToDatabase(String title, int categoryId, LocalDate publicationDate, int copiesOwned) {
        String url = "jdbc:mysql://localhost:3306/librarydb";
        String user = "root";
        String password = "micky!!@@020";

        String sql = "INSERT INTO book (title, category_id, publication_date, copies_owned) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setInt(2, categoryId);
            pstmt.setDate(3, java.sql.Date.valueOf(publicationDate));
            pstmt.setInt(4, copiesOwned);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
