package com.smart.smart.repository;

import com.smart.smart.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Vous pouvez ajouter des méthodes supplémentaires pour des opérations de requête spécifiques
    // si nécessaire, elles seront générées automatiquement par Spring Data JPA
}
