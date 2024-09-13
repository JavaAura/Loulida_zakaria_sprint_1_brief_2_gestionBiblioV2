package com.library.management;

import com.library.management.controller.BorrowingController;
import com.library.management.controller.DocumentController;

import com.library.management.controller.ReservationController;
import com.library.management.controller.UserController;
import com.library.management.dao.DatabaseConnection;

import java.util.Scanner;

public class LibraryManagementSystem {


    private static Scanner scanner = new Scanner(System.in);
    private static DocumentController documentController = new DocumentController();
    private static UserController userController = new UserController();
    private static BorrowingController borrowController = new BorrowingController();
    private static ReservationController reservationController = new ReservationController();


    public static void main(String[] args) {

        while (true) {

            System.out.println("Library Management System");
            System.out.println("1. Manage Documents");
            System.out.println("2. Manage Users");
            System.out.println("3. Manage Borrowing");
            System.out.println("4. Manage Reservations");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    manageDocuments();
                    break;
                case 2:
                    manageUsers();
                    break;
                case 3:
                    manageBorrowing();
                    break;
                case 4:
                    reservationController.manageReservation();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageDocuments() {
        System.out.println("Document Management");
        System.out.println("1. Add Document");
        System.out.println("2. Get Document");
        System.out.println("3. Update Document");
        System.out.println("4. Delete Document");
        System.out.println("5. List Documents");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                documentController.addDocument();
                break;
            case 2:
                documentController.getDocumentByTitle();
                break;
            case 3:
                documentController.updateDocument();
                break;
            case 4:
                documentController.deleteDocument();
                break;
            case 5:
                documentController.listDocuments();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageUsers() {
        System.out.println("User Management");
        System.out.println("1. Add User");
        System.out.println("2. Get User");
        System.out.println("3. Update User");
        System.out.println("4. Delete User");
        System.out.println("5. List Users");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                userController.addUser();
                break;
            case 2:
                userController.getUser();
                break;
            case 3:
                userController.updateUser();
                break;
            case 4:
                userController.deleteUser();
                break;
            case 5:
                userController.listUsers();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public static void manageBorrowing() {
        System.out.println("Manage Borrowing:");
        System.out.println("1. Add Borrowing");
        System.out.println("3. return doc ");
        System.out.println("3. List All Borrowings");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                borrowController.addBorrowing();
                break;

            case 2:
                borrowController.ReturnDocument();
                break;
            case 3:
                borrowController.listAllBorrowings();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }


}
