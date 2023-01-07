package com.example.ticketselling;

import com.example.ticketselling.model.Location;
import com.example.ticketselling.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketSellingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketSellingApplication.class, args);
    }
}
