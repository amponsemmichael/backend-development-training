package com.amponsem.librarymanagementsystem.repositories;

import com.amponsem.librarymanagementsystem.models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    private static DatabaseHandler handler = null;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/librarydb";
    private static final String USER = "root";
    private static final String PASSWORD = "micky!!@@020";
    private Connection conn = null;

    private DatabaseHandler() {
        createConnection();
    }

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addBook(Book book) {
        String query = "INSERT INTO book (title, category_id, publication_date, copies_owned) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setInt(2, book.getCategoryId());
            stmt.setDate(3, Date.valueOf(book.getPublicationDate()));
            stmt.setInt(4, book.getCopiesOwned());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateBook(Book book) {
        String query = "UPDATE book SET title = ?, category_id = ?, publication_date = ?, copies_owned = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setInt(2, book.getCategoryId());
            stmt.setDate(3, Date.valueOf(book.getPublicationDate()));
            stmt.setInt(4, book.getCopiesOwned());
            stmt.setInt(5, book.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM book";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setCategoryId(rs.getInt("category_id"));
                book.setPublicationDate(rs.getDate("publication_date").toLocalDate());
                book.setCopiesOwned(rs.getInt("copies_owned"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public boolean deleteBook(int bookId) {
        String query = "DELETE FROM book WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
