package net.pluriel.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.pluriel.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

	Optional<Client> findByName(String name);
	
	@Query("select c.name from Client c where c.name != ?1 and c.name=?2")
    Optional<Client> findByNameUpdate(String clientname,String name);
}
