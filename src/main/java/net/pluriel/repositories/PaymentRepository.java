package net.pluriel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.pluriel.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	
	
}
