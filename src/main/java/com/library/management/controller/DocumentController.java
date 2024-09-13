package com.library.management.controller;

import com.library.management.model.document.*;
import com.library.management.model.enums.DocumentType;
import com.library.management.model.enums.Rule;
import com.library.management.service.DocumentServiceImpl;

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

        System.out.println("Enter document title: ");
        String title = scanner.nextLine().trim();

        System.out.println("Enter document author: ");
        String author = scanner.nextLine().trim();

        System.out.println("Enter publication date (yyyy-MM-dd): ");
        LocalDate publicationDate = LocalDate.parse(scanner.nextLine().trim());

        System.out.println("Enter who can borrow this document type to list (1 : ONLY_STUDENT, 2  : ONLY_TEACHER, 3 : BOTH): ");
        int choice2 = scanner.nextInt();
        scanner.nextLine();
        Rule rule=null;
        switch (choice){
            case 1:
                rule=Rule.ONLY_STUDENT;
                break;
            case 2:
                rule=Rule.ONLY_TEACHER;
                break;
            case 3 :
                rule=Rule.BOTH;
                break;
            default:
                System.out.println("Invalid document type.");}

        switch (choice){
            case 1:
                System.out.println("Enter ISBN: ");
                String isbn = scanner.nextLine().trim();
                Livre livre = new Livre();
                livre.setIsbn(isbn);
                livre.setAuthor(author);
                livre.setTitle(title);
                livre.setPublicationDate(publicationDate);
                livre.setRule(rule);
                System.out.println(livre.getTitle()+"f "+livre.getAuthor()+"+"+ livre.getPublicationDate());
                documentService.createLivre(livre);
                break;
            case 2:
                System.out.println("Enter numero: ");
                String numero = scanner.nextLine().trim();
                Magazine magazine = new Magazine();
                magazine.setNumero(numero);
                magazine.setAuthor(author);
                magazine.setTitle(title);
                magazine.setPublicationDate(publicationDate);
                magazine.setRule(rule);
                documentService.createMagazine(magazine);
                break;
            case 3:
                System.out.println("Enter domaine de recherche: ");
                String domaineRecherche = scanner.nextLine().trim();
                JournalScientifique journal = new JournalScientifique();
                journal.setDomaineRecherche(domaineRecherche);
                journal.setAuthor(author);
                journal.setTitle(title);
                journal.setPublicationDate(publicationDate);
                journal.setRule(rule);

                documentService.createJournalScientifique(journal);
                break;
            case 4:
                System.out.println("Enter université: ");
                String université = scanner.nextLine().trim();
                System.out.println("Enter domaine: ");
                String domaine = scanner.nextLine().trim();
                TheseUniversitaire these = new TheseUniversitaire();
                these.setUniversité(université);
                these.setDomaine(domaine);
                these.setAuthor(author);
                these.setTitle(title);
                these.setPublicationDate(publicationDate);
                these.setRule(rule);
                documentService.createTheseUniversitaire(these);
                break;
            default:
                System.out.println("Invalid document type.");
        }
    }
    public void listDocuments() {
        System.out.println("Enter document type to list (1 for Livre, 2 for Magazine, 3 for JournalScientifique, 4 for ThèseUniversitaire, 5 for All): ");
        int choice = scanner.nextInt();

        List<Document> documents = new ArrayList<>();

        switch (choice) {
            case 1:
                documents = documentService.getAllDocuments("livres");
                break;
            case 2:
                documents = documentService.getAllDocuments("magazines");
                break;
            case 3:
                documents = documentService.getAllDocuments("journalscientifiques");
                break;
            case 4:
                documents = documentService.getAllDocuments("theseuniversitaires");
                break;
            case 5:
                // Fetch all types of documents
                documents.addAll(documentService.getAllDocuments("livres"));
                documents.addAll(documentService.getAllDocuments("magazines"));
                documents.addAll(documentService.getAllDocuments("journalscientifiques"));
                documents.addAll(documentService.getAllDocuments("theseuniversitaires"));
                break;
            default:
                System.out.println("Invalid choice");
                return; // Exit the method if an invalid choice is made
        }

        // Display documents if any are found
        if (!documents.isEmpty()) {
            documents.forEach(Document::displayInfo);
        } else {
            System.out.println("****************No documents found.****************");
        }
    }


    public void getDocumentById() {
        System.out.println("Enter document ID: ");
        String id = scanner.nextLine().trim();

        System.out.println("Enter document type (Livre, Magazine, JournalScientifique, ThèseUniversitaire): ");
        String type = scanner.nextLine().trim();

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
        System.out.println("Enter document title: ");
        String title = scanner.nextLine().trim();



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
                return;
        }

        // Fetch document by title and type
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
        scanner.nextLine();

        System.out.println("Enter new document title: ");
        String title = scanner.nextLine().trim();

        System.out.println("Enter new document author: ");
        String author = scanner.nextLine().trim();

        System.out.println("Enter new publication date (yyyy-MM-dd): ");
        LocalDate publicationDate = LocalDate.parse(scanner.nextLine().trim());

        System.out.println("Enter document type (1 for Livre, 2 for Magazine, 3 for Journal Scientifique, 4 for Thèse Universitaire): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.println("Enter who can borrow this document type to list (1 : ONLY_STUDENT, 2  : ONLY_TEACHER, 3 : BOTH): ");
        int choice2 = scanner.nextInt();
        scanner.nextLine();
        Rule rule=null;
        switch (choice){
            case 1:
                rule=Rule.ONLY_STUDENT;
                break;
            case 2:
                rule=Rule.ONLY_STUDENT;
                break;
            case 3 :
                rule=Rule.BOTH;

            default:
                System.out.println("Invalid document type.");}



        DocumentType type = null;
        switch (choice) {
            case 1:
                System.out.println("Enter new ISBN: ");
                String isbn = scanner.nextLine().trim();
                Livre livre = new Livre();
                livre.setIsbn(isbn);
                livre.setAuthor(author);
                livre.setTitle(title);
                livre.setPublicationDate(publicationDate);
                livre.setId(id);
                livre.setRule(rule);
                documentService.updateDocument(livre);
                break;
            case 2:
                System.out.println("Enter new numero: ");
                String numero = scanner.nextLine().trim();
                Magazine magazine = new Magazine();
                magazine.setNumero(numero);
                magazine.setAuthor(author);
                magazine.setTitle(title);
                magazine.setPublicationDate(publicationDate);
                magazine.setId(id);
                magazine.setRule(rule);
                documentService.updateDocument(magazine);
                break;
            case 3:
                System.out.println("Enter new domaine de recherche: ");
                String domaineRecherche = scanner.nextLine().trim();
                JournalScientifique journal = new JournalScientifique();
                journal.setDomaineRecherche(domaineRecherche);
                journal.setAuthor(author);
                journal.setTitle(title);
                journal.setPublicationDate(publicationDate);
                journal.setId(id);
                journal.setRule(rule);
                documentService.updateDocument(journal);
                break;
            case 4:
                System.out.println("Enter new université: ");
                String université = scanner.nextLine().trim();
                System.out.println("Enter new domaine: ");
                String domaine = scanner.nextLine().trim();
                TheseUniversitaire these = new TheseUniversitaire();
                these.setUniversité(université);
                these.setDomaine(domaine);
                these.setAuthor(author);
                these.setTitle(title);
                these.setPublicationDate(publicationDate);
                 these.setRule(rule);
                these.setId(id);
                documentService.updateDocument(these);
                break;
            default:
                System.out.println("Invalid document type.");
                return;
        }

        System.out.println("Document Updated successfully.");

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
    public void deleteDocument() {
        System.out.println("Enter document ID to delete: ");
        int id = scanner.nextInt();
scanner.nextLine();
      DocumentType type = validtype();

        documentService.deleteDocumentById(id, type);
        System.out.println("Document deleted successfully.");
    }


}
