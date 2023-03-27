package net.pluriel.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.pluriel.entities.Facture;
@Repository
public interface FactureRepository extends JpaRepository<Facture, Integer>{
	
	//@Query("select facture from Facture ")
	//public Page<Facture> getAllByClientId(Integer clientId, Pageable pageable);
}
