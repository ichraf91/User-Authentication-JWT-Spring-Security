package com.smart.smart.repository;
import com.smart.smart.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAll();
    Optional<Event> findById(Long id);
    Event save(Event event);
    void delete(Event event);
}
