package org.hunter.carrental4.domain.repository;

import org.hunter.carrental4.domain.model.entity.CustomerReservation;

import java.util.Collection;

public interface ReservationRepository {

    CustomerReservation retrieveById(String reservationId) throws Exception;

    void saveReservation(CustomerReservation customerReservation) throws Exception;

    Collection<CustomerReservation> retrieveAll();
}
