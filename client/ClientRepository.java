package com.example.timesheet.client;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByNameContainingOrAddressContaining(String name, String address);
    // Client findByCode(String code);
    // List<Client> findByAddressContaining(String address);
    Client findById(int id);
    void deleteById(int id);
}
