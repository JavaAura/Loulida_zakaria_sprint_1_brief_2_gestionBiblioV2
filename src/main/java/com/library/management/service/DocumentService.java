package com.library.management.service;

import com.library.management.model.document.Document;
import java.util.List;
import java.util.Optional;

public interface DocumentService {
    void addDocument(Document document);
    Optional<Document> getDocument(String id);
    void updateDocument(Document document);
    void deleteDocument(String id);
    List<Document> getAllDocuments();
}
