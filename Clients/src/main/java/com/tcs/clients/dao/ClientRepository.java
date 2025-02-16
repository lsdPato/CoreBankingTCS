package com.tcs.clients.dao;

import com.tcs.clients.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

   Optional<Client> findClientByCi(String clientId);
}