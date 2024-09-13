package com.library.management.service;


import com.library.management.dao.BorrowingDAOImpl;
import com.library.management.model.Borrowing;
import java.util.List;

public class BorrowingService {
    private BorrowingDAOImpl borrowingDAO;

    public BorrowingService() {
        this.borrowingDAO = new BorrowingDAOImpl();
    }

    public void addBorrowing(Borrowing borrowing) {
        borrowing.getUserId();
        borrowingDAO.Emprunter(borrowing);
    }


    public boolean CheckIftheyCanborrow(int doc_id) {

         return   borrowingDAO.checkIfIsBorrowed(doc_id);

    }


    public void deleteBorrowing(int id) {
        borrowingDAO.Retourner(id);
    }



    public List<Borrowing> getAllBorrowings() {
        return borrowingDAO.findAll();
    }
}
