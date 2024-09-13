package com.library.management.dao;

import com.library.management.interfaces.Empruntable;
import com.library.management.model.Borrowing;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowingDAOImpl  implements Empruntable {


    public void Emprunter(Borrowing borrowing) {



        String sql = "INSERT INTO Borrowing (user_id, document_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, borrowing.getUserId());
            pstmt.setInt(2, borrowing.getDocumentId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean checkIfIsBorrowed(int doc_id)  {
        String sql = "SELECT * FROM borrowing WHERE document_id = ? ";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doc_id);
            ResultSet rs = stmt.executeQuery();
            return !rs.next();
        }  catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
        return false;

    }






    public void Retourner(int id) {
        String sql = "DELETE FROM Borrowing WHERE document_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("The document has been successfully returned.");
            } else {
                System.out.println("This document is not currently borrowed.");
            }

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
                        rs.getInt("document_id")
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
                        rs.getInt("document_id")
            ));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return borrowings;
    }
}
