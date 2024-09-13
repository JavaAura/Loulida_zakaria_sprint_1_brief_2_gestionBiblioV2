package com.library.management.service;

import com.library.management.dao.ReservationDAOImpl;
import com.library.management.model.Reservation;
import java.util.List;

public class ReservationService {
    private ReservationDAOImpl reservationDAO;

    public ReservationService() {
        this.reservationDAO =  new ReservationDAOImpl();
    }

    public void addReservation(Reservation reservation) {
        reservationDAO.Reserver(reservation);
    }



    public boolean CheckIftheyCanreserve(int doc_id) {

        return   reservationDAO.CheckIftheyCanReserve(doc_id);

    }


    public void deleteReservation(int id) {
        reservationDAO.AnnulerReservation(id);
    }

    public Reservation getReservation(int id) {
        return reservationDAO.getReservation(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationDAO.findAll();
    }
}
