package net.pluriel.repositories;

import net.pluriel.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
