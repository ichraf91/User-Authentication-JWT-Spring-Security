package com.smart.smart.controller;

import com.smart.smart.Models.Event;
import com.smart.smart.Models.Reservation;
import com.smart.smart.Models.User;
import com.smart.smart.Models.UserPrincipal;
import com.smart.smart.dto.Reservationdto;
import com.smart.smart.exception.ResourceNotFoundException;
import com.smart.smart.repository.EventRepository;
import com.smart.smart.repository.ReservationRepository;
import com.smart.smart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:4200")

public class EventController {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return ResponseEntity.ok(events);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventRepository.save(event);
        createdEvent.setTitle(null);
        createdEvent.setDescription(null);
        createdEvent.setStartDate(null);
        createdEvent.setStartDate(null);
        return ResponseEntity.ok(createdEvent);
    }

//    @PutMapping("/{eventId}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Event> updateEvent(@PathVariable Long eventId, @RequestBody Event updatedEvent) {
//        Event event = eventRepository.findById(eventId)
//                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
//
//        event.setTitle(updatedEvent.getTitle());
//        event.setDescription(updatedEvent.getDescription());
//        // Mettez à jour d'autres attributs de l'événement si nécessaire
//
//        Event savedEvent = eventRepository.save(event);
//        return ResponseEntity.ok(savedEvent);
//    }

    @DeleteMapping("/{eventId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventId) {
        eventRepository.deleteById(eventId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{eventId}/reservation")
    public ResponseEntity<?> reserveEvent(@PathVariable Long eventId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        Optional<User> optionalUser = userRepository.findById(userPrincipal.getId());
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalUser.isPresent() && optionalEvent.isPresent()) {
            User user = optionalUser.get();
            Event event = optionalEvent.get();

            // Ajoutez ici la logique pour effectuer la réservation de l'événement par l'utilisateur
            user.addEvent(event);
            userRepository.save(user);

            // Créez un objet ReservationDto avec les informations nécessaires
            Reservationdto reservation = new Reservationdto(user.getUsername(), event.getTitle());

            return ResponseEntity.ok(reservation);
        } else {
            throw new ResourceNotFoundException("User or Event not found");
        }
    }


    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return ResponseEntity.ok(reservations);
    }


}
