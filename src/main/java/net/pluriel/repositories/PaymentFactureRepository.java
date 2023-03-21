package net.pluriel.repositories;

import net.pluriel.entities.PaymentFacture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentFactureRepository extends JpaRepository<PaymentFacture, Integer> {
}
