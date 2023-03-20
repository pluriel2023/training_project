package net.pluriel.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_facture")
@Data
@NoArgsConstructor
public class PaymentFacture {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "montant", columnDefinition = "Decimal(14,2) default 0.00")
    private BigDecimal montant;


    @ManyToOne
    @JoinColumn(name = "payment_id")
    @JsonIgnoreProperties({"paymentFactures"})
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "facture_id")
    @JsonIgnoreProperties({"paymentFactures"})
    private Facture facture;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
