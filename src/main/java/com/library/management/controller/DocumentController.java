package com.library.management.controller;

import com.library.management.model.document.*;
import com.library.management.model.enums.DocumentType;
import com.library.management.model.enums.Rule;
import com.library.management.service.DocumentServiceImpl;
import com.library.management.utils.DateUtils;
import com.library.management.utils.InputValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DocumentController {
    private DocumentServiceImpl documentService;
    private Scanner scanner;

    public DocumentController() {
        this.documentService = new DocumentServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    public void addDocument() {
        System.out.println("Enter document type to list (1 for Livre, 2 for Magazine, 3 for JournalScientifique, 4 for ThèseUniversitaire): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String title = InputValidator.obtenirStringValide(scanner, "Enter document title");
        String author = InputValidator.obtenirStringValide(scanner, "Enter document author");

        LocalDate publicationDate = InputValidator.obtenirDateValide(scanner);

        System.out.println("Enter who can borrow this document type (1 : ONLY_STUDENT, 2 : ONLY_TEACHER, 3 : BOTH): ");
        int choice2 = scanner.nextInt();
        scanner.nextLine();
        Rule rule = switch (choice2) {
            case 1 -> Rule.ONLY_STUDENT;
            case 2 -> Rule.ONLY_TEACHER;
            case 3 -> Rule.BOTH;
            default -> {
                System.out.println("Invalid document rule.");
                yield null;
            }
        };

        if (rule == null) return;

        switch (choice) {
            case 1 -> {
                String isbn = InputValidator.obtenirISBNValide(scanner);
                Livre livre = new Livre();
                livre.setIsbn(isbn);
                livre.setAuthor(author);
                livre.setTitle(title);
                livre.setPublicationDate(publicationDate);
                livre.setRule(rule);
                documentService.createLivre(livre);
            }
            case 2 -> {
                int numero = InputValidator.obtenirNumeroValide(scanner);
                Magazine magazine = new Magazine();
                magazine.setNumero(String.valueOf(numero));
                magazine.setAuthor(author);
                magazine.setTitle(title);
                magazine.setPublicationDate(publicationDate);
                magazine.setRule(rule);
                documentService.createMagazine(magazine);
            }
            case 3 -> {
                String domaineRecherche = InputValidator.obtenirStringValide(scanner, "Enter domaine de recherche");
                JournalScientifique journal = new JournalScientifique();
                journal.setDomaineRecherche(domaineRecherche);
                journal.setAuthor(author);
                journal.setTitle(title);
                journal.setPublicationDate(publicationDate);
                journal.setRule(rule);
                documentService.createJournalScientifique(journal);
            }
            case 4 -> {
                String universite = InputValidator.obtenirStringValide(scanner, "Enter université");
                String domaine = InputValidator.obtenirStringValide(scanner, "Enter domaine");
                TheseUniversitaire these = new TheseUniversitaire();
                these.setUniversité(universite);
                these.setDomaine(domaine);
                these.setAuthor(author);
                these.setTitle(title);
                these.setPublicationDate(publicationDate);
                these.setRule(rule);
                documentService.createTheseUniversitaire(these);
            }
            default -> System.out.println("Invalid document type.");
        }
    }

    public void listDocuments() {
        System.out.println("Enter document type to list (1 for Livre, 2 for Magazine, 3 for JournalScientifique, 4 for ThèseUniversitaire, 5 for All): ");
        int choice = scanner.nextInt();

        List<Document> documents = new ArrayList<>();

        switch (choice) {
            case 1 -> documents = documentService.getAllDocuments("livres");
            case 2 -> documents = documentService.getAllDocuments("magazines");
            case 3 -> documents = documentService.getAllDocuments("journalscientifiques");
            case 4 -> documents = documentService.getAllDocuments("theseuniversitaires");
            case 5 -> {
                documents.addAll(documentService.getAllDocuments("livres"));
                documents.addAll(documentService.getAllDocuments("magazines"));
                documents.addAll(documentService.getAllDocuments("journalscientifiques"));
                documents.addAll(documentService.getAllDocuments("theseuniversitaires"));
            }
            default -> {
                System.out.println("Invalid choice");
                return;
            }
        }

        if (!documents.isEmpty()) {
            documents.forEach(Document::displayInfo);
        } else {
            System.out.println("No documents found.");
        }
    }

    public void getDocumentById() {
        System.out.println("Enter document ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume the newline

        String type = InputValidator.obtenirStringValide(scanner, "Enter document type (Livre, Magazine, JournalScientifique, ThèseUniversitaire)");

        Document document = documentService.getDocumentById(id, type);
        if (document != null) {
            document.displayInfo();
        } else {
            System.out.println("Document not found.");
        }
    }

    public void getDocumentByTitle() {
        System.out.println("Enter document type (1 for Livre, 2 for Magazine, 3 for Journal Scientifique, 4 for Thèse Universitaire): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String title = InputValidator.obtenirStringValide(scanner, "Enter document title");

        DocumentType type = switch (choice) {
            case 1 -> DocumentType.livres;
            case 2 -> DocumentType.magazines;
            case 3 -> DocumentType.journalscientifiques;
            case 4 -> DocumentType.theseuniversitaires;
            default -> {
                System.out.println("Invalid document type.");
                yield null;
            }
        };

        if (type == null) return;

        Document document = documentService.getDocumentByTitle(title, type);
        if (document != null) {
            document.displayInfo();
        } else {
            System.out.println("Document not found.");
        }
    }

    public void updateDocument() {
        System.out.println("Enter document ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String title = InputValidator.obtenirStringValide(scanner, "Enter new document title");
        String author = InputValidator.obtenirStringValide(scanner, "Enter new document author");
        LocalDate publicationDate = InputValidator.obtenirDateValide(scanner);

        System.out.println("Enter document type (1 for Livre, 2 for Magazine, 3 for Journal Scientifique, 4 for Thèse Universitaire): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.println("Enter who can borrow this document type (1 : ONLY_STUDENT, 2 : ONLY_TEACHER, 3 : BOTH): ");
        int choice2 = scanner.nextInt();
        scanner.nextLine();
        Rule rule = switch (choice2) {
            case 1 -> Rule.ONLY_STUDENT;
            case 2 -> Rule.ONLY_TEACHER;
            case 3 -> Rule.BOTH;
            default -> {
                System.out.println("Invalid document rule.");
                yield null;
            }
        };

        if (rule == null) return;

        switch (choice) {
            case 1 -> {
                String isbn = InputValidator.obtenirISBNValide(scanner);
                Livre livre = new Livre();
                livre.setIsbn(isbn);
                livre.setAuthor(author);
                livre.setTitle(title);
                livre.setPublicationDate(publicationDate);
                livre.setId(id);
                livre.setRule(rule);
                documentService.updateDocument(livre);
            }
            case 2 -> {
                int numero = InputValidator.obtenirNumeroValide(scanner);
                Magazine magazine = new Magazine();
                magazine.setNumero(String.valueOf(numero));
                magazine.setAuthor(author);
                magazine.setTitle(title);
                magazine.setPublicationDate(publicationDate);
                magazine.setId(id);
                magazine.setRule(rule);
                documentService.updateDocument(magazine);
            }
            case 3 -> {
                String domaineRecherche = InputValidator.obtenirStringValide(scanner, "Enter new domaine de recherche");
                JournalScientifique journal = new JournalScientifique();
                journal.setDomaineRecherche(domaineRecherche);
                journal.setAuthor(author);
                journal.setTitle(title);
                journal.setPublicationDate(publicationDate);
                journal.setId(id);
                journal.setRule(rule);
                documentService.updateDocument(journal);
            }
            case 4 -> {
                String universite = InputValidator.obtenirStringValide(scanner, "Enter new université");
                String domaine = InputValidator.obtenirStringValide(scanner, "Enter new domaine");
                TheseUniversitaire these = new TheseUniversitaire();
                these.setUniversité(universite);
                these.setDomaine(domaine);
                these.setAuthor(author);
                these.setTitle(title);
                these.setPublicationDate(publicationDate);
                these.setId(id);
                these.setRule(rule);
                documentService.updateDocument(these);
            }
            default -> System.out.println("Invalid document type.");
        }

        System.out.println("Document updated successfully.");
    }

    public void deleteDocument() {
        System.out.println("Enter document ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        DocumentType type = validtype();

        documentService.deleteDocumentById(id, type);
        System.out.println("Document deleted successfully.");
    }

    public  DocumentType validtype (){

        System.out.println("Enter document type (1 for Livre, 2 for Magazine, 3 for Journal Scientifique, 4 for Thèse Universitaire): ");
        int choice = scanner.nextInt();
        DocumentType type = null;
        switch (choice) {
            case 1:
                type = DocumentType.livres;
                break;
            case 2:
                type = DocumentType.magazines;
                break;
            case 3:
                type = DocumentType.journalscientifiques;
                break;
            case 4:
                type = DocumentType.theseuniversitaires;
                break;
            default:
                System.out.println("Invalid document type.");
                return type;
        }
        return type;
    }
}
