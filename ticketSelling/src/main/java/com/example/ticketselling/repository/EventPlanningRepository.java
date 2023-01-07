package com.example.ticketselling.repository;

import com.example.ticketselling.model.EventPlanning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventPlanningRepository extends JpaRepository<EventPlanning, Integer> {
}
