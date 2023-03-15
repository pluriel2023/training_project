package net.pluriel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.pluriel.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
