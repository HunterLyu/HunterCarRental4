package org.hunter.carrental4.infrastructure.persistence;

import org.hunter.carrental4.domain.model.entity.CustomerReservation;
import org.hunter.carrental4.domain.repository.ReservationRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ReservationRepositoryLocalImpl implements ReservationRepository {

    private static Map<String, CustomerReservation> raservations = new HashMap<>();

    @Override
    public CustomerReservation retrieveById(String reservationId) throws Exception {
        return raservations.get(reservationId);
    }

    @Override
    public void saveReservation(CustomerReservation customerReservation) throws Exception {
        if (customerReservation != null && customerReservation.getId() != null) {
            raservations.put(customerReservation.getId(), customerReservation);
        }
    }

    @Override
    public Collection<CustomerReservation> retrieveAll() {
        return raservations.values();
    }
}
