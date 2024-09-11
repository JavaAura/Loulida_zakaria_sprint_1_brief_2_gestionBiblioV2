package com.library.management.dao;

import com.library.management.model.user.Etudiant;
import com.library.management.model.user.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EtudiantDAO implements UserDAO {

    @Override
    public void create(Utilisateur user) {
        Etudiant etudiant = (Etudiant) user;
        String sql = "INSERT INTO Etudiant (nom, prenom, email, mot_de_passe, date_inscription, niveau_etudes) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, etudiant.getNom());
            pstmt.setString(2, etudiant.getPrenom());
            pstmt.setString(3, etudiant.getEmail());
            pstmt.setString(4, etudiant.getMotDePasse());
            pstmt.setDate(5, java.sql.Date.valueOf(etudiant.getDateInscription()));
            pstmt.setString(6, etudiant.getNiveauEtudes());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void update(Utilisateur user) {
        Etudiant etudiant = (Etudiant) user;
        String sql = "UPDATE Etudiant SET nom = ?, prenom = ?, email = ?, mot_de_passe = ?, date_inscription = ?, niveau_etudes = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, etudiant.getNom());
            pstmt.setString(2, etudiant.getPrenom());
            pstmt.setString(3, etudiant.getEmail());
            pstmt.setString(4, etudiant.getMotDePasse());
            pstmt.setDate(5, java.sql.Date.valueOf(etudiant.getDateInscription()));
            pstmt.setString(6, etudiant.getNiveauEtudes());
            pstmt.setInt(7, etudiant.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Etudiant WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Etudiant deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Utilisateur getUser(int id) {
        String sql = "SELECT * FROM Etudiant WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Etudiant etudiant = new Etudiant(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getDate("date_inscription").toLocalDate(),
                        rs.getString("niveau_etudes")
                );
                etudiant.setId(id);
                return etudiant;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null; // Return null if no user is found
    }

    @Override
    public List<Utilisateur> findAll() {
        List<Utilisateur> etudiants = new ArrayList<>();
        String sql = "SELECT * FROM Etudiant";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) { // Correctly execute the query
            while (rs.next()) {
                Etudiant etudiant = new Etudiant(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getDate("date_inscription").toLocalDate(),
                        rs.getString("niveau_etudes")
                );
                etudiant.setId(rs.getInt("id"));
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return etudiants;
    }
}
