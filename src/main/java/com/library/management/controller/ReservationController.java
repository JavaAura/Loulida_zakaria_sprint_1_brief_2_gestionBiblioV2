package com.library.management.controller;

import com.library.management.model.Reservation;
import com.library.management.service.BorrowingService;
import com.library.management.service.ReservationService;

import java.util.List;
import java.util.Scanner;

public class ReservationController {
    private ReservationService reservationService;
    private BorrowingService borrowingService;
    private Scanner scanner;

    public ReservationController() {
        this.reservationService = new ReservationService();
        this.borrowingService=new BorrowingService();
        this.scanner = new Scanner(System.in);
    }

    public void manageReservation() {
        System.out.println("Manage Reservation:");
        System.out.println("1. Add Reservation");
        System.out.println("2. Delete Reservation");
        System.out.println("3. List All Reservations");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                addReservation();
                break;
            case 2:
                deleteReservation();
                break;
            case 3:
                listAllReservations();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private void addReservation() {
        System.out.println("Enter user ID:");
        int userId = scanner.nextInt();
        System.out.println("Enter document ID:");
        int documentId = scanner.nextInt();


        Reservation reservation = new Reservation(
                0,  // ID is auto-generated
                userId,
                documentId

        );


        if ( borrowingService.CheckIftheyCanborrow(reservation.getDocumentId())){

            System.out.printf("You can borrowed directly  " );
        }
        else if
        (reservationService.CheckIftheyCanreserve(reservation.getDocumentId())  ) {

            reservationService.addReservation(reservation);
            System.out.printf("the document now is reserved" );
        }else {
            System.out.printf("You can't reserve it now it's already reserved " );
        }

    }



    private void deleteReservation() {
        System.out.println("Enter reservation ID:");
        int id = scanner.nextInt();
        reservationService.deleteReservation(id);
    }

    private void listAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}
