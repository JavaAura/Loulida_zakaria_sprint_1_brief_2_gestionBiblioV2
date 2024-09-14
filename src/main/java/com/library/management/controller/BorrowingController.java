package com.library.management.controller;

import com.library.management.model.Borrowing;
import com.library.management.model.Reservation;
import com.library.management.model.document.Document;
import com.library.management.model.enums.DocumentType;
import com.library.management.model.enums.Rule;
import com.library.management.model.user.Etudiant;
import com.library.management.model.user.Professeur;
import com.library.management.model.user.Utilisateur;
import com.library.management.service.BorrowingService;
import com.library.management.service.DocumentServiceImpl;
import com.library.management.service.ReservationService;
import com.library.management.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BorrowingController {
    private BorrowingService borrowingService;
    private Scanner scanner;
    private ReservationService ReservationService;
    private UserServiceImpl userService;
    private DocumentServiceImpl documentService;
    public BorrowingController() {
        this.borrowingService = new BorrowingService();
        this.ReservationService =new ReservationService();
        this.userService =new UserServiceImpl();
        this.documentService = new DocumentServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    public void addBorrowing() {
        System.out.println("Enter user ID:");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter document ID:");
        int documentId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter document type:");
        String type = validtype().toString();


        Optional<Utilisateur> optionalUser = userService.getUser(userId);
        Document document = documentService.getDocumentById(documentId,type);
        if (optionalUser.isPresent() && document != null) {
            Utilisateur user = optionalUser.get();
            Rule rule = document.getRule();
            if (canUserBorrow(user, rule)) {
                if (borrowingService.CheckIftheyCanborrow(documentId)) {
                    Borrowing borrowing = new Borrowing(
                            0,
                            userId,
                            documentId
                    );
                    borrowingService.addBorrowing(borrowing);
                    System.out.println("The document is now borrowed.");
                } else {
                    System.out.println("The document is already borrowed. You can reserve it.");
                }
            } else {
                System.out.println("You do not have permission to borrow this document.");
            }
        } else {
            System.out.println("Invalid user or document ID.");
        }
    }

    private boolean canUserBorrow(Utilisateur user, Rule rule) {
        if (rule == Rule.BOTH) {
            return true;
        } else if (rule == Rule.ONLY_TEACHER && user instanceof Professeur) {
            return true;
        } else if (rule == Rule.ONLY_STUDENT && user instanceof Etudiant) {
            return true;
        }
        return false;
    }


    public void returnDocument() {
        System.out.println("Enter document ID to return:");
        int id = scanner.nextInt();

        Borrowing borrowed = borrowingService.getborrowing(id);

        if (borrowed != null) {
            borrowingService.deleteBorrowing(id);

            Reservation reservation = ReservationService.getReservation(id);

            if (reservation != null) { // Check if the reservation record exists
                // Create a new borrowing record from the reservation
                Borrowing newBorrowing = new Borrowing(
                        0,  // ID is auto-generated
                        reservation.getUserId(),
                        reservation.getDocumentId()
                );
                // Add the new borrowing record
                ReservationService.deleteReservation(reservation.getId());
                borrowingService.addBorrowing(newBorrowing);
                System.out.println("And now emprunted by the user reservation that we have");
            }
        }
    }


    public void listAllBorrowings() {
        List<Borrowing> borrowings = borrowingService.getAllBorrowings();
       borrowings.stream().forEach( b->System.out.println(b));
    }


    public DocumentType validtype (){

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


