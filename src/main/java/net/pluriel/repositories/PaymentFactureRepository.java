package net.pluriel.repositories;

import net.pluriel.entities.PaymentFacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentFactureRepository extends JpaRepository<PaymentFacture, Integer> {
}
