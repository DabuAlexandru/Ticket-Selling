package com.example.ticketselling.repository;

import com.example.ticketselling.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}
