package com.example.ticketselling.repository;

import com.example.ticketselling.model.BoughtTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoughtTicketRepository extends JpaRepository<BoughtTicket, Integer> {
}
