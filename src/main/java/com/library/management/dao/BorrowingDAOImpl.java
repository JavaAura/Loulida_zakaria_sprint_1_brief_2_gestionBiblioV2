package com.library.management.dao;

import com.library.management.model.Borrowing;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowingDAOImpl  {


    public void create(Borrowing borrowing) {


        System.out.println("id in dao "+ borrowing.getUserId()+" "+ Date.valueOf(borrowing.getBorrowingDate()) );

        String sql = "INSERT INTO Borrowing (user_id, document_id, borrowing_date, return_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, borrowing.getUserId());
            pstmt.setInt(2, borrowing.getDocumentId());
            pstmt.setDate(3, Date.valueOf(borrowing.getBorrowingDate()));
            pstmt.setDate(4, borrowing.getReturnDate() != null ? Date.valueOf(borrowing.getReturnDate()) : null);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void update(Borrowing borrowing) {
        String sql = "UPDATE Borrowing SET user_id = ?, document_id = ?, borrowing_date = ?, return_date = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, borrowing.getUserId());
            pstmt.setInt(2, borrowing.getDocumentId());
            pstmt.setDate(3, Date.valueOf(borrowing.getBorrowingDate()));
            pstmt.setDate(4, borrowing.getReturnDate() != null ? Date.valueOf(borrowing.getReturnDate()) : null);
            pstmt.setInt(5, borrowing.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void delete(int id) {
        String sql = "DELETE FROM Borrowing WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public Borrowing getBorrowing(int id) {
        String sql = "SELECT * FROM Borrowing WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Borrowing(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("document_id"),
                        rs.getDate("borrowing_date").toLocalDate(),
                        rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null
                );
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }


    public List<Borrowing> findAll() {
        List<Borrowing> borrowings = new ArrayList<>();
        String sql = "SELECT * FROM Borrowing";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                borrowings.add(new Borrowing(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("document_id"),
                        rs.getDate("borrowing_date").toLocalDate(),
                        rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return borrowings;
    }
}
