package com.example.ticketselling.repository;

import com.example.ticketselling.model.EventPlanning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventPlanningRepository extends JpaRepository<EventPlanning, Integer> {
}
