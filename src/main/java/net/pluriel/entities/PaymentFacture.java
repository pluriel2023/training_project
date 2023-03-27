package net.pluriel.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment_facture")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
