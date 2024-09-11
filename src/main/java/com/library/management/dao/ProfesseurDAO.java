package com.library.management.dao;

import com.library.management.model.user.Professeur;
import com.library.management.model.user.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfesseurDAO implements UserDAO {

    @Override
    public void create(Utilisateur user) {
        Professeur professeur = (Professeur) user;
        String sql = "INSERT INTO Professeur (nom, prenom, email, mot_de_passe, date_inscription, domaine_recherche) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, professeur.getNom());
            pstmt.setString(2, professeur.getPrenom());
            pstmt.setString(3, professeur.getEmail());
            pstmt.setString(4, professeur.getMotDePasse());
            pstmt.setDate(5, java.sql.Date.valueOf(professeur.getDateInscription()));
            pstmt.setString(6, professeur.getDomaineRecherche());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void update(Utilisateur user) {
        Professeur professeur = (Professeur) user;
        String sql = "UPDATE Professeur SET nom = ?, prenom = ?, email = ?, mot_de_passe = ?, date_inscription = ?, domaine_recherche = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, professeur.getNom());
            pstmt.setString(2, professeur.getPrenom());
            pstmt.setString(3, professeur.getEmail());
            pstmt.setString(4, professeur.getMotDePasse());
            pstmt.setDate(5, java.sql.Date.valueOf(professeur.getDateInscription()));
            pstmt.setString(6, professeur.getDomaineRecherche());
            pstmt.setInt(7, professeur.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Professeur WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Professeur deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Utilisateur getUser(int id) {
        String sql = "SELECT * FROM Professeur WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Professeur professeur = new Professeur(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getDate("date_inscription").toLocalDate(),
                        rs.getString("domaine_recherche")
                );
                professeur.setId(id);
                return professeur;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null; // Return null if no user is found
    }


    @Override
    public List<Utilisateur> findAll() {
        List<Utilisateur> professeurs = new ArrayList<>();
        String sql = "SELECT * FROM Professeur";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Professeur professeur = new Professeur(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getDate("date_inscription").toLocalDate(),
                        rs.getString("domaine_recherche")
                );
                professeur.setId(rs.getInt("id"));
                professeurs.add(professeur);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return professeurs;
    }
}
