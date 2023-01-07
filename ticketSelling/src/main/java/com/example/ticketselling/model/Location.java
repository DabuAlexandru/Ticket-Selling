package com.example.ticketselling.model;

import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;

    private String addressDetails;

    private String name;

    private int maxCapacity;

    public Location(String address, String addressDetails, String name, int maxCapacity) {
        this.address = address;
        this.addressDetails = addressDetails;
        this.name = name;
        this.maxCapacity = maxCapacity;
    }

    public Location() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", addressDetails='" + addressDetails + '\'' +
                ", name='" + name + '\'' +
                ", maxCapacity=" + maxCapacity +
                '}';
    }
}
