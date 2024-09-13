package com.library.management.interfaces;

import com.library.management.model.Reservation;

public interface Reservable {


    void Reserver (Reservation reservation);
    void AnnulerReservation(int id);
}
