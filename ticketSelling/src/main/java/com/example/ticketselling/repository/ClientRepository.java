package com.example.ticketselling.repository;

import com.example.ticketselling.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
