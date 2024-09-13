package com.library.management.dao;


import com.library.management.dao.DatabaseConnection;
import com.library.management.interfaces.Reservable;
import com.library.management.model.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements Reservable {


    public void Reserver(Reservation reservation) {
        String sql = "INSERT INTO Reservation (user_id, document_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservation.getUserId());
            pstmt.setInt(2, reservation.getDocumentId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }




    public boolean CheckIftheyCanReserve(int doc_id)  {
        String sql = "SELECT * FROM reservation WHERE document_id = ? ";
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


    public void AnnulerReservation(int id) {
        String sql = "DELETE FROM Reservation WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public Reservation getReservation(int id) {
        String sql = "SELECT * FROM Reservation WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Reservation(
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


    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM Reservation";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("document_id")

                ));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return reservations;
    }
}

