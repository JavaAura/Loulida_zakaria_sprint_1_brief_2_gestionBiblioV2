package com.library.management.dao;

import com.library.management.model.document.*;
import com.library.management.model.enums.DocumentType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAOImpl {


    public void createLivre(Livre livre)  {
       String insertLivreSQL = "INSERT INTO livres (title,author, publication_date, isbn) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmtLivre = conn.prepareStatement(insertLivreSQL)) {
//


                    stmtLivre.setString(1, livre.getTitle());
                    stmtLivre.setString(2, livre.getAuthor());
                    stmtLivre.setDate(3, Date.valueOf(livre.getPublicationDate()));
                    stmtLivre.setString(4, livre.getIsbn());
                    stmtLivre.executeUpdate();
System.out.println("livres added successfully");            }
         catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void createMagazine(Magazine magazine) {
      String insertMagazineSQL = "INSERT INTO magazines ( title, author, publication_date, numero) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement  stmtMagazine = conn.prepareStatement(insertMagazineSQL)) {

                    stmtMagazine.setString(1, magazine.getTitle());
                    stmtMagazine.setString(2, magazine.getAuthor());
                    stmtMagazine.setDate(3, Date.valueOf(magazine.getPublicationDate()));
                    stmtMagazine.setString(4, magazine.getNumero());
                    stmtMagazine.executeUpdate();

            System.out.println("magazines added successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create JournalScientifique
    public void createJournalScientifique(JournalScientifique journalScientifique) {
      String insertJournalSQL = "INSERT INTO journalscientifiques ( title, author, publication_date, domaine_recherche) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmtJournal = conn.prepareStatement(insertJournalSQL)) {

                    stmtJournal.setString(1, journalScientifique.getTitle());
                    stmtJournal.setString(2, journalScientifique.getAuthor());
                    stmtJournal.setDate(3, Date.valueOf(journalScientifique.getPublicationDate()));
                    stmtJournal.setString(4, journalScientifique.getDomaineRecherche());
                    stmtJournal.executeUpdate();


            System.out.println("journalscientifiques added successfully");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create TheseUniversitaire
    public void createTheseUniversitaire(TheseUniversitaire theseUniversitaire) {
     String insertTheseSQL = "INSERT INTO theseuniversitaires ( title, author, publication_date, universite, domaine) VALUES ( ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmtThese = conn.prepareStatement(insertTheseSQL)) {

                    stmtThese.setString(1, theseUniversitaire.getTitle());
                    stmtThese.setString(2, theseUniversitaire.getAuthor());
                    stmtThese.setDate(3, Date.valueOf(theseUniversitaire.getPublicationDate()));
                    stmtThese.setString(4, theseUniversitaire.getUniversité());
                    stmtThese.setString(5, theseUniversitaire.getDomaine());
                    stmtThese.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Read (Retrieve) Document by ID
    public Document getDocumentById(String id, String documentType) {
        String query = "SELECT * FROM " + documentType + " WHERE id = ?";
        Document document = null;
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                document = mapResultSetToDocument(rs, documentType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return document;
    }

    public Document getDocumentByTitle(String title, DocumentType type)  {
        String query = "";

        // Choose the query based on document type
        switch (type) {
            case livres:
                query = "SELECT * FROM livres WHERE title LIKE ?";
                break;
            case magazines:
                query = "SELECT * FROM magazines WHERE title LIKE ?";
                break;
            case journalscientifiques:
                query = "SELECT * FROM journalscientifiques WHERE title LIKE ?";
                break;
            case theseuniversitaires:
                query = "SELECT * FROM theseuniversitaires WHERE title LIKE ?";
                break;
        }
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = conn.prepareStatement(query)){
        statement.setString(1, "%" + title + "%"); // Use LIKE with wildcards
        ResultSet resultSet = statement.executeQuery();

        // Parse the result and return the document
        if (resultSet.next()) {
            switch (type) {
                case livres:
                    return new Livre(
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getDate("publication_date").toLocalDate(),
                            resultSet.getString("isbn")
                    );
                case magazines:
                    return new Magazine(
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getDate("publication_date").toLocalDate(),
                            resultSet.getString("numero")
                    );
                case journalscientifiques:
                    return new JournalScientifique(
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getDate("publication_date").toLocalDate(),
                            resultSet.getString("domaineRecherche")
                    );
                case theseuniversitaires:
                    return new TheseUniversitaire(
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getDate("publication_date").toLocalDate(),
                            resultSet.getString("universite"),
                            resultSet.getString("domaine")
                    );
            }
        }}catch (SQLException e){
            System.out.println("error" + e.getMessage());
        }

        return null; // Return null if no document is found
    }
    // Helper method to map ResultSet to Document object
    private Document mapResultSetToDocument(ResultSet rs, String documentType) throws SQLException {
        switch (documentType) {
            case "livres":
                return new Livre(rs.getString("title"), rs.getString("author"), rs.getDate("publication_date").toLocalDate(), rs.getString("isbn"));
            case "magazines":
                return new Magazine(rs.getString("title"), rs.getString("author"), rs.getDate("publication_date").toLocalDate(), rs.getString("numero"));
            case "journalscientifiques":
                return new JournalScientifique(rs.getString("title"), rs.getString("author"), rs.getDate("publication_date").toLocalDate(), rs.getString("domaine_recherche"));
            case "theseuniversitaires":
                return new TheseUniversitaire(rs.getString("title"), rs.getString("author"), rs.getDate("publication_date").toLocalDate(), rs.getString("universite"), rs.getString("domaine"));
            default:
                return null;
        }
    }

    // Update Document
    public void updateDocument(Document document) {
        String query;
        if (document instanceof Livre) {
            query = "UPDATE livres SET title = ?, author = ?, publication_date = ?, isbn = ? WHERE id = ?";
        } else if (document instanceof Magazine) {
            query = "UPDATE magazines SET title = ?, author = ?, publication_date = ?, numero = ? WHERE id = ?";
        } else if (document instanceof JournalScientifique) {
            query = "UPDATE journalscientifiques SET title = ?, author = ?, publication_date = ?, domaine_recherche = ? WHERE id = ?";
        } else if (document instanceof TheseUniversitaire) {
            query = "UPDATE theseuniversitaires SET title = ?, author = ?, publication_date = ?, universite = ?, domaine = ? WHERE id = ?";
        } else {
            throw new IllegalArgumentException("Unsupported document type");
        }

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, document.getTitle());
            stmt.setString(2, document.getAuthor());
            stmt.setDate(3, java.sql.Date.valueOf(document.getPublicationDate()));

            // Type-specific fields
            if (document instanceof Livre) {
                stmt.setString(4, ((Livre) document).getIsbn());
                stmt.setInt(5, document.getId());
            } else if (document instanceof Magazine) {
                stmt.setString(4, ((Magazine) document).getNumero());
                stmt.setInt(5, document.getId());
            } else if (document instanceof JournalScientifique) {
                stmt.setString(4, ((JournalScientifique) document).getDomaineRecherche());
                stmt.setInt(5, document.getId());
            } else if (document instanceof TheseUniversitaire) {
                stmt.setString(4, ((TheseUniversitaire) document).getUniversité());
                stmt.setString(5, ((TheseUniversitaire) document).getDomaine());
                stmt.setInt(6, document.getId());
            }


            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Document by ID
    public void deleteDocumentById(int id, DocumentType documentType) {
        String query = "DELETE FROM " + documentType + " WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    // Retrieve all documents of a specific type
    public List<Document> getAllDocuments(String documentType) {
        String query = "SELECT * FROM  " + documentType;
        List<Document> documents = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {


                documents.add(mapResultSetToDocument(rs, documentType));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documents;
    }


}
