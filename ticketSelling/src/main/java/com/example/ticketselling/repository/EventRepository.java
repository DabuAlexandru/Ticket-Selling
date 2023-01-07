package com.example.ticketselling.repository;

import com.example.ticketselling.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
