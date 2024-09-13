package com.library.management.controller;

import com.library.management.model.Borrowing;
import com.library.management.service.BorrowingService;

import java.util.List;
import java.util.Scanner;

public class BorrowingController {
    private BorrowingService borrowingService;
    private Scanner scanner;

    public BorrowingController() {
        this.borrowingService = new BorrowingService();
        this.scanner = new Scanner(System.in);
    }


    public void addBorrowing() {
        System.out.println("Enter user ID:");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter document ID:");
        int documentId = scanner.nextInt();


        Borrowing borrowing = new Borrowing(
                0,  // ID is auto-generated
                userId,
                documentId

        );


if (borrowingService.CheckIftheyCanborrow(borrowing.getDocumentId())) {

    borrowingService.addBorrowing(borrowing);
    System.out.printf("the document now is borrowed  " );
    }else {
    System.out.printf("You can't borrow it now but you can reserve it " );
}
    }



    public void ReturnDocument() {
        System.out.println("Enter document ID to return:");
        int id = scanner.nextInt();
        borrowingService.deleteBorrowing(id);




    }

    public void listAllBorrowings() {
        List<Borrowing> borrowings = borrowingService.getAllBorrowings();
       borrowings.stream().forEach( b->System.out.println(b));
    }
}
