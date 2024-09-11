package com.library.management.dao;


import com.library.management.dao.DatabaseConnection;
import com.library.management.model.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl {


    public void create(Reservation reservation) {
        String sql = "INSERT INTO Reservation (user_id, document_id, reservation_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservation.getUserId());
            pstmt.setInt(2, reservation.getDocumentId());
            pstmt.setDate(3, Date.valueOf(reservation.getReservationDate()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void update(Reservation reservation) {
        String sql = "UPDATE Reservation SET user_id = ?, document_id = ?, reservation_date = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservation.getUserId());
            pstmt.setInt(2, reservation.getDocumentId());
            pstmt.setDate(3, Date.valueOf(reservation.getReservationDate()));
            pstmt.setInt(4, reservation.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void delete(int id) {
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
                        rs.getInt("document_id"),
                        rs.getDate("reservation_date").toLocalDate()
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
                        rs.getInt("document_id"),
                        rs.getDate("reservation_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return reservations;
    }
}

