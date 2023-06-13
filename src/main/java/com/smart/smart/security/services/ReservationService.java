package com.smart.smart.security.services;


import com.smart.smart.Models.Event;
import com.smart.smart.Models.Reservation;
import com.smart.smart.Models.User;
import com.smart.smart.dto.Reservationdto;
import com.smart.smart.exception.ResourceNotFoundException;
import com.smart.smart.repository.EventRepository;
import com.smart.smart.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final EventRepository eventRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, EventRepository eventRepository) {
        this.reservationRepository = reservationRepository;
        this.eventRepository = eventRepository;
    }
    public List<Reservationdto> getEventReservations(Long eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
                ///.orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        List<Reservationdto> reservations = new ArrayList<>();
        for (User user : event.get().getUsers()) {
            Reservationdto reservation = new Reservationdto(user.getUsername(), user.getEmail(), event.get().getTitle());
            reservations.add(reservation);
        }

        return reservations;
    }
    public Reservation createReservation(Reservation reservation) {
        // Logique pour créer une réservation
        // Vous pouvez effectuer des validations, des vérifications, des traitements supplémentaires, etc.
        // Exemple : reservation.setCreatedDate(new Date());
        return reservationRepository.save(reservation);
    }

    public Reservation getReservationById(Long id) {
        // Logique pour récupérer une réservation par son ID
        // Vous pouvez effectuer des vérifications supplémentaires, des opérations de jointure, etc.
        return reservationRepository.findById(id).orElse(null);
    }

    public void deleteReservation(Long id) {
        // Logique pour supprimer une réservation par son ID
        // Vous pouvez effectuer des vérifications supplémentaires avant la suppression
        reservationRepository.deleteById(id);
    }
}
