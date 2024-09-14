package com.library.management.service;

import com.library.management.dao.DocumentDAOImpl;
import com.library.management.model.document.*;
import com.library.management.model.enums.DocumentType;

import java.util.List;

public class DocumentServiceImpl {
    private DocumentDAOImpl documentDAO;

    public DocumentServiceImpl() {
        this.documentDAO = new DocumentDAOImpl();
    }

    // Create a Livre
    public void createLivre(Livre livre) {
        documentDAO.createLivre(livre);
    }

    // Create a Magazine
    public void createMagazine(Magazine magazine) {
        documentDAO.createMagazine(magazine);
    }

    // Create a JournalScientifique
    public void createJournalScientifique(JournalScientifique journalScientifique) {
        documentDAO.createJournalScientifique(journalScientifique);
    }

    // Create a TheseUniversitaire
    public void createTheseUniversitaire(TheseUniversitaire theseUniversitaire) {
        documentDAO.createTheseUniversitaire(theseUniversitaire);
    }

    // Retrieve a Document by ID (generic)
    public Document getDocumentById(int id, String documentType) {
        return documentDAO.getDocumentById(id, documentType);
    }




    public Document getDocumentByTitle(String title, DocumentType type) {
      return documentDAO.getDocumentByTitle(title,type);
    }
    // Retrieve all documents by type (generic)
    public List<Document> getAllDocuments(String documentType) {
        return documentDAO.getAllDocuments(documentType);
    }

    // Update Document (generic)
    public void updateDocument(Document document) {
        documentDAO.updateDocument(document);
    }

    // Delete Document by ID
    public void deleteDocumentById(int id, DocumentType documentType) {
        documentDAO.deleteDocumentById(id, documentType);
    }

}
