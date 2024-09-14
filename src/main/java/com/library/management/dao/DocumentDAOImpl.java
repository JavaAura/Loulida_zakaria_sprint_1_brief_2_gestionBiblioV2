package com.library.management.dao;

import com.library.management.model.document.*;
import com.library.management.model.enums.DocumentType;
import com.library.management.model.enums.Rule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAOImpl {


    public void createLivre(Livre livre) {
        String insertLivreSQL = "INSERT INTO livres (title, author, publication_date, isbn, rule) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmtLivre = conn.prepareStatement(insertLivreSQL)) {

            stmtLivre.setString(1, livre.getTitle());
            stmtLivre.setString(2, livre.getAuthor());
            stmtLivre.setDate(3, Date.valueOf(livre.getPublicationDate()));
            stmtLivre.setString(4, livre.getIsbn());

            // Set the enum as an object
            stmtLivre.setObject(5, livre.getRule(), Types.OTHER); // Correctly set enum type

            stmtLivre.executeUpdate();
            System.out.println("Livre added successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void createMagazine(Magazine magazine) {
      String insertMagazineSQL = "INSERT INTO magazines ( title, author, publication_date, numero,rule) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement  stmtMagazine = conn.prepareStatement(insertMagazineSQL)) {

                    stmtMagazine.setString(1, magazine.getTitle());
                    stmtMagazine.setString(2, magazine.getAuthor());
                    stmtMagazine.setDate(3, Date.valueOf(magazine.getPublicationDate()));
                    stmtMagazine.setString(4, magazine.getNumero());
            // Set the enum as an object
            stmtMagazine.setObject(5, magazine.getRule(), Types.OTHER); // Correctly set enum type

            stmtMagazine.executeUpdate();

            System.out.println("magazines added successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create JournalScientifique
    public void createJournalScientifique(JournalScientifique journalScientifique) {
      String insertJournalSQL = "INSERT INTO journalscientifiques ( title, author, publication_date, domaine_recherche,rule) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmtJournal = conn.prepareStatement(insertJournalSQL)) {

                    stmtJournal.setString(1, journalScientifique.getTitle());
                    stmtJournal.setString(2, journalScientifique.getAuthor());
                    stmtJournal.setDate(3, Date.valueOf(journalScientifique.getPublicationDate()));
                    stmtJournal.setString(4, journalScientifique.getDomaineRecherche());
            stmtJournal.setObject(5, journalScientifique.getRule(), Types.OTHER); // Correctly set enum type


            stmtJournal.executeUpdate();


            System.out.println("journalscientifiques added successfully");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create TheseUniversitaire
    public void createTheseUniversitaire(TheseUniversitaire theseUniversitaire) {
     String insertTheseSQL = "INSERT INTO theseuniversitaires ( title, author, publication_date, universite, domaine,rule) VALUES ( ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmtThese = conn.prepareStatement(insertTheseSQL)) {

                    stmtThese.setString(1, theseUniversitaire.getTitle());
                    stmtThese.setString(2, theseUniversitaire.getAuthor());
                    stmtThese.setDate(3, Date.valueOf(theseUniversitaire.getPublicationDate()));
                    stmtThese.setString(4, theseUniversitaire.getUniversité());
                    stmtThese.setString(5, theseUniversitaire.getDomaine());
            stmtThese.setObject(6, theseUniversitaire.getRule(), Types.OTHER); // Correctly set enum type

            stmtThese.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




   public Document getDocumentById(int id, String documentType) {
        String query = "SELECT * FROM " + documentType + " WHERE id = ?";
        Document document = null;
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                document = mapResultSetToDocument(rs, documentType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return document;
    }



    public Document getDocumentByTitle(String title, DocumentType type) {
        String query = "";
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
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, "%" + title + "%");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Rule rule = Rule.valueOf(resultSet.getString("rule")); // Retrieve rule from DB

                switch (type) {
                    case livres:
                        return new Livre(resultSet.getInt("id"),
                                resultSet.getString("title"),
                                resultSet.getString("author"),
                                resultSet.getDate("publication_date").toLocalDate(),
                                resultSet.getString("isbn"),
                                rule
                        );
                    case magazines:
                        return new Magazine(resultSet.getInt("id"),
                                resultSet.getString("title"),
                                resultSet.getString("author"),
                                resultSet.getDate("publication_date").toLocalDate(),
                                resultSet.getString("numero"),
                                rule
                        );
                    case journalscientifiques:
                        return new JournalScientifique(resultSet.getInt("id"),
                                resultSet.getString("title"),
                                resultSet.getString("author"),
                                resultSet.getDate("publication_date").toLocalDate(),
                                resultSet.getString("domaineRecherche"),
                                rule
                        );
                    case theseuniversitaires:
                        return new TheseUniversitaire(resultSet.getInt("id"),
                                resultSet.getString("title"),
                                resultSet.getString("author"),
                                resultSet.getDate("publication_date").toLocalDate(),
                                resultSet.getString("universite"),
                                resultSet.getString("domaine"),
                                rule
                        );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    // Helper method to map ResultSet to Document object
    private Document mapResultSetToDocument(ResultSet rs, String documentType) throws SQLException {

       Rule rule =Rule.valueOf(rs.getString("rule"));
        switch (documentType) {
            case "livres":
                return new Livre(rs.getInt("id"),rs.getString("title"), rs.getString("author"), rs.getDate("publication_date").toLocalDate(), rs.getString("isbn"),rule);
            case "magazines":
                return new Magazine(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getDate("publication_date").toLocalDate(), rs.getString("numero"),rule);
            case "journalscientifiques":
                return new JournalScientifique(rs.getInt("id"),rs.getString("title"), rs.getString("author"), rs.getDate("publication_date").toLocalDate(), rs.getString("domaine_recherche"),rule);
            case "theseuniversitaires":
                return new TheseUniversitaire(rs.getInt("id"),rs.getString("title"), rs.getString("author"), rs.getDate("publication_date").toLocalDate(), rs.getString("universite"), rs.getString("domaine"),rule);
            default:
                return null;
        }
    }

    // Update Document
    public void updateDocument(Document document) {
        String query;
        if (document instanceof Livre) {
            query = "UPDATE livres SET title = ?, author = ?, publication_date = ?, isbn = ?, rule = ? WHERE id = ?";
        } else if (document instanceof Magazine) {
            query = "UPDATE magazines SET title = ?, author = ?, publication_date = ?, numero = ?, rule = ? WHERE id = ?";
        } else if (document instanceof JournalScientifique) {
            query = "UPDATE journalscientifiques SET title = ?, author = ?, publication_date = ?, domaine_recherche = ?, rule = ? WHERE id = ?";
        } else if (document instanceof TheseUniversitaire) {
            query = "UPDATE theseuniversitaires SET title = ?, author = ?, publication_date = ?, universite = ?, domaine = ?, rule = ? WHERE id = ?";
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
                stmt.setObject(5, document.getRule(), Types.OTHER); // Correctly set enum type

                stmt.setInt(6, document.getId());
            } else if (document instanceof Magazine) {
                stmt.setString(4, ((Magazine) document).getNumero());
                stmt.setObject(5, document.getRule(), Types.OTHER); // Correctly set enum type

                stmt.setInt(6, document.getId());
            } else if (document instanceof JournalScientifique) {
                stmt.setString(4, ((JournalScientifique) document).getDomaineRecherche());

                stmt.setObject(5, document.getRule(), Types.OTHER); // Correctly set enum type

                stmt.setInt(6, document.getId());
            } else if (document instanceof TheseUniversitaire) {
                stmt.setString(4, ((TheseUniversitaire) document).getUniversité());
                stmt.setString(5, ((TheseUniversitaire) document).getDomaine());
                // Set the enum as an object
                stmt.setObject(6, document.getRule(), Types.OTHER); // Correctly set enum type

                stmt.setInt(7, document.getId());
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
